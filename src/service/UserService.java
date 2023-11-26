package service;

import domain.User;
import java.util.HashMap;
import java.util.Map;

// public class UserService {
//     public User createUser(String username, String password) {
//         // Logic to create a new user
//         return new User(username, password);
//     }
//     // Add other service methods as needed
// }
// import Projects.Project_1_Heathly4You_demo.domain.User;
// import java.util.HashMap;
// import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public User createUser(String username, String password) {
        if (users.containsKey(username)) {
            return null; // User already exists
        }
        User newUser = new User(username, password);
        users.put(username, newUser);
        return newUser;
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Successful login
        }
        return null; // Login failed
    }

    // Additional methods as needed
}
