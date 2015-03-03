package reader.model.business.factory;

import com.audiveris.proxymusic.Time;

import javafx.scene.layout.VBox;
import reader.model.business.Listener.TimeListener.TimeListener;

import javax.xml.bind.JAXBElement;

import java.util.HashMap;

/**
 * Created by favre on 28/09/2014.
 */
public class TimeFactory {

    private HashMap<Integer, String>    map;
    private TimeListener                listener;

    ////////////////////////////////////////
    ////    Singleton
    private static class SingletonHolder {
        private final static TimeFactory instance = new TimeFactory();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static TimeFactory getInstance() {
        return SingletonHolder.instance;
    }
    ////////////////////////////////////////

    private TimeFactory()
    {
        this.map        = new HashMap<Integer, String>();
        this.listener   = new TimeListener();


        this.map.put(1, "image/one.png");
        this.map.put(2, "image/two.png");
        this.map.put(3, "image/three.png");
        this.map.put(4, "image/four.png");
        this.map.put(5, "image/five.png");
        this.map.put(6, "image/six.png");
        this.map.put(8, "image/eight.png");
        this.map.put(9, "image/nine.png");
        this.map.put(12, "image/twelve.png");
    }

    public VBox createTime(Time time)
    {
        java.lang.String beats      = null;
        java.lang.String beatType   = null;

        for(JAXBElement<String> elem : time.getTimeSignature())
        {
                    if(elem.getName().getLocalPart().equals("beats"))       beats       = elem.getValue();
            else    if(elem.getName().getLocalPart().equals("beat-type"))   beatType    = elem.getValue();
        }

        return this.listener.createTime(
                this.map.get(Integer.valueOf(beats)),
                this.map.get(Integer.valueOf(beatType)));
    }

}
