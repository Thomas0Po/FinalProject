package sample.view;

import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import sample.model.object.SubPart;

import java.util.List;

/**
 * Created by favre on 13/09/2014.
 */
public class ScoreCell extends ListCell<List<SubPart>> {

    private int partByCell;

    public ScoreCell()
    {

    }

    public ScoreCell(int partByCell)
    {
        this.partByCell     = partByCell;
    }

    @Override
    protected void updateItem(List<SubPart>item, boolean b) {
        super.updateItem(item, b);

        if (item != null)
        {
            ListView<SubPart> subPartView = new ListView<SubPart>();

            subPartView.setItems(FXCollections.observableList(item));

            subPartView.setCellFactory(new Callback<ListView<SubPart>, ListCell<SubPart>>() {
                @Override
                public ListCell<SubPart> call(ListView<SubPart> subPartViewListView) {

                    final SubPartCell subPartCell = new SubPartCell();

                    return subPartCell;
                }
            });

            setText(null);
            setGraphic(subPartView);

            //for(int i = 0; i < item.size(); ++i)
            setStyle("-fx-cell-size: " + (100 * (item.size() + 1)));

        }
    }
}
