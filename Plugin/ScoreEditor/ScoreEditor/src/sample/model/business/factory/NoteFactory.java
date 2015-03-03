package sample.model.business.factory;

import com.audiveris.proxymusic.Note;
import javafx.scene.image.ImageView;
import sample.constant.NoteConstant;
import sample.model.business.ClefSession;
import sample.model.business.Listener.NoteListener.*;

import java.util.HashMap;

/**
 * Created by favre on 29/09/2014.
 */
public class NoteFactory {

    private HashMap<String, INoteListener>  map;
    private HashMap<String, Integer>        notes;


    ////////////////////////////////////////
    ////    Singleton
    private static class SingletonHolder {
        private final static NoteFactory instance = new NoteFactory();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static NoteFactory getInstance() {
        return SingletonHolder.instance;
    }
    ////////////////////////////////////////

    private NoteFactory()
    {
        this.map        = new HashMap<String, INoteListener>();
        this.notes      = new HashMap<String, Integer>();

        this.map.put("quarter",     new QuarterNoteListener());
        this.map.put("sixteenth",   new SixteenthNoteListener());
        this.map.put("whole",       new WholeNoteListener());
        this.map.put("eighth",      new EighthNoteListener());
        this.map.put("half",        new HalfNoteListener());
        this.map.put("16th",        new SixteenthNoteListener());
        this.notes.put("C", NoteConstant.C);
        this.notes.put("D", NoteConstant.D);
        this.notes.put("E", NoteConstant.E);
        this.notes.put("F", NoteConstant.F);
        this.notes.put("G", NoteConstant.G);
        this.notes.put("A", NoteConstant.A);
        this.notes.put("B", NoteConstant.B);




    }

    public ImageView createNote(Note note)
    {
        ClefSession clefSession = ClefSession.getInstance();
        Double      translateY  = 0.0;

        if (note.getPitch().getAlter() != null){
            translateY  = clefSession.getClefPosY().get(note.getPitch().getStep().value() +
                    String.valueOf(note.getPitch().getOctave() + note.getPitch().getAlter().intValue()));
        }
        else {

            translateY  = clefSession.getClefPosY().get(note.getPitch().getStep().value() + String.valueOf(note.getPitch().getOctave() - 1));
        }
        ImageView   imageView   = this.map.get(note.getType().getValue()).createNote();



        imageView.setStyle("-fx-translate-y: "+ translateY + ";");

        return imageView;
    }

}
