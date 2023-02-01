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
        Queue<Word> toVisit = new LinkedList<Word>();
        toVisit.add(this.start);
        while(!toVisit.isEmpty()){
            Word current = toVisit.remove();
            current.visited = true;
            for(int i = 0; i < current.nbs.size(); i++){
                if(!current.nbs.get(i).visited){
                    toVisit.add(current.nbs.get(i));
                    current.nbs.get(i).distFromSrc = current.distFromSrc + 1;
                }
            }
        }
        if(target.distFromSrc != -1)
        makePath(target);
    }

    public void makePath(Word currentWord){
        path.add(0,currentWord);
        if(!currentWord.equals(this.start)){
            Word nextWord = currentWord.nbs.get(0);
            for(int i = 1; i < currentWord.nbs.size(); i++){
                if(currentWord.nbs.get(i).distFromSrc < nextWord.distFromSrc){
                    nextWord = currentWord.nbs.get(i);
                }
            }
            makePath(nextWord);
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