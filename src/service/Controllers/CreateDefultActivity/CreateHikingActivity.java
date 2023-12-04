package service.Controllers.CreateDefultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateHikingActivity extends CreateDefultActivity {
    private final String activityType = "Hiking";

    public CreateHikingActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
