package com.aquarium.webgame.persistence;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.aquarium.webgame.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.JsonNode;

public class UserFileDAO implements UserDAO {

    private final Map<String, User> users; //name, User
    private final String filePath;
    private final String activeFilePath = "api/data/activeUsers.json";
    private final ObjectMapper objectMapper;

    public UserFileDAO(String filePath, ObjectMapper objectMapper) throws IOException {
        this.users = new HashMap<>();
        this.filePath = filePath;
        this.objectMapper = objectMapper;
    }
    private void loadFromFile() throws IOException {
        users.clear();
        User[] userArray = objectMapper.readValue(new File(filePath), User[].class);
        for (User user : userArray) {
            users.put(user.getName(), user);
        }
    }

    private void saveToFile() throws IOException{
        //remove the session IDs before saving
        
        objectMapper.writeValue(new File(filePath), users.values());
    }

    public User addUser(User user) throws IOException {
        synchronized (users) {
            if (users.get(user) == null) {
                users.put(user.getName(), user);
            saveToFile();
            }
        }

        return user;
    }

    public User addActiveUser(User user) throws IOException {
        synchronized (users) {
            objectMapper.writeValue(new File(activeFilePath), user);
        }

        return user;
    }

    public void removeActiveUser(User user) throws IOException {
        synchronized (users) {
            JsonNode node = objectMapper.readTree(new File(activeFilePath));
            ArrayNode arrayNode = (ArrayNode) node;
            arrayNode.removeIf(n -> n.get("name").asText().equals(user.getName()));
            objectMapper.writeValue(new File(activeFilePath), arrayNode);
        }
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
