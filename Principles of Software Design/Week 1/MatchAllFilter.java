package EarthquakeFilterStarterProgram;
import java.util.*;

/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filter;
    public MatchAllFilter(){
        filter = new ArrayList<Filter>();
    }
    public void addFilter(Filter f){
    //add f to arraylist
    filter.add(f);
    }
    public boolean satisfies(QuakeEntry parameter){
     //true if param satisfies all filter coniditions        
    for(Filter f : filter){
        if (f.satisfies(parameter) == false){
            return false;
        }
        }
    return true;
    }
    
    @Override
    public String getName(){        
        StringBuilder names = new StringBuilder();
        for (Filter f : filter){
            System.out.println(f.getName());
            names.append(f.getName() + " ");
        }
        return names.toString();
    }
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        MatchAllFilter maf = new MatchAllFilter();        
        EarthQuakeClient2 eq2 = new EarthQuakeClient2();
        String source = "http://www.dukelearntoprogram.com/course4/data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Filter pt = new PhraseFilter("any", "a");
        maf.addFilter(pt);
        Filter mag = new MagnitudeFilter(0.0, 2.0);
        maf.addFilter(mag);
        Filter df = new DepthFilter(-100000.0, -10000.0);
        maf.addFilter(df);
        ArrayList<QuakeEntry> filtered = eq2.filter(list, maf);
        for (QuakeEntry qe : filtered){
            System.out.println(qe);              
        }
        System.out.println(maf.getName());
        System.out.println(mag.getName());
    }
}
