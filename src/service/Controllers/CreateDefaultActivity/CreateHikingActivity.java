package service.Controllers.CreateDefaultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateHikingActivity extends CreateDefaultActivity {
    private final String activityType = "Hiking";

    public CreateHikingActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
