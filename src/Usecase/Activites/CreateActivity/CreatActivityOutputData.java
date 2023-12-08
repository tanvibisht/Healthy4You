package Usecase.Activites.CreateActivity;

import domain.User;

public class CreatActivityOutputData {
    private final String message;

    public CreatActivityOutputData(String m){
        message = m;
    }

    public String getMessage(){
        return message;
    }
}
