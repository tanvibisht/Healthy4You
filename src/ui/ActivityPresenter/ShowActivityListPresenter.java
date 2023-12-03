package ui.ActivityPresenter;

import Usecase.Activites.ShowActivityList.Output;
import Usecase.Activites.ShowActivityList.OutputData;
import ui.ActivityUI;
import ui.DashboardUI;

import javax.swing.*;
import java.util.List;

public class ShowActivityListPresenter implements Output {
    final ActivityUI view;

    public ShowActivityListPresenter(ActivityUI ui){
        view = ui;
    }


    @Override
    public void prepareFailView(String message) {
        JOptionPane.showMessageDialog(view, "", message, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void prepareSuccessView(OutputData outputData) {
        view.getDashboardUI().getActivitypanel().removeAll();
        for (List<String> attributes: outputData.getActivities()){
            view.getDashboardUI().addActivityPanel(attributes.get(0), attributes.get(1),attributes.get(2),
                    attributes.get(3));
        }
    }
}
