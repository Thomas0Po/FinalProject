package metronometest;

import static org.junit.Assert.*;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

import metronome.PlayBeats;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayBeatsTest {

	protected PlayBeats testPlayBeats;
	@Before
	public void setUp() throws Exception {
		testPlayBeats = new PlayBeats();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		testPlayBeats.setPlay(true);
		assertTrue("should be true", testPlayBeats.getPlay());
		assertEquals("should be 0", 0, testPlayBeats.getTimeBetweenBeats());
		testPlayBeats.setTimeBetweenBeats(60);
		assertEquals("should be 60", 60, testPlayBeats.getTimeBetweenBeats());
		MidiChannel channel = null;
		try {
		final Synthesizer synthesizer = MidiSystem.getSynthesizer();
		synthesizer.open();
		channel = synthesizer.getChannels()[9];
		}
		catch (Exception e) {
			System.out.println("[Metronome Exception]\t" + e.toString() + " : "
					+ e.getMessage());
		}
		
		assertNotNull("should not be null", channel);
		testPlayBeats.setChannel(channel);
		testPlayBeats.start();
		testPlayBeats.setPlay(false);
		testPlayBeats.setToOff();
		try {
			testPlayBeats.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse("not playing beat, should be false", testPlayBeats.getPlay());
		assertFalse("not playing beat, should be false", testPlayBeats.getToOff());		
	}

}
