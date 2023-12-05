package service.Controllers.CreateDefaultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateWalkingActivity extends CreateDefaultActivity {
    private final String activityType = "Walking";

    public CreateWalkingActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
