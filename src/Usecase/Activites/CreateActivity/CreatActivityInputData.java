package Usecase.Activites.CreateActivity;

import domain.User;

public class CreatActivityInputData {
    private final String description;
    private final int duration;
    CreatActivityInputData(String des, int du){
        description = des;
        duration = du;
    }


    public String getDescription(){
        return description;
    }

    public int getDuration(){
        return duration;
    }
}
