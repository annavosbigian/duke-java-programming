package EarthquakeFilterStarterProgram;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        String source = "http://www.dukelearntoprogram.com/course4/data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*
        Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        Filter f = new MagnitudeFilter(4.0, 5.0);
        ArrayList<QuakeEntry> mf = filter(list, f);
        Filter f2 = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> df = filter(mf, f2);
        for (QuakeEntry qe : df){
            System.out.println(qe);
        }*/
        Filter phrase = new PhraseFilter("end", "Japan");
        QuakeEntry Tokyo = new QuakeEntry(35.42, 139.42, 1.0, 
	                 "Tokyo", 1.0);
        ArrayList<QuakeEntry> p = filter(list, phrase);
        Filter f = new DistanceFilter(10000, Tokyo);
        ArrayList<QuakeEntry> df = filter(p, f);
        for (QuakeEntry qe : df){
            System.out.println(qe);
    }
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
    //where = where to search in title (start, end, any)
    //phrase = phrase to search for
    ArrayList<QuakeEntry> phrases = new ArrayList<QuakeEntry>();
    for (QuakeEntry qe : quakeData){
    String title = qe.getInfo();
    System.out.println(title);
        if (phraseFilter(qe, where, phrase)){
                phrases.add(qe);
        }
    }
    return phrases;
    }
    public boolean phraseFilter(QuakeEntry qe, String where, String phrase){
    String title = qe.getInfo();
        if (where == "start"){
            if (title.startsWith(phrase)){
                return true;
            }
        }
        else if (where == "end"){
            if (title.endsWith(phrase)){
                return true;
            }
        }
        else if (where == "any"){
            if (title.contains(phrase)){
                return true;
            }           
        }
        return false;
    }
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        MatchAllFilter maf = new MatchAllFilter();        
        String source = "http://www.dukelearntoprogram.com/course4/data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        Filter pt = new PhraseFilter("any", "Ca");
        maf.addFilter(pt);
        Filter mag = new MagnitudeFilter(0.0, 3.0);
        maf.addFilter(mag);
        QuakeEntry Tulsa = new QuakeEntry(36.1312, -95.9372, 1.0, 
	                 "Tulsa", 1.0);        
        Filter df = new DistanceFilter(10000.0, Tulsa);
        maf.addFilter(df);
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        for (QuakeEntry qe : filtered){
            System.out.println(qe);              
        }   
    }
}
