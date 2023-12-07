import static org.junit.Assert.*;

import Usecase.Activites.CreateActivity.CreatActivityInputData;
import Usecase.Activites.CreateActivity.CreatActivityInteractor;
import Usecase.Activites.CreateActivity.CreatActivityOutput;
import Usecase.Activites.CreateActivity.CreatActivityOutputData;
import domain.LoggedUser;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;

public class ActivityUseCaseTest {

    private CreatActivityInteractor interactor;
    private TestCreatActivityOutput testOutput;
    private CreatActivityInputData inputData;

    private static class TestCreatActivityOutput implements CreatActivityOutput {
        String message = "";
        boolean isSuccess = false;

        @Override
        public void prepareFailView(String error) {
            message = error;
            isSuccess = false;
        }

        @Override
        public void prepareSuccessView(CreatActivityOutputData outputData) {
            if (outputData != null) {
                message = outputData.getMessage();
            } else {
                message = "No data";
            }
            isSuccess = true;
        }
    }

    @Before
    public void setUp() {
        testOutput = new TestCreatActivityOutput();
        interactor = new CreatActivityInteractor(testOutput);

        // Prepare test data
        inputData = new CreatActivityInputData("Jogging", 30, LocalDateTime.now());
    }

    @Test
    public void testExecuteWithValidUser() {
        // Adjust the user creation to include a password
        LoggedUser.setUser(new User("TestUser", "TestPassword"));

        interactor.execute(inputData);

        // Verify success
        assertTrue(testOutput.isSuccess);
        assertEquals("Success", testOutput.message);
    }

    @Test
    public void testExecuteWithNoUser() {
        // Clear the current user to simulate no logged-in user
        LoggedUser.setUser(null);

        interactor.execute(inputData);

        // Verify failure
        assertFalse(testOutput.isSuccess);
        assertEquals("User not Found", testOutput.message);
    }
}
