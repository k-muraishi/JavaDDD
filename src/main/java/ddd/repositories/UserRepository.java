package ddd.repositories;

import ddd.domains.User;

public interface UserRepository {
    void save (User user) throws  UserRepositoryException;
    User find (String name) throws UserRepositoryException;
}
