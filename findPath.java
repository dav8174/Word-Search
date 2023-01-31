import java.util.*;

public class findPath{
    Word start;
    Word target;
    ArrayList<Word> wordList;
    int distance;
    ArrayList<Word> path;
    Word lastWord;

    findPath(Word start, Word target, ArrayList<Word> wordList){
        this.start = start;
        this.target = target;
        this.wordList = wordList;
        this.distance = -1;
        this.path = new ArrayList<Word>();
        this.lastWord = null;
        search();
    }

    public void search(){
        ArrayList<Word> toVisit = new ArrayList<Word>();
        toVisit.add(this.start);
        while(!toVisit.isEmpty()){
            Word current = pickNext(toVisit);
            current.visited = true;
            toVisit.remove(current);
            if(current.word.equals(this.target.word)){
                this.distance = 0;
                this.lastWord = current;
                break;
            }
            else{
                for(int i = 0; i < current.nbs.size(); i++){
                    if(!current.nbs.get(i).visited)
                        toVisit.add(current.nbs.get(i));
                }
            }
            this.path.add(current);
            distance++;
        }
        this.path.add(lastWord);
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