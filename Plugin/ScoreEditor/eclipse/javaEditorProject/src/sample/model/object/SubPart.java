package sample.model.object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by favre on 13/09/2014.
 */
public class SubPart
{
    protected List<_Measure>  measures = new ArrayList<_Measure>();
    protected int index;

    public SubPart(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<_Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<_Measure> measures) {
        this.measures = measures;
    }
}
