package EarthquakeFilterStarterProgram;


/**
 * Write a description of interface Filter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe); 
    //promises this method will exist in all class implementing Filter
    //when using a class implementing Filter, you must define promised methods
    public String getName();    
}
