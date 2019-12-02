import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CityReaderTest {
	
	CityReader cr;

	@BeforeEach
	void setUp() throws Exception {
		cr = new CityReader();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllCities() {
		ArrayList<City> cities = cr.getAllCities();
		City hk = cities.get(0);
		assertEquals(1, hk.getRank());
		assertEquals("Hong Kong", hk.getName());
	}
	
}
