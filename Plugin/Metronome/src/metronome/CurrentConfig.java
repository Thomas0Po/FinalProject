package metronome;

public class CurrentConfig {

	private TypeSound	_typeSound;
	private int			_speed;
	private String		_nameConfig;

	public CurrentConfig(TypeSound typeSound, int speed, String nameConfig)
	{
		this._typeSound = typeSound;
		this._speed = speed;
		this._nameConfig = nameConfig;
	}
	
	public int			getSpeed()							{return this._speed;					}
	public void		setSpeed(int newSpeed)
	{
		if (newSpeed >= 1 && newSpeed <= 1250 )
			this._speed = newSpeed;
	}
	public String		getName() 							{return this._nameConfig;				}
	public void		setName(String newNameConfig)		{this._nameConfig = newNameConfig;	}
	public TypeSound	getSound()							{ return this._typeSound;				}
	public void		setSound(TypeSound newTypeSound)	{this._typeSound = newTypeSound;		}
}
