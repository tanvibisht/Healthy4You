import javax.swing.*;
import entities.User;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Example user creation - this will later be replaced with more dynamic logic
        User user = new User("John Doe");

        // Setup the main application window
        JFrame frame = new JFrame("Healthy4You Application");
        JLabel nameLabel = new JLabel("User Name: " + user.getName());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(nameLabel);
        frame.pack();
        frame.setVisible(true);
    }
}
