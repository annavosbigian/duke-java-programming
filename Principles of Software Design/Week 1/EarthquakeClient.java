import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            double mag = qe.getMagnitude();
            if (mag > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData){
        //add to answer if loc is less than distMax
        Location loc = qe.getLocation();
        if (loc.distanceTo(from) < distMax){
            answer.add(qe);
        }        
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        double size = 5.0;
        ArrayList<QuakeEntry> filtered = filterByMagnitude(list, size);
        System.out.println("read data for "+list.size()+" quakes");
        for (QuakeEntry qe: filtered){
            System.out.println(qe);
        }
        System.out.println("Found " + filtered.size() + "quakes matching criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list, 1000, city);
        for (QuakeEntry qe : closeQuakes){
        Location loc = qe.getLocation();
            System.out.println(loc.distanceTo(city) + "of" + qe.getInfo());
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        //return quakes between min & max depth
        ArrayList<QuakeEntry> depthList = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData){
            double depth = qe.getDepth();
            if (depth > minDepth && depth < maxDepth){
                depthList.add(qe);
            }
        }
        return depthList;
    }
    public void quakesofDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //URLResource fr = new URLResource("http://www.dukelearntoprogram.com/course4/data/nov20quakedatasmall.atom");
        String source = "http://www.dukelearntoprogram.com/course4/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        ArrayList<QuakeEntry> filteredList = filterByDepth(list, -10000.0, -5000.0);
        for (QuakeEntry qe : filteredList){        
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
    //where = where to search in title (start, end, any)
    //phrase = phrase to search for
    ArrayList<QuakeEntry> phrases = new ArrayList<QuakeEntry>();
    for (QuakeEntry qe : quakeData){
    String title = qe.getInfo();
    System.out.println(title);
        if (where == "start"){
            if (title.startsWith(phrase)){
                phrases.add(qe);
            }
        }
        else if (where == "end"){
            if (title.endsWith(phrase)){
                phrases.add(qe);
            }
        }
        else if (where == "any"){
            if (title.contains(phrase)){
                phrases.add(qe);
            }           
        }
    }
    return phrases;
    }
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://www.dukelearntoprogram.com/course4/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());   
        ArrayList<QuakeEntry> phrases = filterByPhrase(list, "any", "Can");        
        System.out.println("Number with California:"+phrases.size());   
        for (QuakeEntry qe : phrases){        
            System.out.println(qe);
        }
    }
}
