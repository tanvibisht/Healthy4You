package DAOs;

import java.io.*;

public class UserDAO {
    private static final String HYDRATION_FILE = "src/hydration.txt";

    public void saveUsername(String username) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HYDRATION_FILE, true))) {
            writer.write(username + "\n");
        }
    }

    public String getLastUsername() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HYDRATION_FILE))) {
            String lastLine = "";
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    lastLine = line;
                }
            }
            return lastLine; // Returns the last username
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
