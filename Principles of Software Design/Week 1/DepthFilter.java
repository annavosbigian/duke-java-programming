package EarthquakeFilterStarterProgram;


/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double minimum;
    private double maximum;
    private String name = "DepthFilter";
    public DepthFilter(double min, double max){
        minimum = min;
        maximum = max;
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() >= minimum && qe.getDepth() <= maximum;       
    }
    public String getName(){
        return name;        
    }
}
