package service.Controllers;

import Usecase.Activites.DeleteActivity.Input;
import Usecase.Activites.DeleteActivity.InputData;

public class DeleteActivity {
    private final Input inputBound;
    public  DeleteActivity(Input deleteActivityInputBoundary){
        inputBound = deleteActivityInputBoundary;
    }
    public void execute(int activityIndex) {
        InputData inputData = new InputData(activityIndex);
        inputBound.execute(inputData);
    }
}
