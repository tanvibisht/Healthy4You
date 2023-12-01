package Usecase.Activites.CreateActivity;

public interface CreatActivityOutput {
    void prepareFailView(String error);
    void prepareSuccessView(CreatActivityOutputData message);
}
