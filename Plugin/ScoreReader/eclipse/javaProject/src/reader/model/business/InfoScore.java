package reader.model.business;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.audiveris.proxymusic.Attributes;
import com.audiveris.proxymusic.Clef;
import com.audiveris.proxymusic.Key;
import com.audiveris.proxymusic.Note;
import com.audiveris.proxymusic.NoteType;
import com.audiveris.proxymusic.Pitch;
import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.ScorePartwise.Part;
import com.audiveris.proxymusic.ScorePartwise.Part.Measure;

public class InfoScore {

	private XmlManager			m_xmlManager = null;
	private ScorePartwise		m_scorePartwise = null;
	
	
	public InfoScore() {
		this.m_xmlManager = new XmlManager();
	}

	/*
	 * R�cup�re un xml score
	 */
	public void		newScore(String fileName)
	{
		try {
			m_scorePartwise = m_xmlManager.xmlReader(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("InfoScore n'a pas r�ussis � r�cup�rer le xml.");
		}
	}
	
	/*
	 * Nombre de port� avec la liste des port�es
	 */
	public int		getNumberOfPart()
	{
		int			numberOfPart = 0;
		
		if (m_scorePartwise == null)
			return -1;
		numberOfPart += m_scorePartwise.getPart().size();
		return numberOfPart;
	}

	/*
	 * La liste des port�es de scorePartwise
	 */
	public List<Part>		getListPart()
	{
		if (m_scorePartwise == null)
			return null;
		return m_scorePartwise.getPart();
	}

	
	/*
	 * Le nombre de mesure que contient la port�e donn�e en param�tre
	 */
	public int		getNumberOfMeasureByPart(Part part)
	{
		if (part != null)
			return part.getMeasure().size();
		return -1;
	}
	
	/*
	 * Le nombre de mesure que contient la port�e � l'index donn� en param�tre
	 */
	public int		getNumberOfMeasureByIndexPart(int indexPart)
	{
		if (indexPart < m_scorePartwise.getPart().size() && indexPart >= 0 && m_scorePartwise != null) {
			return m_scorePartwise.getPart().get(indexPart).getMeasure().size();
		}
		return -1;
	}
	
	/*
	 * Une liste du nombre de mesure de chaque port�e
	 */
	public List<Integer>	getAllNumberOfMeasure()
	{	
		if (m_scorePartwise == null)
			return null;
		List<Integer> listMeasure = new ArrayList<Integer>();
		
		for (int i = 0; i < m_scorePartwise.getPart().size(); ++i)
		{
			if (m_scorePartwise.getPart().get(i) != null)
				listMeasure.add(m_scorePartwise.getPart().get(i).getMeasure().size());
		}
		return listMeasure;
	}
	
	/*
	 * La liste des mesures que contient la port�e donn�e en param�tre
	 */
	public List<Measure>	getListMeasureByPart(Part part)
	{
		if (part != null)
			return part.getMeasure();
		return null;
	}
	
	/*
	 *  La liste des mesures que contient la port�e � l'index donn� en param�tre
	 */
	public List<Measure>	getListMeasureByIndexPart(int indexPart)
	{
		if (indexPart < m_scorePartwise.getPart().size() && indexPart >= 0 && m_scorePartwise != null)
			return m_scorePartwise.getPart().get(indexPart).getMeasure();
		return null;
	}
	
	public List<List<Measure>> getAllListMeasure()
	{
		if (m_scorePartwise == null)
			return null;
		List<List<Measure>> allMeasure = new ArrayList<List<Measure>>();
		
		for (int i = 0; i < m_scorePartwise.getPart().size(); ++i)
		{
			if (m_scorePartwise.getPart().get(i) != null)
				allMeasure.add(m_scorePartwise.getPart().get(i).getMeasure());
		}
		return allMeasure;
	}
	
	/*
	 * Le nombre de note ou backup ou forward dans la mesure donn�e en param�tre
	 */
	public int		getNumberOfNoteOrBackupOrForwardByMeasure(Measure measure)
	{
		if (measure != null)
			return measure.getNoteOrBackupOrForward().size();
		return -1;
	}
	
	/*
	 * Tout les nombres de notes par port�es et par mesures.
	 * Une liste dont la size est le nombre de port�e. Chaque iterator de cette liste contient une liste 
	 * dont la size est le nombre de mesure. Chaque iterator de cette liste est le nombre de note.
	 * 
	 */
	public List<List<Integer>>		getAllNumberOfNoteOrBackupOrForward()
	{
		if (m_scorePartwise == null)
			return null;
		List<List<Integer>> allNotes = new ArrayList<List<Integer>>();
		for (int i = 0; i < m_scorePartwise.getPart().size(); ++i)
		{
			List<Integer> notes = new ArrayList<Integer>();
			if (m_scorePartwise.getPart().get(i) != null)
			{
				for (int x = 0; x < m_scorePartwise.getPart().get(i).getMeasure().size(); ++x)
				{
					if (m_scorePartwise.getPart().get(i).getMeasure().get(x) != null)
						notes.add(m_scorePartwise.getPart().get(i).getMeasure().get(x).getNoteOrBackupOrForward().size());
				}
				allNotes.add(notes);
			}
			
		}
		return allNotes;
	}
	/*
	 * List d'obj qui peut �tre une note, backup, attributes ou forward.
	 */
	public List<Object>		getNoteOrBackupOrFowardByMeasure(Measure measure)
	{
		if (measure != null)
			return measure.getNoteOrBackupOrForward();
		return null;
	}
	
	/*
	 * Toutes les notes par port�es et par mesures.
	 * Une liste dont la size est le nombre de port�e. Chaque iterator de cette liste contient une liste 
	 * dont la size est le nombre de mesure. Chaque iterator de cette liste contient un obj qui peut �tre 
	 * une note, backup, attributes ou forward..
	 * 
	 */
	public List<List<Object>>		getAllNoteOrBackupOrFoward()
	{
		if (m_scorePartwise == null)
			return null;
		List<List<Object>> allNotes = new ArrayList<List<Object>>();
		for (int i = 0; i < m_scorePartwise.getPart().size(); ++i)
		{
			if (m_scorePartwise.getPart().get(i) != null)
			{
				for (int x = 0; x < m_scorePartwise.getPart().get(i).getMeasure().size(); ++x)
				{
					if (m_scorePartwise.getPart().get(i).getMeasure().get(x) != null)
						allNotes.add(m_scorePartwise.getPart().get(i).getMeasure().get(x).getNoteOrBackupOrForward());
				}
			}
		}
		return allNotes;
	}
	/*
	 * Pour dire si c'est une note
	 */
	public boolean			isNote(Object obj)
	{
		if (obj instanceof Note)
			return true;
		return false;
	}
	
	/*
	 * Pour dire si c'est un attribut
	 * Peut �tre null
	 */
	public boolean			isAttributes(Object obj)
	{
		if (obj instanceof Attributes)
			return true;
		return false;
	}
	
	/*
	 *  Get NoteType
	 *  Peut �tre null
	 */
	public NoteType		getNoteTypeOfNote(Note note)
	{
		if (note != null)
			return note.getType();
		return null;
	}
	
	/*
	 * Get duration of the note
	 */
	public BigDecimal	getDurationOfNote(Note note)
	{
		if (note != null)
			return note.getDuration();
		return null;
	}

	/*
	 * Staff permet de savoir si il y a une port� en plusieurs partie distinct qui est repr�senter par ce chiffre
	 * (En gros un ID, la port�e peut �tre divis� en deux pour la main droite et gauche)
	 * Peut �tre null si aucune port�e en couple
	 */
	public BigInteger	getStaffOfNote(Note note)
	{
		if (note != null)
			return note.getStaff();
		return null;
	}
	
	/*
	 * Pitch is represented as a combination of the step of the diatonic scale, 
	 * the chromatic alteration, and the octave.
	 * Peut �tre null
	 */
	public Pitch		getPitchOfNote(Note note)
	{
		if (note != null)
			return note.getPitch();
		return null;
	}
	
	public List<Key>			getKeyOfAttributes(Attributes attr)
	{
		return attr.getKey();
	}
	
	public List<Clef>			getClefOfAttributes(Attributes attr)
	{
		return attr.getClef();
	}
	
	/*
	 * 
	 * 
	 * 	A REVOIR
	 * 
	 * 
	 */
	/*
	 * Une liste des port�es en obj g�n�rique
	 */
	/*public List<Object>		getListScorePartOrGroupPart()
	{
		if (m_scorePartwise == null)
			return null;
		if (m_scorePartwise.getPartList() == null)
			return null;
		return m_scorePartwise.getPartList().getPartGroupOrScorePart();
	}*/
	
	/*
	 * Te dit si l'objet est un ScorePart
	 */
	/*public boolean			isScorePart(Object obj)
	{
		if (obj instanceof ScorePart)
			return true;
		return false;
	}*/
	
	/*
	 * Te dit si l'objet est un PartGroup
	 */
	/*public boolean			isPartGroup(Object obj)
	{
		if (obj instanceof PartGroup)
			return true;
		return false;
	}*/
}
