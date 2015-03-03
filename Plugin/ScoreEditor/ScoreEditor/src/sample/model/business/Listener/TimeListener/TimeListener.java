package sample.model.business.Listener.TimeListener;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Created by favre on 28/09/2014.
 */
public class TimeListener implements ITimeListener {

    @Override
    public VBox createTime(String beats, String beatType) {
        VBox vBox = new VBox(2);

        ImageView imageViewBeats = new ImageView(
                new Image(beats)
        );

        ImageView imageViewBeatType = new ImageView(
                new Image(beatType)
        );

        vBox.getChildren().add(imageViewBeats);
        vBox.getChildren().add(imageViewBeatType);

        vBox.setStyle("-fx-translate-y: 20;");

        return vBox;
    }
}
