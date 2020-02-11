package com.safnow.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import com.safnow.model.SafnowDao;
import com.safnow.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

import static java.util.Collections.emptyList;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private SafnowDao safnowDao;
    private static final String KEY = "S@fn0W";

    public LoginFilter(String url, AuthenticationManager authManager, SafnowDao safnowDao) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.safnowDao = safnowDao;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        // obtenemos el body de la peticion que asumimos viene en formato JSON
        InputStream body = req.getInputStream();

        // Asumimos que el body tendrá el siguiente JSON  {"username":"ask", "password":"123"}
        // Realizamos un mapeo a nuestra clase User para tener ahi los datos
        User user = new ObjectMapper().readValue(body, User.class);
        // Finalmente autenticamos
        // Spring comparará el user/password recibidos
        // contra el que definimos en la clase SecurityConfig
        try {
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getPhoneNumber(),
                            user.getVerificationCode(),
                            Collections.emptyList()
                    )
            );
        } catch (AuthenticationException ex) {
            throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).entity("Verification error").build());
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        // Si la autenticacion fue exitosa, agregamos el token a la respuesta
        addAuthentication(res, auth.getName());
    }


    void addAuthentication(HttpServletResponse res, String username) throws IOException {

        String token = Jwts.builder()
                .setSubject(username)
                // Hash con el que firmaremos la clave
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
        String tokenJson = new Gson().toJson(token);
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.addHeader("Authorization", "Bearer " + token);
        User user = safnowDao.getUserByPhoneNumber(username);
        user.setVerificated(true);
        out.println(tokenJson);
        out.flush();
    }

    // Método para validar el token enviado por el cliente
    static Authentication getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");

        // si hay un token presente, entonces lo validamos
        if (token != null) {
            try {
                String user = Jwts.parser()
                        .setSigningKey(KEY)
                        .parseClaimsJws(token.replace("Bearer", "")) //este metodo es el que valida
                        .getBody()
                        .getSubject();

                // Recordamos que para las demás peticiones que no sean /login
                // no requerimos una autenticacion por username/password
                // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
                return user != null ?
                        new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                        null;
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
                throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED).entity("Verification error").build());
            }

        }
        return null;
    }
}

