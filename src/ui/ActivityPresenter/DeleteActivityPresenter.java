package ui.ActivityPresenter;

import Usecase.Activites.DeleteActivity.Output;
import Usecase.Activites.DeleteActivity.OutputData;
import ui.ActivityUI;
import ui.DashboardUI;

import javax.swing.*;

public class DeleteActivityPresenter implements Output {
    final DashboardUI view;
    public DeleteActivityPresenter(DashboardUI ui){
        view = ui;
    }

    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(view.getActivitypanel(), "", error, JOptionPane.WARNING_MESSAGE);
    }

    public void prepareSuccessView(OutputData message) { //don't do anything if you don't want to show
        //success message
    }
}
