package reader.model.object;

import java.util.ArrayList;
import java.util.List;

import com.audiveris.proxymusic.ScorePartwise.Part.Measure;

public class _Measure {
	/*
	 * Mesure de proxymusic
	 */
	private Measure					m_Measure = null;
	/*
	 * List de note ou d'attribut
	 */
	private List<NoteOrAttribute>	m_NoteOrAttributes = null;
	/*
	 * Nombre de note ou attribut dans la mesure
	 */
	private Integer					m_NbNoteOrAttributes= null;
	/*
	 * Cette mesure est-elle en couple
	 */
	private boolean					m_isCouple = false;
	
	_Measure() {}
/*	_Measure(Measure measure) {
		if (measure != null) {
			this.m_Measure = measure;
			this.constructMeasure(coupleId);
		}
	}*/
	
	public Measure						getMeasure() { return this.m_Measure; }
	public List<NoteOrAttribute>		getListNoteOrMeasure() { return this.m_NoteOrAttributes; }
	public NoteOrAttribute				getNoteOrMeasure(int index) { 
		if (this.m_NoteOrAttributes != null && index < this.m_NoteOrAttributes.size() && index >= 0)
			return this.m_NoteOrAttributes.get(index);
		return null;
	}
	public Integer						getNbNoteOrAttributes() { return this.m_NbNoteOrAttributes; }
	public boolean						isCouple() { return this.m_isCouple; }

	public Integer						setMeasure(Measure measure, Integer CoupleIdUse, Integer CoupleIdOther)
	{
		if (measure != null) {
			this.m_Measure = measure;
			return this.constructMeasure(CoupleIdUse, CoupleIdOther);
		}
		return CoupleIdUse;
	}
	private Integer constructMeasure(Integer CoupleIdUse,  Integer CoupleIdOther) {
		List<Object> listNoteOrAttribute = m_Measure.getNoteOrBackupOrForward();
		this.m_NoteOrAttributes = new ArrayList<NoteOrAttribute>();
		Integer coupleId = null;
		NoteOrAttribute oldNoteOrAttribute = null;
		Integer idPosition = new Integer(0);
		
		for (int i = 0; i < listNoteOrAttribute.size(); ++i)
		{
			NoteOrAttribute newNoteOrAttribute = new NoteOrAttribute();
			newNoteOrAttribute.setNoteOrAttributes(listNoteOrAttribute.get(i));
			coupleId = newNoteOrAttribute.getCoupleId();
			if (coupleId != null)
			{
				if (CoupleIdUse == coupleId)
					this.m_NoteOrAttributes.add(newNoteOrAttribute);
				else if (CoupleIdOther == coupleId)
				{
				}
				else
				{
					CoupleIdUse = coupleId;
					this.m_NoteOrAttributes.add(newNoteOrAttribute);
				}
				this.m_isCouple = true;
			}
			else
				this.m_NoteOrAttributes.add(newNoteOrAttribute);
			if (oldNoteOrAttribute != null && oldNoteOrAttribute.getNote() != null && newNoteOrAttribute.getNote() != null)
			{
				if (oldNoteOrAttribute.getNote().getDefaultX() == newNoteOrAttribute.getNote().getDefaultX()) {
					newNoteOrAttribute.setIdPosition(idPosition);
				}
				else {
					++idPosition;
					newNoteOrAttribute.setIdPosition(idPosition);
				}
			}
			else
				newNoteOrAttribute.setIdPosition(idPosition);
			oldNoteOrAttribute = newNoteOrAttribute;
		}
		this.m_NbNoteOrAttributes = m_NoteOrAttributes.size();
		return CoupleIdUse;
	}

}
