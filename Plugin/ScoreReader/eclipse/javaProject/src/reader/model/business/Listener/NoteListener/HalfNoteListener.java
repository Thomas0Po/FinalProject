package reader.model.business.Listener.NoteListener;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by favre on 28/09/2014.
 */
public class HalfNoteListener implements INoteListener {
    public ImageView createNote() {
        ImageView imageView = new ImageView(
                new Image("image/half-note.png")
        );

        return imageView;
    }
}
