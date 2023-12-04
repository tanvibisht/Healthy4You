package service.Controllers.CreateDefaultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateRunningActivity extends CreateDefaultActivity {
    private final String activityType = "Running";

    public CreateRunningActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
