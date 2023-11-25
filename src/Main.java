import service.UserService;
import service.WeatherService;
import ui.DashboardUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserService userService = new UserService();
            WeatherService weatherService = new WeatherService();
            DashboardUI dashboardUI = new DashboardUI(userService, weatherService);
            dashboardUI.display();
        });
    }
}

// package Projects.Project_1_Heathly4You_demo;

// import Projects.Project_1_Heathly4You_demo.service.UserService;
// import Projects.Project_1_Heathly4You_demo.service.WeatherService;
// import Projects.Project_1_Heathly4You_demo.ui.ConsoleUI;
// import Projects.Project_1_Heathly4You_demo.ui.HomepageUI;
// import Projects.Project_1_Heathly4You_demo.domain.User;

// import javax.swing.*;

// public class Main {
//     private static UserService userService;
//     private static WeatherService weatherService;

//     public static void main(String[] args) {
//         userService = new UserService();
//         weatherService = new WeatherService();

//         SwingUtilities.invokeLater(() -> {
//             HomepageUI homepageUI = new HomepageUI(userService);
//             homepageUI.setOnLoginSuccess(Main::showDashboard);
//         });
//     }

//     private static void showDashboard(User user) {
//         // Transition to the dashboard interface
//         ConsoleUI dashboardUI = new ConsoleUI(user, weatherService);
//         // Here, ConsoleUI should be modified to utilize weatherService
//     }
// }

