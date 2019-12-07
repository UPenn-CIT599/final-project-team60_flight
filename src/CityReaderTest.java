import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the functioning of the file reader class by testing getters.
 * @author xueyingli
 *
 */
public class CityReaderTest {
	
	CityReader cr;

	@Before
	public void setUp() throws Exception {
		cr = new CityReader();
	}
	
	@Test
	public void testCityReader() {
		assertTrue(cr.getCities().size() == 137);
		assertTrue(cr.getCityToCountry().size() == 137);
		assertTrue(cr.getCityToCountryCode().size() == 137);
	}

	@Test
	public void testGetCities() {
		assertEquals("2,Bangkok,Thailand", cr.getCities().get(1).toString());
	}

	@Test
	public void testGetCityToCountry() {
		assertEquals("United States", cr.getCityToCountry().get("Philadelphia"));
	}

	@Test
	public void testGetCityToCountryCode() {
		assertEquals("US", cr.getCityToCountryCode().get("Philadelphia"));
	}

}
