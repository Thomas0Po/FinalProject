package sample.model.business.Listener.ClefListener;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by favre on 28/09/2014.
 */

public class FClefListener implements IClefListener{

    public ImageView createClef(){
        System.out.println("ATTRIBUTES F");
        ImageView imageView = new ImageView(
                new Image("image/F-clef.png")
        );
        imageView.setStyle("-fx-translate-y: -17;");

        return imageView;
    }
}
