package service.Controllers.CreateDefultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateRunningActivity extends CreateDefultActivity {
    private final String activityType = "Running";

    public CreateRunningActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
