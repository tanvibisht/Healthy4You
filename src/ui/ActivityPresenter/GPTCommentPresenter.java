package ui.ActivityPresenter;

import Usecase.UpdateGPTcomment.GPTUpdateOutput;
import ui.DashboardUI;

import javax.swing.*;

public class GPTCommentPresenter implements GPTUpdateOutput {
    final DashboardUI ui;

    public GPTCommentPresenter(DashboardUI u){
        ui = u;
    }

    @Override
    public void prepareFailView(String message) {
        JOptionPane.showMessageDialog(ui.getActivitypanel(), "", message, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void prepareSuccessView(String message) {
        JOptionPane.showMessageDialog(
                ui.getActivitypanel(), "Response", message, JOptionPane.INFORMATION_MESSAGE);
    }
}
