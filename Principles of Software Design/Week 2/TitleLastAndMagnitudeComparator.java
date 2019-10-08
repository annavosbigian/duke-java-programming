import java.util.*;
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String word1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ") + 1);
        String word2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ") + 1); 
        int i = word1.compareTo(word2);
        if (i > 0){
            return 1;
        }
        else if (i < 0) {
            return -1;
        }
        else {
            i = Double.compare(q1.getMagnitude(), q2.getMagnitude());
    }
    return i;
}
}
