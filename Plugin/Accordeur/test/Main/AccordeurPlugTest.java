package Main;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccordeurPlugTest {

	@Test
	public void testStop() {
		AccordeurPlug test = new AccordeurPlug();
		
		try {
			test.initialiseComponent();
			test.stop();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testStart() {
		AccordeurPlug test = new AccordeurPlug();
		
		try {
			test.initialiseComponent();
		} catch (Exception e) {
			fail();
		}
		try {
			test.initialiseComponent();
		} catch (Exception e) {
			test.stop();
			fail();
		}
		test.stop();
	}
}
