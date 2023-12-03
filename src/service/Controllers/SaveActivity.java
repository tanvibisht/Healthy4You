package service.Controllers;

import Usecase.Activites.SaveActivities.Input;

public class SaveActivity {
    private final Input input;

    public SaveActivity(Input saveActivityInputBound){
        input = saveActivityInputBound;
    }

    public void execute(){
        input.execute();
    }
}
