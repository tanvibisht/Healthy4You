
import DAOs.GeoLocationService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeoLocationServiceTest {

    @Test
    void testGetCity() {
        GeoLocationService service = new GeoLocationService();
        String city = service.getCity();

        // This assertion checks that the method returns a non-null and non-error response.
        // Note that this doesn't necessarily confirm the correctness of the city, just the functioning of the API call.
        assertNotNull(city, "City should not be null");
        assertNotEquals("Error fetching location", city, "Should not return error fetching location");
        assertNotEquals("Error:", city.substring(0, 6), "Should not start with error message");
    }
}
