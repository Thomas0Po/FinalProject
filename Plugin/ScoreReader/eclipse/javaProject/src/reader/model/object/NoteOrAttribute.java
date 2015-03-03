package reader.model.object;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.audiveris.proxymusic.Attributes;
import com.audiveris.proxymusic.Clef;
import com.audiveris.proxymusic.Key;
import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.Pitch;

public class NoteOrAttribute {
	/*
	 * Note ou attribut de proxymusic
	 * Un des deux est forcement null
	 */
	private Note			m_Note = null;
	private Attributes		m_Attribute = null;
	
	/*
	 * Est-ce un attribut ou une note
	 * Un des deux est forcement false
	 * Si les deux sont falses c'est peut etre un forward, back non gere
	 */
	private boolean			m_isAttribute = false;
	private boolean			m_isNote = false;

	/*
	 * L'Id du couple (si c' est une portee en couple pour determine si 
	 * la note ou l'attribut est sur la portee une ou deux
	 */
	private Integer		m_CoupleId = null;

	/* Notes */	
	/*
	 * Duree de la note
	 */
	private BigDecimal		m_Duration = null;

	/*
	 * La valeur du Type de la note 
	 */
	private String			m_TypeValue = null;
	
	/*
	 * Pitch de la note de proxymusic (octave et step)
	 */
	private Pitch			m_Pitch = null;
	
	/* Attributes */
	
	private Integer			m_IdPosition = null;
	
	/*
	 * Cle ou key a cette note (c'est gere comme ca cote proxymusic donc
	 * j'ai laisse mais cela veut dire qu il peut y avoir plusieurs key/clef
	 * ce qui est uniquement util en cas de couple
	 * Dans la clef prendre getSign() et getLine()
	 * Dans la key prendre getFifths()
	 */
	private List<Key>		m_ListKey = null;
	private List<Clef>		m_ListClef = null;
	
	NoteOrAttribute() {}
	/*NoteOrAttribute(Object obj) {
		if (obj != null) {
			if (obj instanceof Note) {
				this.m_Note = (Note)obj;
				m_isNote = true;
			}
			else if (obj instanceof Attributes) {
				this.m_Attribute = (Attributes)obj;
				this.m_isAttribute = true;
			}
			this.constructNoteOrAttribute();
		}
	}*/
	
	public Integer	getCoupleId() { return this.m_CoupleId; }
	public BigDecimal	getDuration() { return this.m_Duration; }
	public String		getTypeValue() { return this.m_TypeValue; }
	public Pitch		getPitch() { return this.m_Pitch; }
	public boolean		isAttribute() { return this.m_isAttribute; }
	public boolean		isNote() { return this.m_isNote; }
	public Note			getNote() { return this.m_Note; }
	public Attributes	getAttribute() { return this.m_Attribute; }
	public List<Key>	getListKey() { return this.m_ListKey; }
	public Key			getKey(int index) {
		if (m_ListKey != null && index < this.m_ListKey.size() && index >= 0)
			return this.m_ListKey.get(index);
		return null;
	}
	public List<Clef>	getListClef() { return this.m_ListClef; }
	public Clef			getClef(int index) {
		if (m_ListClef != null && index < this.m_ListClef.size() && index >= 0)
			return this.m_ListClef.get(index);
		return null;
	}
	public	Integer		getIdPosition() { return this.m_IdPosition; }
	public	void		setIdPosition(Integer newValue) {
		if(this.m_IdPosition == null)
			this.m_IdPosition = new Integer(newValue);
		else
			this.m_IdPosition = newValue;
	}
	
	public void			setNoteOrAttributes(Object obj) {
		if (obj != null) {
			if (obj instanceof Note) {
				this.m_Note = (Note)obj;
				this.m_isNote = true;
			}
			else if (obj instanceof Attributes) {
				this.m_Attribute = (Attributes)obj;
				this.m_isAttribute = true;
			}
			this.constructNoteOrAttribute();
		}
	}
	
	
	private void		constructNoteOrAttribute() {
		if (this.m_isAttribute)
			this.contructAttribute();
		else if (this.m_isNote)
			this.constructNote();
	}
	
	private void constructNote() {
		if (m_Note.getStaff() != null)
			this.m_CoupleId = this.m_Note.getStaff().intValue();
		this.m_Duration = this.m_Note.getDuration();
		if (this.m_Note.getType() != null)
			this.m_TypeValue = this.m_Note.getType().getValue();
		this.m_Pitch = this.m_Note.getPitch();
	}
	
	private void contructAttribute() {
		this.m_ListClef = this.m_Attribute.getClef();
		this.m_ListKey = this.m_Attribute.getKey();
	}
}
