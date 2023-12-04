package ui.ActivityPresenter;

import Usecase.Activites.TrackActivity.Output;
import Usecase.Activites.TrackActivity.OutputData;
import ui.DashboardUI;

import javax.swing.*;

public class TrackActivityPresenter implements Output {
    final DashboardUI ui;

    public TrackActivityPresenter(DashboardUI u){
        ui = u;
    }
    @Override
    public void prepareFailView(String message) {
        JOptionPane.showMessageDialog(ui.getActivitypanel(), "", message, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void prepareSuccessView(OutputData output) {
    }
}
