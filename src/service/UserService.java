package service;

import java.io.*;
import java.util.*;

public class UserService {

    private static final String USERS_FILE = "src/users.txt";
    private static final String USER_DATA_FILE = "src/usersdata.txt";

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
    public String getUserLocation(String username){
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return "";
    }
    public void saveUserLocation(String username, String location) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(username + "," + location + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

    }
}

