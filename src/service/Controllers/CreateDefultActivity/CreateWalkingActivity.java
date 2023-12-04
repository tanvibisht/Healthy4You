package service.Controllers.CreateDefultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateWalkingActivity extends CreateDefultActivity {
    private final String activityType = "Walking";

    public CreateWalkingActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
