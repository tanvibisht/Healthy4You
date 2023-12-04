package Usecase.Activites.DeleteActivity;

public class OutputData {
    private final String message;

    OutputData(String m){
        message = m;
    }

    public String getMessage(){
        return message;
    }
}
