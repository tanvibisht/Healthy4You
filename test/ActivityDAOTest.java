import DAOs.ActivitiesDAO.ActivitiesDAO;
import DAOs.ActivitiesDAO.ActivityDAOFacade;
import DAOs.ActivitiesDAO.ActivityReader;
import DAOs.ActivitiesDAO.ActivitySaver;
import Usecase.Activites.CreateActivity.CreatActivityInput;
import Usecase.Activites.CreateActivity.CreatActivityInputData;
import domain.Activity;
import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Controllers.CreateDefaultActivity.CreateDefaultActivity;
import Usecase.Activites.CreateActivity.CreatActivityInput;
import Usecase.Activites.CreateActivity.CreatActivityInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Controllers.CreateDefaultActivity.CreateDefaultActivity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;



public class ActivityDAOTest {

    private ActivitiesDAO activitiesDAO;
    private CreateDefaultActivityTest.FakeCreateActivityInput fakeInput;

    @BeforeEach
    public void setUp() {
        activitiesDAO = new ActivitiesDAO();
        fakeInput = new CreateDefaultActivityTest.FakeCreateActivityInput();
        CreateDefaultActivity createDefaultActivity = new CreateDefaultActivity(fakeInput);
    }

    @Test
    void testLoadAllActivities() throws IOException {
        Map<String, List<String>> loadedActivities = activitiesDAO.LoadAllActivities();
        assertNotNull(loadedActivities);
        // Additional assertions...
    }

    @Test
    void testWriteAllActivities() throws IOException {
        Map<String, List<String>> activitiesToWrite = new HashMap<>();
        List<String> user1Activities = new ArrayList<>();
        user1Activities.add("Activity1");
        user1Activities.add("Activity2");
        activitiesToWrite.put("User1", user1Activities);

        boolean writeResult = activitiesDAO.WriteAllActivities(activitiesToWrite);
        assertTrue(writeResult);
        // Additional assertions...
    }
}

class StubActivityReader extends ActivityReader {
    @Override
    public List<Activity> getActivities(User user) throws IOException {
        return null; // Implement as needed for test cases
    }
}

class StubActivitySaver extends ActivitySaver {
    @Override
    public boolean saveActivities(User user) {
        return false; // Implement as needed for test cases
    }
}

class ActivityDAOFacadeTest {

    @Test
    public void testGetActivitiesReturnsCorrectData() throws IOException {
        ActivityReader stubReader = new StubActivityReader();
        ActivitySaver stubSaver = new StubActivitySaver();
        ActivityDAOFacade facade = new ActivityDAOFacade(stubReader, stubSaver);

        User testUser = new User("test", "test");
        List<Activity> activities = facade.getActivities(testUser);
        // Assertions...
    }



}
