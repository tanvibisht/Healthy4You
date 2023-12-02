package Usecase.Activites.CreateActivity;

import domain.User;

import java.time.LocalDateTime;

public class CreatActivityInputData {
    private final String description;
    private final int duration;
    private final LocalDateTime time;
    public CreatActivityInputData(String des, int du, LocalDateTime t){
        description = des;
        duration = du;
        time = t;
    }


    public String getDescription(){
        return description;
    }

    public int getDuration(){
        return duration;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
