package Main;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaptureThreadTest {

	@Test
	public void testCaptureThread() {
		try {
			CaptureThread test = new CaptureThread(null, null);
		} catch (Exception e) {
			fail();
		}
	}
}