package Usecase.Activites.CompleteActivity;

import domain.User;

public class InputData {
    private final int activity;
    InputData(int act){
        activity = act;
    }

    public int getActivity(){
        return activity;
    }
}

