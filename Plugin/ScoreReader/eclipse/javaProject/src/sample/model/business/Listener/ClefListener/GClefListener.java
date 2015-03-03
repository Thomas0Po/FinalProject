package sample.model.business.Listener.ClefListener;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by favre on 28/09/2014.
 */
public class GClefListener implements IClefListener{

    public ImageView createClef(){
        System.out.println("ATTRIBUTES G");
        ImageView imageView = new ImageView(
                new Image("image/G-clef.png")
        );
        imageView.setStyle("-fx-translate-y: 3;");

        return imageView;
    }
}
