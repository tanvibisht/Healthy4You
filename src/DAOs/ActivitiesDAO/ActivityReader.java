package DAOs.ActivitiesDAO;

import DAOs.ActivitiesDAO.ActivitiesDAO;
import Usecase.Activites.UpdateDatabaseActivities.DAI;
import domain.Activity;
import domain.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityReader extends ActivitiesDAO implements DAI {
    public List<Activity> getActivities(User user) throws IOException {
        Map<String, List<String>> users = this.LoadAllActivities();
        List<Activity> activities = new ArrayList<>();
        if (!users.containsKey(user.getUsername())){
            users.put(user.getUsername(),new ArrayList<>());
        }
        for (String s: users.get(user.getUsername())){
            String[] attributes = s.split(splitter2);
            if (attributes.length == 4) {
                String description = attributes[0];
                LocalDateTime time = LocalDateTime.parse(attributes[1], dateTimeFormatter);
                int duration = Integer.parseInt(attributes[2]);
                boolean completion = Boolean.parseBoolean(attributes[3]);
                Activity activity = new Activity(description, duration, time);
                if (completion) {
                    activity.complete();
                }
                activities.add(activity);
            }
        }
        return activities;
    }
}
