import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void findURL() {
        URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
            for (String item : file.words()) {
            String itemLower = item.toLowerCase();
                int link = itemLower.indexOf("youtube.com"); //check to see if "youtube.com" is in it
                if (link != -1) {
                    int beg = item.lastIndexOf("\"", link); //find double quote from begin to end
                    int end = item.indexOf("\"", link+1);
                    System.out.println(item.substring((beg+1),end));
                }
        }
        }
}
