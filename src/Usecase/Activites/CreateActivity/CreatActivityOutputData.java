package Usecase.Activites.CreateActivity;

public class CreatActivityOutputData {
    private final String message;

    CreatActivityOutputData(String m){
        message = m;
    }

    public String getMessage(){
        return message;
    }
}
