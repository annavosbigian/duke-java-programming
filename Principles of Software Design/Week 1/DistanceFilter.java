package EarthquakeFilterStarterProgram;


/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private double maximum;
    private QuakeEntry location;
    private String name = "DistanceFilter";
    public DistanceFilter(double max, QuakeEntry loc){
        maximum = max;
        location = loc;
    }
    public boolean satisfies(QuakeEntry qe){
        return qe.compareTo(location) <= maximum;       
    }
    public String getName(){
        return name;        
    }    
}
