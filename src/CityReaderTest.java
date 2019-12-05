import static org.junit.Assert.*;
import org.junit.Test;

public class CityReaderTest {

	@Test
	public void testCityReader() {
		CityReader cr = new CityReader();
		assertEquals("2,Bangkok,Thailand", cr.getCities().get(1).toString());
		assertEquals("United States", cr.getCityToCountry().get("Philadelphia"));
		assertEquals("US", cr.getCityToCountryCode().get("Philadelphia"));
	}

}
