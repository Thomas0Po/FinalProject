package metronometest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import metronome.*;

public class CurrentConfigTest {
	protected CurrentConfig configTest;
	@Before
	public void setUp() throws Exception {
		configTest = new CurrentConfig(new TypeSound("toto", 75), 100, "Basique");
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConfig() {
		assertEquals("String name identique", "Basique", configTest.getName());
		assertEquals("Speed identique", 100, configTest.getSpeed());
		assertEquals("String name Sound identique", "toto", configTest.getSound().getName());
		assertEquals("midi note identique", 75, configTest.getSound().getMidiNote());

		configTest.setName("newOne");
		configTest.setSpeed(60);
		configTest.setSound(new TypeSound("francky", 55));
		
		assertEquals("String name identique", "newOne", configTest.getName());
		assertEquals("Speed identique", 60, configTest.getSpeed());
		assertEquals("String name Sound identique", "francky", configTest.getSound().getName());
		assertEquals("midi note identique", 55, configTest.getSound().getMidiNote());

	}

}
