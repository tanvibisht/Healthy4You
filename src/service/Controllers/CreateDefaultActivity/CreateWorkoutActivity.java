package service.Controllers.CreateDefaultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;

public class CreateWorkoutActivity extends CreateDefaultActivity {
    private final String activityType = "Workout";

    public CreateWorkoutActivity(CreatActivityInput creatActivityInput) {
        super(creatActivityInput);
    }
}
