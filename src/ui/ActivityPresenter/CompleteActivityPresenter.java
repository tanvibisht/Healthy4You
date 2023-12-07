package ui.ActivityPresenter;

import Usecase.Activites.CompleteActivity.Output;
import Usecase.Activites.CompleteActivity.OutputData;
import ui.DashboardUI;

import javax.swing.*;

public class CompleteActivityPresenter implements Output {
    final DashboardUI ui;

    public CompleteActivityPresenter(DashboardUI u){
        ui = u;
    }
    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(ui.getActivitypanel(), "", error, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void prepareSuccessView(OutputData message) {
    }
}
