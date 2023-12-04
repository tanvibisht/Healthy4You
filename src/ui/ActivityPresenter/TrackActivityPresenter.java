package ui.ActivityPresenter;

import Usecase.Activites.TrackActivity.Output;
import Usecase.Activites.TrackActivity.OutputData;
import ui.DashboardUI;

import javax.swing.*;
import java.util.List;

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
        ui.getActivitypanel().removeAll();
        int i = 0;
        for (List<String> attributes: output.getActivities()){
            ui.addActivityPanel(attributes.get(0), attributes.get(1),attributes.get(2),
                    attributes.get(3), String.valueOf(i));
            i += 1;
        }
    }
}
