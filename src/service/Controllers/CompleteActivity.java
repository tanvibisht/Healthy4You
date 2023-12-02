package service.Controllers;

import Usecase.Activites.CompleteActivity.Input;
import Usecase.Activites.CompleteActivity.InputData;

public class CompleteActivity {
    private final Input inputBound;
    public  CompleteActivity(Input completeActivityInputBoundary){
        inputBound = completeActivityInputBoundary;
    }
    public void execute(int activityIndex){
        InputData inputData = new InputData(activityIndex);
        inputBound.execute(inputData);
    }
}
