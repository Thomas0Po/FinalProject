package sample.view;

import com.audiveris.proxymusic.*;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import sample.model.business.factory.ClefFactory;
import sample.model.business.factory.NoteFactory;
import sample.model.business.factory.RestFactory;
import sample.model.business.factory.TimeFactory;
import sample.model.object.NoteOrAttribute;
import sample.model.object.SubPart;
import javafx.scene.image.*;
import sample.model.object._Measure;

/**
 * Created by favre on 14/09/2014.
 */
public class SubPartCell extends ListCell<SubPart> {

    HBox layout;

    @Override
    protected void updateItem(SubPart subPartView, boolean b) {
        super.updateItem(subPartView, b);

        if (subPartView != null)
        {
            this.setId("subSheet-custom");
            this.getStylesheets().add("css/background-music-sheet.css");

            this.layout = new HBox(subPartView.getMeasures().size());

            System.out.println("updateItem : subPart.Measures size : " + subPartView.getMeasures().size());


            for(_Measure measure: subPartView.getMeasures())
            {
                System.out.println("updateItem : subPart.Measures size : " + measure.getListNoteOrMeasure().size());
                for (NoteOrAttribute obj : measure.getListNoteOrMeasure())
                {
                    System.out.println(obj.getClass().getSimpleName());


                            if (obj.isAttribute())	{ this.addAttribute(obj.getAttribute(), subPartView);       }
                    else 	if (obj.isNote())       { this.addNote(obj.getNote());                              }
                }

                ImageView imageView = new ImageView(
                        new Image("image/vertical-line.png")
                );

                layout.getChildren().add(imageView);
            }

            layout.setAlignment(Pos.CENTER_LEFT);
            layout.setSpacing(10);
            this.setGraphic(layout);
        }
    }

    private void addAttribute(Attributes attr, SubPart subPartView)
    {
        ClefFactory clefFactory = ClefFactory.getInstance();
        TimeFactory timeFactory = TimeFactory.getInstance();

        System.out.println("ATTRIBUTES FOUND !!");

        //Create Clef if exist
        if (attr.getClef() != null && attr.getClef().size() > subPartView.getIndex())
            layout.getChildren().add(clefFactory.createClef(attr.getClef().get(subPartView.getIndex())));

        //Create Time if exist
        if (attr.getTime() != null && attr.getTime().size() > subPartView.getIndex())
         layout.getChildren().add(timeFactory.createTime(attr.getTime().get(subPartView.getIndex())));

    }

    private void addNote(Note note)
    {
        NoteFactory noteFactory = NoteFactory.getInstance();
        RestFactory restFactory = RestFactory.getInstance();

        //System.out.println("NOTE FOUND  : " + note.getType().getValue());

        if ((note.getRest() != null) && (note.getType() != null))
        {
            layout.getChildren().add(restFactory.createRest(note));
        }
        else if (note.getType() != null)
        {
            layout.getChildren().add(noteFactory.createNote(note));
        }
    }
}
