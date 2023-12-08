import DAOs.UserService;
import Usecase.Hydration.Hydration;
import org.junit.Before;
import org.junit.Test;
import ui.DashboardUI;
import ui.HydrationGraphUI;
import ui.LoginUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HydrationGraphUITest {
    private LoginUI loginUI;

    private DashboardUI dashboardUI;
    private Hydration hydrationService;

    @Before
    public void setUp() {
        // Create a UserService mock (You can use a mocking framework like Mockito for this)
        UserService userService = new UserService() {
            @Override
            public String getUserLocation(String username) {
                return "Toronto";
            }
        };

        SwingUtilities.invokeLater(() -> {
            loginUI = new LoginUI();
        });

        // Create a Hydration mock or use a real implementation if available
        hydrationService = new Hydration() {
            private List<Double> hydrationData = new ArrayList<>();

            @Override
            public List<Double> getUserHydrationData(String username) {
                return hydrationData;
            }

            @Override
            public void addWater(String username, double liters) {
                hydrationData.add(liters);
            }

            @Override
            public void clearData(String username) {
                hydrationData.clear();
            }
        };

        // Create the DashboardUI instance for testing within the EDT
        try {
            SwingUtilities.invokeAndWait(() -> {
                try {
                    dashboardUI = new DashboardUI("TestUser", userService);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitialization() {
        HydrationGraphUI hydrationGraphUI = new HydrationGraphUI(hydrationService, dashboardUI);
        assertNotNull(hydrationGraphUI);
    }


    @Test
    public void testClearData() {
        HydrationGraphUI hydrationGraphUI = new HydrationGraphUI(hydrationService, dashboardUI);

        // Add some data
        hydrationService.addWater("TestUser", 2.5);

        // Simulate clearing data
        hydrationGraphUI.clearData();

        // Ensure that the hydration data has been cleared
        List<Double> hydrationData = hydrationService.getUserHydrationData("TestUser");
        assertTrue(hydrationData.isEmpty());
    }


}
