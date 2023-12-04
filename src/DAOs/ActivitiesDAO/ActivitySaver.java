package DAOs.ActivitiesDAO;

import DAOs.ActivitiesDAO.ActivitiesDAO;
import Usecase.Activites.SaveActivities.DAI;
import domain.Activity;
import domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivitySaver extends ActivitiesDAO implements DAI {
    public boolean saveActivities(User user) {
        try{
            Map<String, List<String>> users = this.LoadAllActivities();
            List<String> userActivity = new ArrayList<>();
            for (Activity activity: user.getActivities()){
                userActivity.add(activity.getDescription() + splitter2 +
                        activity.getTime().format(dateTimeFormatter) + splitter2 +
                        activity.getDuration() + splitter2 + activity.isCompletedToday());
            }
            users.put(user.getUsername(),userActivity);
            this.WriteAllActivities(users);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
