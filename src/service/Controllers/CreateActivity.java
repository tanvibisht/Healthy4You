package service.Controllers;


import Usecase.Activites.CreateActivity.CreatActivityInput;
import Usecase.Activites.CreateActivity.CreatActivityInputData;

import java.time.LocalDateTime;

public class CreateActivity {
    final CreatActivityInput inputBound;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public CreateActivity(CreatActivityInput creatActivityInput){
        inputBound = creatActivityInput;
    }

    public void execute(String description, int duration){
        CreatActivityInputData inputData = new CreatActivityInputData(description, duration, localDateTime);
        inputBound.execute(inputData);
    }

    public void setTime(int year, int month, int dateOfMonth, int hour, int minute){
        localDateTime = LocalDateTime.of(year, month, dateOfMonth, hour, minute);
    }
}
