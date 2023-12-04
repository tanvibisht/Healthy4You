package service.Controllers.CreateDefultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateWorkoutActivity extends CreateDefultActivity {
    private final String activityType = "Workout";

    public CreateWorkoutActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
