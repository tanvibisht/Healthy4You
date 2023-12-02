package Usecase.Activites.UpdateDatabaseActivities;

import domain.Activity;
import domain.User;

import java.util.List;

public interface DAI {
    List<Activity> getActivities(User user);
}
