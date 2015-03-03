package sample.model.object;

import java.util.ArrayList;
import java.util.List;

import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.ScorePartwise.Part;

public class Score {
	/*
	 * ScorePartwise de proxymusic
	 */
	private ScorePartwise	m_scorePartwise = null;
	/*
	 * Liste de portee
	 */
	private List<_Part>		m_ListPart = null;
	/*
	 * Nombre de portee
	 */
	private Integer			m_nbPart = null;
	/*
	 * Nombre de portee en couple (un couple de portee est une portee double)
	 */
	private Integer			m_NbPartCouple = null;
	
	public Score() {}
	public Score(ScorePartwise scorePartwise) {
		if (scorePartwise != null) {
			this.m_scorePartwise = scorePartwise;
			this.constructScore();
		}
	}
	
	public Integer			getNbPart() { return this.m_nbPart; }
	public ScorePartwise	getScorePartwise() { return this.m_scorePartwise; }
	public List<_Part>		getListPart() { return this.m_ListPart; }
	public _Part			getPart(int index) {
		if (index < this.m_ListPart.size() && index >= 0)
			return this.m_ListPart.get(index);
		return null;
	}
	public Integer			getNbPartCouple() { return this.m_NbPartCouple; }
	
	public void setScorePartwise(ScorePartwise newScorePartwise) {
		if (newScorePartwise != null) {
			this.m_scorePartwise = newScorePartwise;
			this.constructScore();
		}
	}
	
	private void constructScore()
	{
		List<Part>	partList = this.m_scorePartwise.getPart();
		this.m_ListPart = new ArrayList<_Part>();
		m_NbPartCouple = new Integer(0);
		
		for (int i = 0; i < partList.size(); ++i)
		{
			this.m_ListPart.add(new _Part(partList.get(i)));
			if (this.m_ListPart.get(i).isCouple()) {
				++m_NbPartCouple;
			}
		}
		this.m_nbPart = new Integer(this.m_ListPart.size());
	}
}
