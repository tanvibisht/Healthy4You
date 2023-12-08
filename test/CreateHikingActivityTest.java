import Usecase.Activites.CreateActivity.CreatActivityInput;
import Usecase.Activites.CreateActivity.CreatActivityInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Controllers.CreateDefaultActivity.CreateHikingActivity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateHikingActivityTest {

    private CreateHikingActivity createHikingActivity;
    private FakeCreateActivityInput fakeInput;

    @BeforeEach
    public void setUp() {
        fakeInput = new FakeCreateActivityInput();
        createHikingActivity = new CreateHikingActivity(fakeInput);
    }

    @Test
    public void testExecute() {
        // Set the expected values
        int duration = 90;

        // Execute the method
        createHikingActivity.execute(duration);

        // Check if the execute method was called with the expected activity type
        String activityType = fakeInput.getActivityType();
        assertEquals("Hiking", activityType);
    }

    // Define a fake implementation of CreatActivityInput for testing purposes
    private static class FakeCreateActivityInput implements CreatActivityInput {
        private String activityType;

        @Override
        public void execute(String type, int duration) {
            activityType = type;
        }

        public String getActivityType() {
            return activityType;
        }

        @Override
        public void execute(CreatActivityInputData data) {

        }
    }
}
