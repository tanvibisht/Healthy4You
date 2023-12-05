package service.Controllers.CreateDefaultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateSwimmingActivity extends CreateDefaultActivity {
    private final String activityType = "Swimming";

    public CreateSwimmingActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
