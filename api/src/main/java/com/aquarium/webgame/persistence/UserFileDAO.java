package com.aquarium.webgame.persistence;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.aquarium.webgame.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserFileDAO implements UserDAO {

    private final Map<Integer, User> users;
    private final String filePath;
    private final ObjectMapper objectMapper;
    private int nextId = 0;

    public UserFileDAO(String filePath, ObjectMapper objectMapper) throws IOException {
        this.users = new HashMap<>();
        this.filePath = filePath;
        this.objectMapper = objectMapper;
    }
    private void loadFromFile() throws IOException {
        users.clear();
        nextId = 0;
        User[] userArray = objectMapper.readValue(new File(filePath), User[].class);
        for (User user : userArray) {
            users.put(user.getId(), user);
            if (user.getId() > nextId) {
                nextId = user.getId();
            }
        }
        nextId++; // Set nextId to one more than the current max ID
    }

    private void saveToFile() throws IOException{
        User[] userArray = users.values().toArray(User[]::new);

        objectMapper.writeValue(new File(filePath), userArray);
    }

    public User addUser(User user) throws IOException {
        synchronized (users) {
            if (users.get(user) == null) {
                users.put(user.getId(), user);
            saveToFile();
            }
        }

        return user;
    }

    public User getUserByName(String name) {
        return null;
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public User[] getUsers() {
        return users.values().toArray(User[]::new);
    }

    public User updateUser(User user) throws IOException {
        return null;
    }

    public boolean deleteUser(int id) throws IOException {
        return false;
    }

    public boolean userExists(String name) throws IOException {
        loadFromFile();
        return users.values().stream().anyMatch(user -> user.getName().equals(name));
    }
}
