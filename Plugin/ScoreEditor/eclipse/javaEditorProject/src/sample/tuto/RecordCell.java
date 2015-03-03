package sample.tuto;

import javafx.scene.control.ListCell;

/**
 * Created by favre on 11/09/2014.
 */
public class RecordCell extends ListCell<Record> {

    //AnchorPane anchorPane;

    @Override
    protected void updateItem(Record item, boolean b) {
        super.updateItem(item, b);

        if (item != null)
        {
            setText(item.getName());
            setGraphic(null);

            getStylesheets().add("css/background-music-sheet.css");
        }
        /*else
        {
            setText("No Name");
            setGraphic(null);
        }*/
       /* if (item != null) {
            if (item instanceof String) {
                setText((String) item);
                setGraphic(null);
            } else if (item instanceof Integer) {
                setText(Integer.toString((Integer) item));
                setGraphic(null);
            } else if (item instanceof Boolean) {
                CheckBox checkBox = new CheckBox();
                checkBox.setSelected((boolean) item);
                setGraphic(checkBox);
            } else if (item instanceof Image) {
                setText(null);
                ImageView imageView = new ImageView((Image) item);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                setGraphic(imageView);
            } else {
                setText("N/A");
                setGraphic(null);
            }
        } else {
            setText(null);
            setGraphic(null);
        }
        */
    }
}
