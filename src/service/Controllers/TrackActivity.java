package service.Controllers;

import Usecase.Activites.TrackActivity.Input;

public class TrackActivity {
    private final Input inputBound;

    public TrackActivity(Input trackActInputBoundary){
        inputBound = trackActInputBoundary;
    }

    public void execute(){
        inputBound.execute();
    }
}
