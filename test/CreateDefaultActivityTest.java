import Usecase.Activites.CreateActivity.CreatActivityInput;
import Usecase.Activites.CreateActivity.CreatActivityInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.Controllers.CreateDefaultActivity.CreateDefaultActivity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateDefaultActivityTest {

    private CreateDefaultActivity createDefaultActivity;
    private FakeCreateActivityInput fakeInput;

    @BeforeEach
    public void setUp() {
        fakeInput = new FakeCreateActivityInput();
        createDefaultActivity = new CreateDefaultActivity(fakeInput);
    }

    @Test
    public void testExecute() {
        // Set the expected values
        int duration = 60;
        LocalDateTime localDateTime = LocalDateTime.of(2023, 12, 31, 15, 30);
        createDefaultActivity.setTime(2023, 12, 31, 15, 30);

        // Execute the method
        createDefaultActivity.execute(duration);

        // Check if the execute method was called with the expected input data
        CreatActivityInputData inputData = fakeInput.getInputData();
        assertEquals(duration, inputData.getDuration());
    }

    // Define a fake implementation of CreatActivityInput for testing purposes
    private static class FakeCreateActivityInput implements CreatActivityInput {
        private CreatActivityInputData inputData;


        @Override
        public void execute(String type, int duration) {

        }

        @Override
        public void execute(CreatActivityInputData data) {
            inputData = data;
        }

        public CreatActivityInputData getInputData() {
            return inputData;
        }
    }
}
