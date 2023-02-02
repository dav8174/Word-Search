import java.util.*;

public class findPath{
    Word start;
    Word target;
    ArrayList<Word> wordList;
    ArrayList<Word> path;

    findPath(Word start, Word target, ArrayList<Word> wordList){
        this.start = start;
        this.target = target;
        this.wordList = wordList;
        this.path = new ArrayList<Word>();
        
        search();
    }

    public void search(){
        ArrayList<Word> toVisit = new ArrayList<Word>();
        toVisit.add(this.start);
        while(!toVisit.isEmpty()){
            Word current = pickNext(toVisit);
            for(int i = 0; i < toVisit.size(); i++){
                if(toVisit.get(i).equals(current)){
                    toVisit.remove(i);
                }
            }
            current.visited = true;
            for(int i = 0; i < current.nbs.size(); i++){
                if(!current.nbs.get(i).visited){
                    if(current.nbs.get(i).equals(target)){
                        current.nbs.get(i).distFromSrc = current.distFromSrc+1;
                        makePath(current.nbs.get(i));
                        return;
                    }
                    toVisit.add(current.nbs.get(i));
                    current.nbs.get(i).distFromSrc = current.distFromSrc + 1;
                }
            }
        }
    }

    public Word pickNext(ArrayList<Word> list){
        Word nextWord = list.get(0);
        for(int i = 1; i < list.size(); i++){
            if(list.get(i).distFromSrc < nextWord.distFromSrc){
                nextWord = list.get(i);
            }
        }
        return nextWord;
    }

    public void makePath(Word currentWord){
        path.add(0,currentWord);
        if(!currentWord.equals(this.start)){
            Word nextWord = currentWord.nbs.get(0);
            for(int i = 1; i < currentWord.nbs.size(); i++){
                if(currentWord.nbs.get(i).distFromSrc == currentWord.distFromSrc-1){
                    nextWord = currentWord.nbs.get(i);
                }
            }
            makePath(nextWord);  
        }
    }
}