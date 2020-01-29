package security;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;

@Configuration
@Log
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Bean
    public CorsFilter corsFilter()
    {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        log.info("***** CorsFilter inicialitzat *****");

        return new CorsFilter(source);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("rest/login").permitAll() //permitimos el acceso a /login a cualquiera
                .anyRequest().authenticated() //cualquier otra peticion requiere autenticacion
                .and()
                // Las peticiones /login pasaran previamente por este filtro
                .addFilterBefore(new LoginFilter("/rest/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                // Las demás peticiones pasarán por este filtro para validar el token
                .addFilterBefore(new JwtFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Creamos una cuenta de usuario por default
        auth.inMemoryAuthentication()
                .withUser("ask")
                .password("{noop}123")
                .roles("ADMIN");
    }
    @PostConstruct
    private void init()
    {
        log.info("***** WebSecurityConfig inicialitzat *****");
    }

}
