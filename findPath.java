import java.util.*;

public class findPath{
    Word start;
    Word target;
    ArrayList<Word> wordList;
    int distance;
    ArrayList<Word> path;

    findPath(Word start, Word target, ArrayList<Word> wordList){
        this.start = start;
        this.target = target;
        this.wordList = wordList;
        this.distance = -1;
        this.path = new ArrayList<Word>();
        
        search();
    }

    public void search(){
        ArrayList<Word> toVisit = new ArrayList<Word>();
        toVisit.add(this.start);
        while(!toVisit.isEmpty()){
            Word current = pickNext(toVisit);
            current.visited = true;
            for(int i = 0; i < toVisit.size(); i++){
                if(current.equals(toVisit.get(i)))
                    toVisit.remove(i);
            }
            if(current.word.equals(this.target.word)){
                distance = 0;
                makePath(current);
                break;              
            }
            else{
                for(int i = 0; i < current.nbs.size(); i++){
                    if(!current.nbs.get(i).visited){
                        toVisit.add(current.nbs.get(i));
                        current.nbs.get(i).predecessor = current;
                    }
                }
            }
        }
    }

    public void makePath(Word lastWord){
        path.add(0,lastWord);
        if(lastWord.predecessor != null){
            this.distance++;
            makePath(lastWord.predecessor);
        }
    }

    public Word pickNext(ArrayList<Word> list){
        Word mostSimilar = list.get(0);
        for(int i = 1; i < list.size(); i++){
            String newWord = list.get(i).word;
            String currentMatch = mostSimilar.word;
            for(int j = 0; j < newWord.length(); j++){
                if(newWord.charAt(j) == target.word.charAt(j) && currentMatch.charAt(j) != target.word.charAt(j)){
                    mostSimilar = list.get(i);
                    break;
                }
            }
        }
        return mostSimilar;
    }
    
}