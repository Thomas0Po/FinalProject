package sample.utils;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.audiveris.proxymusic.*;
import com.audiveris.proxymusic.ScorePartwise.Part;
import com.audiveris.proxymusic.ScorePartwise.Part.Measure;

/**
 * Created by favre on 23/09/2014.
 */
public class ScorePartwiseHelper {

    //-----------------//
    // displayAttributes //
    //-----------------//
    public static void displayAttributes (Attributes attr)
    {
        System.out.println("Attributes divisions : " 	+ attr.getDivisions());
        System.out.println("Attributes key size : " 	+ attr.getKey().size());

        for(Key key : attr.getKey())
            System.out.println("Attributes key fifths : " + key.getFifths());

        System.out.println("Attributes time size : " + attr.getTime().size());

        for(Time time : attr.getTime())
        {
            for (JAXBElement<java.lang.String> elem : time.getTimeSignature())
            {
                java.lang.String name = elem.getName().getLocalPart();

                if (name.equals("beats")) 		{ System.out.println("Attributes Time beats : " 	+ elem.getValue());							}
                else if (name.equals("beat-type")) 	{ System.out.println("Attributes Time beatType : " 	+ elem.getValue()); 						}
                else 								{ System.out.println("Unexpected attribute " + "name=" + name + " value=" + elem.getValue());	}
            }
        }

        System.out.println("Attributes clef size : " + attr.getClef().size());

        for(Clef clef : attr.getClef())
        {
            System.out.println("Attributes Clef Sign : " + clef.getSign());
            System.out.println("Attributes Clef Line : " + clef.getLine());
        }
    }

    //--------------//
    // displayMeasure //
    //--------------//
    public static void displayMeasure (Measure measure)
    {
        System.out.println("Measure number : " + measure.getNumber());
        System.out.println("Measure size : " + measure.getNoteOrBackupOrForward().size());

        for (Object obj : measure.getNoteOrBackupOrForward())
        {
            System.out.println(obj.getClass().getSimpleName());
            if(obj.getClass().getSimpleName().equals("Attributes"))	{ displayAttributes((Attributes) obj); 	}
            else 	if (obj.getClass().getSimpleName().equals("Note"))		{ displayNote((Note) obj);				}
        }
    }

    //-----------//
    // displayNote //
    //-----------//
    public static void displayNote (Note note)
    {
        Pitch 		pitch 		= note.getPitch();
        BigDecimal 	duration 	= note.getDuration();
        NoteType 	type 		= note.getType();

        System.out.println("Note Pitch Step : " 	+  ((pitch != null) ? pitch.getStep() 	: ""));
        System.out.println("Note Pitch Octave : " 	+  ((pitch != null) ? pitch.getOctave() : ""));
        System.out.println("Note Type : " 			+  ((type != null) 	? type.getValue() 	: ""));
        System.out.println("Note duration : " 		+  duration);

    }

    //-----------//
    // displayPart //
    //-----------//
    public static void displayPart (Part part)
    {
        Object 			id 			= part.getId();
        List<Measure> 	measures 	= part.getMeasure();

        System.out.println("Part measures size : " + measures.size());

        for(Measure measure : measures)
        {
            System.out.println("=======================");
            displayMeasure(measure);
        }
    }

    //---------------//
    // displayPartList //
    //---------------//
    public static void displayPartList (PartList partList)
    {
        for(Object obj : partList.getPartGroupOrScorePart())
        {
            System.out.println("=======================");
            displayScorePart((ScorePart) obj);
        }
    }

    //----------------//
    // displayScorePart //
    //----------------//
    public static void displayScorePart (ScorePart scorePart)
    {
        System.out.println("ScorePart id : " 	+ scorePart.getId());
        System.out.println("ScorePart name : " 	+ scorePart.getPartName().getValue());
    }

    //--------------------//
    // displayScorePartwise //
    //--------------------//
    public static void displayScorePartwise (ScorePartwise scr)
    {
        System.out.println("ScorePartwise version : " + scr.getVersion());

        displayPartList(scr.getPartList());

        List<ScorePartwise.Part> parts = scr.getPart();

        System.out.println("ScorePartwise Part size : " + parts.size());

        for(com.audiveris.proxymusic.ScorePartwise.Part part : parts)
        {
            displayPart(part);
        }
    }
}
