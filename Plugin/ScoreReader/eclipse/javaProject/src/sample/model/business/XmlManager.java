package sample.model.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.audiveris.proxymusic.ScorePartwise;
import com.audiveris.proxymusic.util.Marshalling;

public class XmlManager {

	public XmlManager() {}

	public ScorePartwise	xmlReader(String file)
			throws Exception
	{
		File		xmlFile = new File(file);
		InputStream	is = new FileInputStream(xmlFile);
		
		ScorePartwise scorePartwise = (ScorePartwise) Marshalling.unmarshal(is);
		is.close();
		return scorePartwise;
	}	
}
