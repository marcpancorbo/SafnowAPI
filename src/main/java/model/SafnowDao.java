package model;

public interface SafnowDao {
    User getUser(String code);
    void store(Object object);
}
