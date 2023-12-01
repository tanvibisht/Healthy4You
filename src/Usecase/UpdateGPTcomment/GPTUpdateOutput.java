package Usecase.UpdateGPTcomment;

import Usecase.Activites.TrackActivity.OutputData;

public interface GPTUpdateOutput {
    public void prepareFailView(String message);
    public void prepareSuccessView(String message);
}
