import java.util.*;
import edu.duke.*;
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;
    private HashMap<String, ArrayList<String>> map = new HashMap<>();
    
    public EfficientMarkovModel(int characters) {
        myRandom = new Random();
        N = characters;
    }

    public void makeString(){
    //print class number
    System.out.println("This is the EfficientMarkovModel class of "); //number);
    }
    
    public HashMap<String, ArrayList<String>> buildMap(){
    //if key isn't in hashmap, add it to empty ArrayList  
    //add the key
    //along with the arraylist
    //if !map.containsKey
    //map.put(key, value);
        for(int k=0; k < myText.length() - N; k++){
            String key = myText.substring(k, k+N);
            if (!map.containsKey(key)){
                ArrayList<String> follow = new ArrayList<String>();                               
                int i = 0;    
                while (i < myText.length()){
                    int start = myText.indexOf(key, i);
                        if (start == -1){
                            break;
                        }
                        String next = myText.substring(start+key.length(), start+key.length()+1);
                        follow.add(next);
                        i = myText.indexOf(next, start);
                    }
                if (follow.size() == 0){
                        break;
                    }
                else {
                         map.put(key, follow);
                    }   
                }
            }
    System.out.println(map);
    return map;
    }
    protected ArrayList<String> getFollows(String key){
        return map.get(key);
    }    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s;
    }

    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        System.out.println(key);
        sb.append(key);
        //System.out.println(map);
        for (int i = 0; i < numChars; i++){
            ArrayList<String> array = getFollows(key);
            index = myRandom.nextInt(array.size());
            String next = array.get(index);
            sb.append(next);            
            key = key.substring(1)+next;              
        }
        map.clear();
        return sb.toString();
        }
        
    public void printHashMapInfo(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        HashMap<String, ArrayList<String>> map = buildMap();
        for (String s : map.keySet()){
            System.out.println(s);
            System.out.println(map.get(s));
        }
    }
        }
