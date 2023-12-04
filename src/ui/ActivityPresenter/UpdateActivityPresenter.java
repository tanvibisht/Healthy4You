package ui.ActivityPresenter;

import ui.DashboardUI;

import javax.swing.*;

public class UpdateActivityPresenter implements Usecase.Activites.UpdateDatabaseActivities.Output {
    final DashboardUI dashboardUI;
    public UpdateActivityPresenter(DashboardUI ui){
        dashboardUI = ui;
    }
    @Override
    public void prepareSuccessView(String message) {
    }

    @Override
    public void prepareFailView(String message) {
        JOptionPane.showMessageDialog(dashboardUI.getActivitypanel(), "", message, JOptionPane.WARNING_MESSAGE);
    }
}