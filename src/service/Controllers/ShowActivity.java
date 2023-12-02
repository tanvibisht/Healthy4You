package service.Controllers;

import Usecase.Activites.ShowActivityList.Input;

public class ShowActivity {
    private final Input inputBound;

    public ShowActivity(Input showActivityInputBoundary){
        inputBound = showActivityInputBoundary;
    }

    public void execute(){
        inputBound.execute();
    }
}
