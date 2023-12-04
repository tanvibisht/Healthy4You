package ui;

import Usecase.Activites.CreateActivity.CreatActivityInteractor;
import Usecase.Activites.ShowActivityList.Interactor;
import service.Controllers.CreateActivity;
import service.Controllers.DeleteActivity;
import service.Controllers.ShowActivity;
import ui.ActivityPresenter.AddActivityPresenter;
import ui.ActivityPresenter.DeleteActivityPresenter;
import ui.ActivityPresenter.ShowActivityListPresenter;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ActivityUI extends JFrame implements ActionListener {
    private DashboardUI dashboardUI;
    private JTextField activityNameField;
    private JTextArea activityDescriptionArea;
    private JButton createButton;

    private JButton deleteButton;

    public ActivityUI(DashboardUI dashboardUI) {
        super("Add New Activity");
        this.dashboardUI = dashboardUI;

        setLayout(new BorderLayout());
        setSize(300, 200);
        setLocationRelativeTo(dashboardUI.frame);

        activityNameField = new JTextField();
        activityDescriptionArea = new JTextArea();
        createButton = new JButton("Create");
        createButton.addActionListener(this);

        add(activityNameField, BorderLayout.NORTH);
        add(new JScrollPane(activityDescriptionArea), BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String activityName = activityNameField.getText().trim();
        String activityDescription = activityDescriptionArea.getText().trim();
        ShowActivityListPresenter showActivityListPresenter = new ShowActivityListPresenter(this);
        Interactor showActivityInteractor = new Interactor(showActivityListPresenter);
        ShowActivity showActivity = new ShowActivity(showActivityInteractor);
        if (e.getSource() == createButton) {

            if (!activityName.isEmpty() && !activityDescription.isEmpty()) {
                AddActivityPresenter addActivityPresenter = new AddActivityPresenter(this);
                CreatActivityInteractor creatActivityInteractor = new CreatActivityInteractor(addActivityPresenter);
                CreateActivity createActivity = new CreateActivity(creatActivityInteractor);
                createActivity.setTime(2001, 12, 5, 12, 30);
                createActivity.execute(activityDescription, 1); //need a new slot for duration
                showActivity.execute();
                dispose();
            } else if (e.getSource() == deleteButton) {
                DeleteActivityPresenter deleteActivityPresenter = new DeleteActivityPresenter(this);
                Usecase.Activites.DeleteActivity.Interactor deleteActivityInteractor = (
                        new Usecase.Activites.DeleteActivity.Interactor(deleteActivityPresenter));
                DeleteActivity deleteActivity = new DeleteActivity(deleteActivityInteractor);
                deleteActivity.execute(0);
            }
            else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public DashboardUI getDashboardUI() {
        return dashboardUI;
    }
}

