import DAOs.FileIO;
import DAOs.UserService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private FileIO fileIO;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidateLogin() throws IOException {
        // Arrange
        BufferedReader reader = mock(BufferedReader.class);
        when(fileIO.getBufferedReader(UserService.USERS_FILE)).thenReturn(reader);
        when(reader.readLine())
                .thenReturn("user,password")
                .thenReturn(null);

        // Act
        boolean result = userService.validateLogin("user", "password");

        // Assert
        assertTrue(result);
    }

    // Additional test cases here...
}
