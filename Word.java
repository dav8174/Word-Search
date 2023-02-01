import java.util.*;

public class Word{
    String word;
    boolean visited;
    ArrayList<Word> nbs; //neighbors
    Word predecessor;
    int distFromSrc;

    public boolean isNeighbor(String other){
        if(this.word.equals(other) || this.word.length() != other.length())
            return false;
        int diff = 0; // different chars
        for(int i = 0; i < other.length(); i++) { 
            if(this.word.charAt(i) != other.charAt(i)) { 
                diff++;
                if(diff>1){
                    return false;
                }
            }
        }
        return true;
    }
    
    public ArrayList<String> fillNBS(ArrayList<Word> wordList){
        ArrayList<String> neighbors = new ArrayList<String>();
        for(int i = 0; i < wordList.size(); i++){
            if(isNeighbor(wordList.get(i).word))
                nbs.add(wordList.get(i));
        }
        return neighbors;
    }

    Word(String word){
        this.word = word;
        this.visited = false;
        this.nbs = new ArrayList<Word>();
        this.predecessor = null;
        this.distFromSrc = -1;
    }
}