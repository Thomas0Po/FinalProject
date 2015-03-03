package metronome;

import javax.sound.midi.MidiChannel;

public class PlayBeats extends Thread
{
	
	public PlayBeats() { super();}
	
	private CurrentConfig	config = null;
    private boolean         play = false;
	private final int       velocity = 127;
    private MidiChannel     channel = null;
    private long            timeBetweenBeats = 0;
    private boolean			still = true;
    
    public CurrentConfig getConfig()
    {
    	return this.config;
    }
    
	public void setConfig(CurrentConfig config) {
		this.config = config;
	}
	
	public boolean getPlay()
	{
		return this.play;
	}
	
	public void setPlay(boolean play) {
		this.play = play;
	}
	
	public MidiChannel getChannel()
	{
		return this.channel;
	}
	
	public void setChannel(MidiChannel channel) {
		this.channel = channel;
	}
	
	public long getTimeBetweenBeats()
	{
		return this.timeBetweenBeats;
	}
	
	public void setTimeBetweenBeats(long timeBetweenBeats) {
		this.timeBetweenBeats = timeBetweenBeats;
	}
	
	public boolean getToOff()
	{
		return this.still;
	}
	
	public void setToOff()
	{
		still = false;
	}
 
	@Override
	public void run() {
		// TODO Auto-generated method stub
        final long  startTime   = System.currentTimeMillis();
        long        wokeLateBy  = 0;
        play = false;

        while (still) 
        {
        	if (play == true)
        	{
            final int noteForThisBeat = config.getSound().getMidiNote();
            
            if (wokeLateBy > 10)    { System.out.println("Woke late by " + wokeLateBy); } 
            else                    { channel.noteOn(noteForThisBeat, velocity);        }
            
            final long currentTimeBeforeSleep   = System.currentTimeMillis();
            final long currentLag               = (currentTimeBeforeSleep - startTime) % timeBetweenBeats;
            final long sleepTime                = timeBetweenBeats - currentLag;
            final long expectedWakeTime         = currentTimeBeforeSleep + sleepTime;
            
            
            try                             { Thread.sleep(sleepTime);              } 
            catch (InterruptedException ex) { System.out.println("Interrupted");    }
            
            wokeLateBy = System.currentTimeMillis() - expectedWakeTime;
            
            channel.noteOff(noteForThisBeat);
        	}
        	else
        	{
        	try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
        }
	}

}
