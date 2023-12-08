package Usecase.Activites.CreateActivity;

public interface CreatActivityInput {
    void execute(String type, int duration);

    void execute(CreatActivityInputData data);
}
