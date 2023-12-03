package Usecase.Activites.DeleteActivity;

public interface Output {
    void prepareFailView(String error);
    void prepareSuccessView(OutputData message);
}
