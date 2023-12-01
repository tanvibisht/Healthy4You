package service;

import java.io.*;
import java.util.*;

public class UserService {

    private static final String USERS_FILE = "src/users.txt";

    // Load users from the file
    private Map<String, String> loadUsers() throws IOException {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            file.createNewFile();
        }

        Map<String, String> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        }
        return users;
    }

    // Save user to the file
    public void saveUser(String username, String password) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + "," + password + "\n");
        }
    }

    // Validate login credentials
    public boolean validateLogin(String username, String password) throws IOException {
        Map<String, String> users = loadUsers();
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // Check if username already exists
    public boolean userExists(String username) throws IOException {
        Map<String, String> users = loadUsers();
        return users.containsKey(username);
    }
}
