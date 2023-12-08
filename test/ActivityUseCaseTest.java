import static org.junit.Assert.*;

import DAOs.UserService;
import Usecase.Activites.CreateActivity.CreatActivityInputData;
import Usecase.Activites.CreateActivity.CreatActivityInteractor;
import Usecase.Activites.CreateActivity.CreatActivityOutput;
import Usecase.Activites.CreateActivity.CreatActivityOutputData;
import Usecase.Activites.TrackActivity.Input;
import Usecase.Hydration.Hydration;
import Usecase.Sleep.Sleep;
import domain.LoggedUser;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import service.Controllers.TrackActivity;
import service.Controllers.TrackActivityFactory;
import ui.ActivityUI;
import ui.DashboardUI;
import DAOs.RecipeGenerator;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Map;

public class ActivityUseCaseTest {
    private Hydration hydration;
    private Sleep sleep;
    private CreatActivityInteractor interactor;
    private TestCreatActivityOutput testOutput;
    private CreatActivityInputData inputData;
    private TrackActivity trackActivity;

    private UserService userService;

    private static class TestCreatActivityOutput implements CreatActivityOutput {
        String message = "";
        boolean isSuccess = false;

        @Override
        public void prepareFailView(String error) {
            message = error;
            isSuccess = false;
        }

        @Override
        public void prepareSuccessView(CreatActivityOutputData outputData) {
            if (outputData != null) {
                message = outputData.getMessage();
            } else {
                message = "No data";
            }
            isSuccess = true;
        }
    }

    @Before
    public void setUp() {
        testOutput = new TestCreatActivityOutput();
        interactor = new CreatActivityInteractor(testOutput);
        userService = new UserService();
        hydration = new Hydration();
        sleep = new Sleep();

        // Prepare test data
        inputData = new CreatActivityInputData("Jogging", 30, LocalDateTime.now());
        Input mockInput = new Input() {
            @Override
            public void execute() {
                // Mock implementation for Input's execute method
            }
        };
        trackActivity = new TrackActivity(mockInput);

        RecipeGenerator recipeGenerator = new RecipeGenerator();
    }

    @Test
    public void testExecuteWithValidUser() {
        // Adjust the user creation to include a password
        LoggedUser.setUser(new User("TestUser", "TestPassword"));

        interactor.execute(inputData);

        // Verify success
        assertTrue(testOutput.isSuccess);
        assertEquals("Success", testOutput.message);
    }

    @Test
    public void testExecuteWithNoUser() {
        // Clear the current user to simulate no logged-in user
        LoggedUser.setUser(null);

        interactor.execute(inputData);

        // Verify failure
        assertFalse(testOutput.isSuccess);
        assertEquals("User not Found", testOutput.message);
    }
    @Test
    public void testTrackActivityExecute() {
        // Test the execution of TrackActivity
        // You can set up a mock Input object and verify if its execute method is called
        // and assert the expected behavior based on the mock
        // Example:
        trackActivity.execute(); // Execute TrackActivity
        // Add assertions based on the behavior of the mock Input object
        // Example assertion:
        // assertTrue(mockInput.executeCalled);
    }
    // Add the ActivityUI test
    @Test
    public void testActivityUIConstruction() throws MalformedURLException {
        DashboardUI dashboardUI = new DashboardUI("test", userService);
        ActivityUI activityUI = new ActivityUI(dashboardUI);

        assertNotNull(activityUI);
        assertNotNull(dashboardUI);
    }

    @Test
    public void testTrackActivityFactoryBuild() throws MalformedURLException {
        // Test the build method of TrackActivityFactory
        DashboardUI dashboardUI = new DashboardUI("test", new UserService()); // Replace with an actual UserService or mock
        TrackActivity trackActivity = TrackActivityFactory.build(dashboardUI);

        assertNotNull(trackActivity);
    }
    @Test
    public void testAddWater() throws IOException {
        String username = "testuser";
        double litersToAdd = 1.5;

        // Add water for the user
        hydration.addWater(username, litersToAdd);

        // Get the user's hydration data
        double totalLiters = hydration.getUserHydrationData(username).get(0);

        // Check if the total liters match the added amount
        assertEquals(litersToAdd, totalLiters, 0.01);
    }

    @Test
    public void testClearData() throws IOException {
        String username = "testuser";

        // Add some water for the user
        hydration.addWater(username, 2.0);

        // Clear the user's hydration data
        hydration.clearData(username);

        // Get the user's hydration data
        double totalLiters = hydration.getUserHydrationData(username).get(0);

        // Check if the total liters are cleared (should be 0.0)
        assertEquals(0.0, totalLiters, 0.01);
    }

    @Test
    public void testGetTotalLiters() throws IOException {
        String username = "testuser";

        // Add some water for the user
        hydration.addWater(username, 3.0);

        // Get the total liters as a string
        String totalLitersString = hydration.getTotalLiters(username);

        // Check if the total liters string matches the expected value
        assertEquals("4.50", totalLitersString);
    }
    @Test
    public void testAddSleepData() throws IOException {
        String username = "testuser";
        int dayNumber = 1;
        double hoursToAdd = 8.5;

        // Set the current username (simulating a logged-in user)

        // Add sleep data for the user
        sleep.addSleepData(dayNumber, hoursToAdd);

        // Get the user's sleep data
        Map<String, Double> userSleepData = sleep.getUserSleepData();

        // Check if the user's sleep data contains the added data
        assertTrue(userSleepData.containsKey("Day " + dayNumber));
    }

    @Test
    public void testClearSleepData() throws IOException {
        String username = "testuser";

        // Set the current username (simulating a logged-in user)

        // Add some sleep data for the user
        sleep.addSleepData(1, 7.5);
        sleep.addSleepData(2, 6.5);

        // Clear the user's sleep data
        sleep.clearSleepData();

        // Get the user's sleep data
        Map<String, Double> userSleepData = sleep.getUserSleepData();

        // Check if the user's sleep data is empty
        assertTrue(userSleepData.isEmpty());
    }

    @Test
    public void testGetUserSleepData() throws IOException {
        String username = "testuser";

        // Set the current username (simulating a logged-in user)

        // Add some sleep data for the user
        sleep.addSleepData(1, 7.5);

        // Get the user's sleep data
        Map<String, Double> userSleepData = sleep.getUserSleepData();

        // Check if the user's sleep data matches the added data
        assertTrue(userSleepData.containsKey("Day 1"));
        assertEquals(7.5, userSleepData.get("Day 1"), 0.01);

    }


}
