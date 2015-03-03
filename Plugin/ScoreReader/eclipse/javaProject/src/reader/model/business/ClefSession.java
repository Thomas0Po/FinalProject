package reader.model.business;

import com.audiveris.proxymusic.Clef;

import reader.constant.NoteConstant;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by favre on 30/09/2014.
 */
public class ClefSession {

    private HashMap<Integer, Double> map;
    private Clef                    clef;

    ////////////////////////////////////////
    ////    Singleton
    private static class SingletonHolder {
        private final static ClefSession instance = new ClefSession();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static ClefSession getInstance() {
        return SingletonHolder.instance;
    }
    ////////////////////////////////////////


    private ClefSession(){

        this.map = new HashMap<Integer, Double>();

        this.map.put(1, NoteConstant.FIRST_LINE);
        this.map.put(2, NoteConstant.SECOND_LINE);
        this.map.put(3, NoteConstant.THIRD_LINE);
        this.map.put(4, NoteConstant.FOURTH_LINE);
        this.map.put(5, NoteConstant.FIFTH_LINE);
    }

    public void setClef(Clef clef){

        this.clef = clef;
    }


    public Clef getClef(){

        return this.clef;
    }

    public HashMap<String, Double> getClefPosY(){

             if (this.clef.getSign().toString().equals("G"))    return Session.getInstance().getGnotesPosY();
        else if (this.clef.getSign().toString().equals("F"))    return Session.getInstance().getFnotesPosY();
        else                                                    return null;
    }
}
