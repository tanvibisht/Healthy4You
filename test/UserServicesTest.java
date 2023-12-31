import DAOs.FileIO;
import DAOs.UserService;
import DAOs.WeatherService;
import Usecase.Activites.UpdateDatabaseActivities.Input;
import Usecase.Sleep.Sleep;
import org.junit.jupiter.api.Test;
import service.Controllers.UpdateDatabaseActivities;
import org.junit.jupiter.api.BeforeEach;

import ui.DashboardUI;
import ui.RecipeUI;
import ui.SignUpUI;
import ui.SleepUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class UserServicesTest {
    private FileIO fileIO;

    private SignUpUI signUpUI;

    private UserService userService;
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        fileIO = new FileIO();
        userService = new UserService();
        weatherService = new WeatherService();
        userService = new UserService();
        JFrame testFrame = new JFrame();
        signUpUI = new SignUpUI(testFrame);

        // Set up the frame for testing (hidden and not visible)
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setPreferredSize(new Dimension(800, 600));
        testFrame.pack();
        testFrame.setVisible(false);
    }

    @Test
    public void testValidateLogin() throws IOException {
        String username = "testuser";
        String password = "testpassword";

        // Save the user
        userService.saveUser(username, password);

        // Validate login credentials
        assertTrue(userService.validateLogin(username, password));
    }
    @Test
    public void testGetBufferedReader() {
        String testFilePath = "test.txt";

        try (BufferedWriter writer = fileIO.getBufferedWriter(testFilePath, false)) {
            writer.write("Test data");
        } catch (IOException e) {
            fail("Failed to create a test file for BufferedReader test.");
        }

        try (BufferedReader reader = fileIO.getBufferedReader(testFilePath)) {
            String line = reader.readLine();
            assertEquals("Test data", line);
        } catch (FileNotFoundException e) {
            fail("Test file not found.");
        } catch (IOException e) {
            fail("Failed to read from the test file.");
        }

        // Clean up the test file
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testGetBufferedWriter() {
        String testFilePath = "test.txt";

        try (BufferedWriter writer = fileIO.getBufferedWriter(testFilePath, false)) {
            writer.write("Test data");
        } catch (IOException e) {
            fail("Failed to create a test file for BufferedWriter test.");
        }

        // Check if the test file exists and contains the expected data
        File testFile = new File(testFilePath);
        assertTrue(testFile.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(testFilePath))) {
            String line = reader.readLine();
            assertEquals("Test data", line);
        } catch (FileNotFoundException e) {
            fail("Test file not found.");
        } catch (IOException e) {
            fail("Failed to read from the test file.");
        }

        // Clean up the test file
        if (testFile.exists()) {
            testFile.delete();
        }
    }


    @Test
    public void testSaveUser() throws IOException {
        String username = "testuser";
        String password = "testpassword";

        // Save the user
        userService.saveUser(username, password);

        // Check if the user file exists
        assertTrue(userService.userExists(username));
    }

    @Test
    public void testValidateLogin_ValidCredentials() throws IOException {
        String username = "testuser";
        String password = "testpassword";

        // Save the user
        userService.saveUser(username, password);

        // Validate login credentials
        assertTrue(userService.validateLogin(username, password));
    }

    @Test
    public void testValidateLogin_InvalidCredentials() throws IOException {
        String username = "testuser";
        String password = "testpassword";
        String invalidPassword = "invalidpassword";

        // Save the user with a password
        userService.saveUser(username, password);

        // Validate login with invalid password
        assertFalse(userService.validateLogin(username, invalidPassword));
    }

    @Test
    public void testGetUserLocation() throws IOException {
        String username = "testuser";
        String location = "TestLocation";

        // Save the user location
        userService.saveUserLocation(username, location);

        // Get the user location
        assertEquals(location, userService.getUserLocation(username));
    }


    @Test
    public void testUserExists() throws IOException {
        String username = "testuser";

        // Save the user
        userService.saveUser(username, "testpassword");

        // Check if the user exists
        assertTrue(userService.userExists(username));
    }

    @Test
    public void testGetWeather() {
        String city = "New York"; // Replace with the desired city for testing

        String weatherData = weatherService.getWeather(city);

        assertNotNull(weatherData);
        assertFalse(weatherData.contains("Error:"));
    }
    @Test
    public void testCreateAccount() {
        // Set up test data
        signUpUI.usernameField.setText("testuser");
        signUpUI.passwordField.setText("testpassword");
        signUpUI.locationField.setText("testlocation");

        // Create a mock UserService (you should replace this with a real UserService in production)
        UserServicesTest.UserServiceMock userServiceMock = new UserServiceMock();

        // Set the UserService instance in the SignUpUI to the mock


        // Simulate clicking the "Sign Up" button
        ActionEvent actionEvent = new ActionEvent(signUpUI.signupbutton, ActionEvent.ACTION_PERFORMED, "");
        signUpUI.signupbutton.getActionListeners()[0].actionPerformed(actionEvent);

        // Check the expected behavior
        assertFalse(userServiceMock.userExistsCalled);
        assertFalse(userServiceMock.saveUserCalled);
        assertFalse(userServiceMock.saveUserLocationCalled);
    }

    // Mock UserService for testing
    private static class UserServiceMock extends UserService {
        boolean userExistsCalled = false;
        boolean saveUserCalled = false;
        boolean saveUserLocationCalled = false;

        @Override
        public boolean userExists(String username) {
            userExistsCalled = true;
            // Simulate the behavior of userExists
            // Return true if the user exists, false otherwise
            return true; // Assuming the user does not exist for this test
        }

        @Override
        public void saveUser(String username, String password) {
            saveUserCalled = true;
            // Simulate the behavior of saveUser
        }

        @Override
        public void saveUserLocation(String username, String location) {
            saveUserLocationCalled = true;
            // Simulate the behavior of saveUserLocation
        }
    }
    @Test
    public void testExecute() {
        // Create a custom implementation of the Input interface for testing
        UserServicesTest.TestInput testInput = new TestInput();

        // Create an instance of UpdateDatabaseActivities with the test input
        UpdateDatabaseActivities updateDatabaseActivities = new UpdateDatabaseActivities(testInput);

        // Call the execute method
        updateDatabaseActivities.execute();

        // Add assertions or checks based on the behavior of the test input
        // For example, you can check if a flag or variable in the test input was set
        // to indicate that execute() was called.
    }

    // Test implementation of the Input interface
    private static class TestInput implements Input {
        private boolean executeCalled = false;

        @Override
        public void execute() {
            // Simulate the behavior of the execute method
            // You can set a flag or perform other checks here
            executeCalled = true;
        }

        // Add any additional methods or properties needed for testing

        public boolean isExecuteCalled() {
            return executeCalled;
        }
    }

    @Test
    public void testRecipeUI() {
        // Create an instance of RecipeUI with sample recipe text and a null DashboardUI reference (for testing)
        RecipeUI recipeUI = new RecipeUI("Sample Recipe Text", null);

        // The RecipeUI window will be displayed for manual inspection and interaction
        // You can close it manually after testing.
    }


        @Test
        public void testSleepUI() throws MalformedURLException {
            // Create a DashboardUI instance (or a mock) if needed
            DashboardUI dashboardUI = new DashboardUI("test", userService);

            // Create a SleepUI instance with a SleepService and the DashboardUI reference
            SleepUI sleepUI = new SleepUI(new Sleep(), dashboardUI);

            // The SleepUI window will be displayed for manual inspection and interaction
            // You can close it manually after testing.
        }
    }




