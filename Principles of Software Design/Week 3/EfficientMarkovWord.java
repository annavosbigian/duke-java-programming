import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<Integer, ArrayList<String>>();
            }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
    }
        
    public void buildMap(){
        //if WordGram not in map, it should be put in mapped to empty list
        //if key is in the hasmap, don't do anything
        System.out.println("entering buildMap");
        int start = 0;
        WordGram wg = new WordGram(myText, start, myOrder);
            while (start < myText.length-myOrder){
            int wgInt = wg.hashCode();
            if (map.containsKey(wgInt)){
                ArrayList<String> array = map.get(wgInt);
                String next = myText[start + myOrder];
                array.add(next);
                wg = wg.shiftAdd(next);
            }                
            else {
                ArrayList<String> newList = new ArrayList<String>();
                String next = myText[start + myOrder];
                newList.add(next);
                map.put(wgInt, newList);
                wg = wg.shiftAdd(next);
                }
            start++;
        }
        printHashMapInfo();
        System.out.println("leaving buildMap");
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        //start = where to start looking for the wordgram match in words
        //return first position from start with words matching target
        for (int i = start; i < words.length - target.length(); i ++){        
            WordGram wg = new WordGram(words, i, myOrder);
            if (wg.equals(target)){
                return i;
            } 
        }
        //System.out.println("returning -1");
        return -1;        
    }

    
    private ArrayList<String> getFollows(WordGram kGram) {
        //System.out.println("entering follows");
        int hashCode = kGram.hashCode();
        if (!map.containsKey(hashCode)){
            ArrayList<String> empty = new ArrayList<String>();            
            return empty;
        }
        return map.get(hashCode);
    } 
    
    public String getRandomText(int numWords){
        //System.out.println("getting random text");
        StringBuilder sb = new StringBuilder();
        //String[] keys = new String[myOrder];
        int index = myRandom.nextInt(myText.length-myOrder); 
        //System.out.println("index is " + index);        
        //add to the key
        /*for (int i = 0; i < myOrder; i++){
            keys[i] = myText[index + i];
            sb.append(keys[i]);
            System.out.println(keys[i]);
        }*/
        //for(int start = 0; start <= numWords - myOrder; start ++) {
        WordGram wg = new WordGram(myText, index, myOrder);
        sb.append(wg.toString() + " ");
        //System.out.println("first key is " + sb);
        for(int k=0; k < numWords; k++){
        //System.out.println(k);
        ArrayList<String> follows = getFollows(wg);
                if (follows.size() == 0) {
                    System.out.println("follows is 0");
                    break;
                }
                index = myRandom.nextInt(follows.size());
                String next = follows.get(index);
                //System.out.println(next);
                sb.append(next);
                sb.append(" ");
                //System.out.println("old " +wg);
                wg = wg.shiftAdd(next);
                //System.out.println("new " + wg);
            }        

        return sb.toString();
    }
    
    public void printHashMapInfo(){
        System.out.println("entering hash map info");
        //print map
        System.out.println(map);
        System.out.println(map.size());
        int largest = 0;
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for (ArrayList<String> al : map.values()){
            if (al.size() > largest){
                largest = al.size();
            }
        for ( int i : map.keySet()){
                if (map.get(i).size() == largest){
                keys.add(i);
            }
        }
    }
        System.out.println(largest);
        System.out.println(keys);
        System.out.println("leaving hash map info");
    }
}
