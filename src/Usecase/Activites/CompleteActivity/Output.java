package Usecase.Activites.CompleteActivity;


public interface Output {
    void prepareFailView(String error);
    void prepareSuccessView(OutputData message);
}
