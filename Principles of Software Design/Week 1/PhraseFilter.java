package EarthquakeFilterStarterProgram;


/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String request; //where to search: start, end any
    private String phrase; //to search for in title of quake
    private String name = "PhraseFilter";
    public PhraseFilter(String req, String phr){
        request = req;
        phrase = phr;
    }
    
    public boolean satisfies(QuakeEntry qe){
        EarthQuakeClient2 eq2 = new EarthQuakeClient2();
        return eq2.phraseFilter(qe, request, phrase);
    }
    
    public String getName(){
        return name;        
    }
}
