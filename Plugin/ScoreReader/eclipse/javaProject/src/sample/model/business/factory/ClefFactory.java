package sample.model.business.factory;

import com.audiveris.proxymusic.Clef;
import javafx.scene.image.ImageView;
import sample.model.business.ClefSession;
import sample.model.business.Listener.ClefListener.FClefListener;
import sample.model.business.Listener.ClefListener.GClefListener;
import sample.model.business.Listener.ClefListener.IClefListener;

import java.util.HashMap;

/**
 * Created by favre on 28/09/2014.
 */
public class ClefFactory {

    private HashMap<String, IClefListener> map;

    ////////////////////////////////////////
    ////    Singleton
    private static class SingletonHolder {
        private final static ClefFactory instance = new ClefFactory();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static ClefFactory getInstance() {
        return SingletonHolder.instance;
    }
    ////////////////////////////////////////

    private ClefFactory()
    {
        this.map = new HashMap<String, IClefListener>();

        map.put("G", new GClefListener());
        map.put("F", new FClefListener());
    }

    public ImageView createClef(Clef clef)
    {
        ClefSession clefSession = ClefSession.getInstance();
        clefSession.setClef(clef);

        return this.map.get(clef.getSign().toString()).createClef();
    }


}
