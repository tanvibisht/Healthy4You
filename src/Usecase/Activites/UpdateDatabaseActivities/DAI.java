package Usecase.Activites.UpdateDatabaseActivities;

import domain.Activity;
import domain.User;

import java.io.IOException;
import java.util.List;

public interface DAI {
    List<Activity> getActivities(User user) throws IOException;
}
