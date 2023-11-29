package Usecase.Activites.CompleteActivity;

import domain.User;

public class InputData {
    private final User user;
    private final int activity;
    InputData(User u, int act){
        user = u;
        activity = act;
    }

    public User getUser(){
        return user;
    }

    public int getActivity(){
        return activity;
    }
}

