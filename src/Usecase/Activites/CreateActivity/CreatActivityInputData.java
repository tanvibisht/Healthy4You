package Usecase.Activites.CreateActivity;

import domain.User;

public class CreatActivityInputData {
    private final User user;
    private final String description;
    private final int duration;
    CreatActivityInputData(User u, String des, int du){
        user = u;
        description = des;
        duration = du;
    }

    public User getUser(){
        return user;
    }

    public String getDescription(){
        return description;
    }

    public int getDuration(){
        return duration;
    }
}
