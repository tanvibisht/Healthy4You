package ui.ActivityPresenter;

import Usecase.Activites.SaveActivities.Output;
import ui.DashboardUI;

import javax.swing.*;

public class SaveActivityPresenter implements Output {
    final DashboardUI ui;

    public SaveActivityPresenter(DashboardUI u){
        ui = u;
    }
    @Override
    public void prepareFailView(String message) {
        JOptionPane.showMessageDialog(ui.getActivitypanel(), "", message, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void prepareSuccessView(String message) {
    }
}
