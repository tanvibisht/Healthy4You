import DAOs.UserService;
import DAOs.WeatherService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class UserServicesTest {

    private UserService userService;
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        weatherService = new WeatherService();
        userService = new UserService();
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
}
