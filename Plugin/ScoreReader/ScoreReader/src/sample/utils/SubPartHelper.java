package sample.utils;

import sample.model.object.SubPart;

import java.util.ArrayList;

/**
 * Created by favre on 28/09/2014.
 */
public class SubPartHelper {

    public static ArrayList<SubPart> buildArray(int nb){

        ArrayList<SubPart> array = new ArrayList<SubPart>();

        for(int k = 0; k < nb; ++k)
            array.add(new SubPart(k));

        return array;
    }
}
