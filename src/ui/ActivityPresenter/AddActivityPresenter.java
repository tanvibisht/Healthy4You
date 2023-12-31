package ui.ActivityPresenter;

import Usecase.Activites.CreateActivity.CreatActivityOutput;
import Usecase.Activites.CreateActivity.CreatActivityOutputData;
import ui.ActivityUI;

import javax.swing.*;

public class AddActivityPresenter implements CreatActivityOutput {
    final ActivityUI view;
    public AddActivityPresenter(ActivityUI ui){
        view = ui;
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(view, "", error, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void prepareSuccessView(CreatActivityOutputData message) {
    }
}
