package Main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SoundRecorderTest {

	private DialView acPlug;
	
	@Before
	public void setUp() throws Exception {
		acPlug = new DialView();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStart() throws Exception {
		SoundRecorder test = new SoundRecorder(acPlug);
		
		try {
			test.start();
		} catch (Exception e) {
			fail();
		}
		test.stop();
	}


	@Test
	public void testStop() {
		SoundRecorder test = new SoundRecorder(acPlug);
		
		try {
			test.stop();
			fail();
		} catch (Exception e) {
		}
		
		try {
			test.start();
			test.stop();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testIsError() {
		SoundRecorder test = new SoundRecorder(acPlug);
		
		test.setError(true);
		assertEquals(true, test.isError());
		test.setError(false);
		assertEquals(false, test.isError());
	}

}
