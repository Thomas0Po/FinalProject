package sample.model.object;

import com.audiveris.proxymusic.ScorePartwise.Part;
import com.audiveris.proxymusic.ScorePartwise.Part.Measure;

import java.util.ArrayList;
import java.util.List;

public class _Part {

	/*
	 * Portee de proxymusic
	 */
	private Part			m_Part = null;
	/*
	 * Liste de mesure
	 */
	private List<_Measure>	m_ListMeasure = null;
	/*
	 * Nombre de mesure dans la portee
	 */
	private Integer			m_NbMeasure = null;
	/*
	 * Est-ce que cette portee est en couple
	 */
	private boolean			m_IsCouple = false;
	/*
	 * Measure a null si pas couple
	 */
	private List<_Measure>	m_ListMeasureIfCouple = null;
	/*
	 *  BigInteger for CoupleID
	 */
	private Integer		m_CoupleIdUp = null;
	private Integer		m_CoupleIdDown = null;
	
	_Part() {}
	_Part(Part part) {
		if (part != null) {
			this.m_Part = part;
			m_CoupleIdUp = new Integer(-1);
			m_CoupleIdDown = new Integer(-2);
			constructPart();			
		}
	}

	public Part				getPart() { return this.m_Part; }
	public List<_Measure>	getListMeasure() { return this.m_ListMeasure; }
	public List<_Measure>	getListMeasureIfCouple() { return this.m_ListMeasureIfCouple; }
	public _Measure getMeasure(int index) {
		if (index < this.m_ListMeasure.size() && index >= 0)
			return this.m_ListMeasure.get(index);
		return null;
	}
	public Integer			getNbMeasure() { return this.m_NbMeasure; }
	public Boolean			isCouple() { return this.m_IsCouple; }
	
	public void				setPart(Part newPart) {
		if (newPart != null) {
			this.m_Part = newPart;
			constructPart();
		}
	}
	
	private void constructPart() {
		List<Measure> listMeasure = this.m_Part.getMeasure();
		this.m_ListMeasure = new ArrayList<_Measure>();
		this.m_ListMeasureIfCouple = new ArrayList<_Measure>();
		
		for (int i = 0; i < listMeasure.size(); ++i)
		{
			_Measure newMeasure = new _Measure();
			m_CoupleIdUp = newMeasure.setMeasure(listMeasure.get(i), m_CoupleIdUp, m_CoupleIdDown);
			this.m_ListMeasure.add(newMeasure);
			if (this.m_ListMeasure.get(i).isCouple()) {
				this.m_IsCouple = true;
				_Measure newCoupleMeasure = new _Measure();
				m_CoupleIdDown = newCoupleMeasure.setMeasure(listMeasure.get(i), m_CoupleIdDown, m_CoupleIdUp);
				this.m_ListMeasureIfCouple.add(newCoupleMeasure);
			}
		}
		this.m_NbMeasure = new Integer(this.m_ListMeasure.size());
	}
	
}
