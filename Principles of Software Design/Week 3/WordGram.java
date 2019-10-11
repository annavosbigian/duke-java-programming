
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        StringBuilder sb = new StringBuilder();
        for (String s : myWords){
        sb.append(s + " ");   
        }
        ret = sb.toString();
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()){
            return false;
        }
        for (int i = 0; i < myWords.length; i++){
            if (!myWords[i].equals(other.wordAt(i))){
                return false;
            }
        //x.wordAt(i).equals(y.wordAt(i));
        }
    return true;
    }
    //compare to
    public WordGram shiftAdd(String word) { 
        WordGram in = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        for (int i = 0; i < in.length(); i++){
            if (i + 1 == in.length()){
                myWords[i] = word;
            }
            else {
            myWords[i] = myWords[i + 1];    
            }

        }
        WordGram out = new WordGram(myWords, 0, myWords.length);
        return out;
    }

    public int hashCode(){
        //return int that is a hash code repping the WordGram
        //use hashCode method in String
        String wgString = this.toString();
        System.out.println(wgString);
        System.out.println(wgString.hashCode());
        return wgString.hashCode();
    }
}
