package sample.model.business;

import sample.constant.FConstant;
import sample.constant.GConstant;

import java.util.HashMap;

/**
 * Created by favre on 30/09/2014.
 */
public class Session {


    private HashMap<String, Double> GnotesPosY;
    private HashMap<String, Double> FnotesPosY;

    ////////////////////////////////////////
    ////    Singleton
    private static class SingletonHolder {
        private final static Session instance = new Session();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static Session getInstance() {
        return SingletonHolder.instance;
    }
    ////////////////////////////////////////

    public Session(){

        this.GnotesPosY  = new HashMap<String, Double>();
        this.FnotesPosY  = new HashMap<String, Double>();

        this.GnotesPosY.put("C3", GConstant.C3_POS);
        this.GnotesPosY.put("D3", GConstant.D3_POS);
        this.GnotesPosY.put("E3", GConstant.D3_POS);
        this.GnotesPosY.put("F3", GConstant.F3_POS);
        this.GnotesPosY.put("G3", GConstant.G3_POS);
        this.GnotesPosY.put("A3", GConstant.A3_POS);
        this.GnotesPosY.put("B3", GConstant.B3_POS);
        this.GnotesPosY.put("C4", GConstant.C4_POS);
        this.GnotesPosY.put("D4", GConstant.D4_POS);
        this.GnotesPosY.put("E4", GConstant.E4_POS);
        this.GnotesPosY.put("F4", GConstant.F4_POS);
        this.GnotesPosY.put("G4", GConstant.G4_POS);
        this.GnotesPosY.put("A4", GConstant.A4_POS);
        this.GnotesPosY.put("B4", GConstant.B4_POS);
        this.GnotesPosY.put("C5", GConstant.C5_POS);

        this.FnotesPosY.put("C1", FConstant.C1_POS);
        this.FnotesPosY.put("D1", FConstant.D1_POS);
        this.FnotesPosY.put("E1", FConstant.E1_POS);
        this.FnotesPosY.put("F1", FConstant.F1_POS);
        this.FnotesPosY.put("G1", FConstant.G1_POS);
        this.FnotesPosY.put("A1", FConstant.A1_POS);
        this.FnotesPosY.put("B1", FConstant.B1_POS);
        this.FnotesPosY.put("C2", FConstant.C2_POS);
        this.FnotesPosY.put("D2", FConstant.D2_POS);
        this.FnotesPosY.put("E2", FConstant.E2_POS);
        this.FnotesPosY.put("F2", FConstant.F2_POS);
        this.FnotesPosY.put("G2", FConstant.G2_POS);
        this.FnotesPosY.put("A2", FConstant.A2_POS);
        this.FnotesPosY.put("B2", FConstant.B2_POS);
        this.FnotesPosY.put("C3", FConstant.C3_POS);

    }

    public HashMap<String, Double> getGnotesPosY() {
        return GnotesPosY;
    }

    public HashMap<String, Double> getFnotesPosY() {
        return FnotesPosY;
    }
}
