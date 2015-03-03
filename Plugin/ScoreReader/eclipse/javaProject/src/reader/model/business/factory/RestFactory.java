package reader.model.business.factory;

import com.audiveris.proxymusic.Note;

import javafx.scene.image.ImageView;
import reader.model.business.Listener.RestListener.*;

import java.util.HashMap;

/**
 * Created by favre on 29/09/2014.
 */
public class RestFactory {

    private HashMap<String, IRestListener> map;

    ////////////////////////////////////////
    ////    Singleton
    private static class SingletonHolder {
        private final static RestFactory instance = new RestFactory();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static RestFactory getInstance() {
        return SingletonHolder.instance;
    }
    ////////////////////////////////////////

    private RestFactory()
    {
        this.map        = new HashMap<String, IRestListener>();

        this.map.put("quarter",     new QuarterRestListener());
        this.map.put("sixteenth",   new SixteenthRestListener());
        this.map.put("whole",       new WholeRestListener());
        this.map.put("eighth",      new EighthRestListener());
        this.map.put("half",        new HalfRestListener());
        this.map.put("16th", new SixteenthRestListener());

    }

    public ImageView createRest(Note note)
    {
        System.out.println("Note Type : " + note.getType().getValue());
        if (note.getRest().getMeasure() != null &&
            note.getRest().getMeasure().value().equals("yes"))  return this.map.get("whole").createRest();
        else                                                    return this.map.get(note.getType().getValue()).createRest();
    }
}
