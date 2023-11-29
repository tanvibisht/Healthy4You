package Usecase.UpdateGPTcomment;

import domain.Activity;

import java.util.List;

public class GPTInputData {
    final private List<Activity> Activities;

    GPTInputData(List<Activity> A){
        Activities = A;
    }

    public List<Activity> getActivities(){
        return Activities;
    }
}
