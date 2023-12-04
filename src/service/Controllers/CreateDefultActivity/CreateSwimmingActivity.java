package service.Controllers.CreateDefultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateSwimmingActivity extends CreateDefultActivity {
    private final String activityType = "Swimming";

    public CreateSwimmingActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
