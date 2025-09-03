package com.aquarium.webgame.persistence;

import java.io.IOException;

import com.aquarium.webgame.model.User;

public interface UserDAO {
    User[] getUsers() throws IOException;

    User getUserById(int id) throws IOException;

    User addUser(User user) throws IOException;

    User updateUser(User user) throws IOException;

    boolean deleteUser(int id) throws IOException;

    boolean userExists(String name) throws IOException;

    User addActiveUser(User user) throws IOException;

    void removeActiveUser(User user) throws IOException;
}
