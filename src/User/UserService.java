package User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static final String USERS_FILE = "src/Users.txt";

    // Load users from the file
    private Map<String, String> loadUsers() {
        Map<String, String> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                users.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            // Handle exception
        }
        return users;
    }

    // Save users to the file
    private void saveUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + "," + password + "\n");
        } catch (IOException e) {
            // Handle exception
        }
    }

    // Register a new user
    public boolean register(User user) {
        Map<String, String> users = loadUsers();
        if (users.containsKey(user.getUsername())) {
            return false; // User already exists
        }
        saveUser(user.getUsername(), user.getPassword());
        return true;
    }

    // Check login credentials
    public boolean login(String username, String password) {
        Map<String, String> users = loadUsers();
        return users.getOrDefault(username, "").equals(password);
    }
}
