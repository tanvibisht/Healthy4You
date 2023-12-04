package ui.ActivityPresenter;

import Usecase.Activites.ShowActivityList.Output;
import Usecase.Activites.ShowActivityList.OutputData;
import ui.ActivityUI;
import ui.DashboardUI;

import javax.swing.*;
import java.util.List;

public class ShowActivityListPresenter implements Output {
    final DashboardUI view;

    public ShowActivityListPresenter(DashboardUI ui){
        view = ui;
    }


    @Override
    public void prepareFailView(String message) {
        JOptionPane.showMessageDialog(view.getActivitypanel(), "", message, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void prepareSuccessView(OutputData outputData) {
        view.getActivitypanel().removeAll();
        int i = 0;
        for (List<String> attributes: outputData.getActivities()){
            view.addActivityPanel(attributes.get(0), attributes.get(1),attributes.get(2),
                    attributes.get(3), String.valueOf(i));
            i += 1;
        }
    }
}