package com.aquarium.webgame.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.aquarium.webgame.model.User;
import com.aquarium.webgame.persistence.UserDAO;
import com.aquarium.webgame.persistence.UserFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginService {

    private UserDAO userFileDao; 
    private Map<String, User> activeUsers; //sessionID, User

    public LoginService() {
        try {
            this.userFileDao = new UserFileDAO("api/data/users.json", new ObjectMapper());
            this.activeUsers = new HashMap<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(WebSocketSession session, String username)  throws IOException {
        User user = new User(0, username, "", session.getId(), true);
        try {
            boolean userExists = userFileDao.userExists(username);
            activeUsers.put(user.getSessionId(), user);
            userFileDao.addUser(user);
            userFileDao.addActiveUser(user);
            System.out.println("User " + username + " logged in with session " + session.getId()+". Active users: " + activeUsers.size());
            return userExists;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }  
    }

    public boolean logout(WebSocketSession session) throws IOException {
        if (activeUsers.containsKey(session.getId())) {
            User user = activeUsers.remove(session.getId());
            userFileDao.removeActiveUser(user);
            System.out.println("User " + user.getName() + " logged out from session " + session.getId() + ". Active users: " + activeUsers.size());
            return true;
        } else {
            System.out.println("No active user with session " + session.getId() + " found to log out.");
            return false;
        }
    }

    public User[] getUsers() {
        try {
            return userFileDao.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return new User[0];
        }
    }

    public boolean userLoggedIn(String sessionId) {
        return activeUsers.containsKey(sessionId);
    }

    public User getUserBySessionId(String sessionId) {
        return activeUsers.get(sessionId);
    }
}