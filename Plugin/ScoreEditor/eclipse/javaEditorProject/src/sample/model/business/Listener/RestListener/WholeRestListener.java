package sample.model.business.Listener.RestListener;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by favre on 28/09/2014.
 */
public class WholeRestListener implements IRestListener {
    public ImageView createRest() {
        ImageView imageView = new ImageView(
                new Image("image/whole-rest.png")
        );

        return imageView;
    }
}
