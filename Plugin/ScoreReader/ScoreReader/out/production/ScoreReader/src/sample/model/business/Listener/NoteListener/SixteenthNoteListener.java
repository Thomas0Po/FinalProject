package sample.model.business.Listener.NoteListener;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by favre on 28/09/2014.
 */
public class SixteenthNoteListener implements INoteListener {
    @Override
    public ImageView createNote() {
        ImageView imageView = new ImageView(
                new Image("image/sixteenth-note.png")
        );

        return imageView;
    }
}
