package Usecase.Activites.ShowActivityList;

public interface Output {
    public void prepareFailView(String message);
    public void prepareSuccessView(OutputData outputData);
}
