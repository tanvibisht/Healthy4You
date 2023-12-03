package service.Controllers;

import Usecase.Activites.UpdateDatabaseActivities.Input;

public class UpdateDatabaseActivities {
    private final Input inputBound;

    public UpdateDatabaseActivities(Input updateDatabaseActivitiesInputBound){
        inputBound = updateDatabaseActivitiesInputBound;
    }

    public void execute(){
        inputBound.execute();
    }
}
