package reader.model.business;

import javafx.stage.Stage;

/**
 * Created by favre on 15/10/2014.
 */
public class StageManager {

    ////////////////////////////////////////
    ////    Singleton
    private static class SingletonHolder {
        private final static StageManager instance = new StageManager();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static StageManager getInstance() {
        return SingletonHolder.instance;
    }
    ////////////////////////////////////////

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
