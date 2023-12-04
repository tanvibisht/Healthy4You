package service.Controllers.CreateDefaultActivity;

import Usecase.Activites.CreateActivity.CreatActivityInput;
import Usecase.Activites.CreateActivity.CreatActivityInputData;

import java.time.LocalDateTime;

public class CreateDefaultActivity {
    final CreatActivityInput inputBound;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private final String activityType = "";

    public CreateDefaultActivity(CreatActivityInput creatActivityInput){
        inputBound = creatActivityInput;
    }

    public void execute(int duration){
        CreatActivityInputData inputData = new CreatActivityInputData(activityType, duration, localDateTime);
        inputBound.execute(inputData);
    }

    public void setTime(int year, int month, int dateOfMonth, int hour, int minute){
        localDateTime = LocalDateTime.of(year, month, dateOfMonth, hour, minute);
    }
}
