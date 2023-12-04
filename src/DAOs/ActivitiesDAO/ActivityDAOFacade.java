package DAOs.ActivitiesDAO;

import domain.Activity;
import domain.User;

import java.io.IOException;
import java.util.List;

public class ActivityDAOFacade {
    private ActivityReader activityReader;
    private ActivitySaver activitySaver;

    public ActivityDAOFacade(ActivityReader reader, ActivitySaver saver){
        activityReader = reader;
        activitySaver = saver;
    }

    public List<Activity> getActivities(User user) throws IOException {
        return activityReader.getActivities(user);
    }

    public boolean saveActivities(User user){
        return activitySaver.saveActivities(user);
    }
}
