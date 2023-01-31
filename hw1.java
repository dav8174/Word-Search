import java.io.*;
import java.util.*;

public class hw1{
    ArrayList<Word> path = new ArrayList<Word>();

    public void dfs(Word start, Word target, String file){
        Stack<Word> stack = new Stack<Word>();
        stack.push(start);
        while(!stack.isEmpty()){
            Word current = stack.pop();
            if(!current.visited){
                if(current.word.equals(target.word))
                    this.path.add(0,current);
                else{
                    current.visited = true;
                    for(int i = 0; i < current.nbs.size(); i++){
                        Word next = new Word(file,current.nbs.get(i));
                        dfs(next, target, file);
                    }
                }
            }
        }

    }
    public static void main(String[] args){
        String file = args[0];
        Word start = new Word(file, args[1]);
        Word target = new Word(file, args[2]);


        dfs(start, target, file);

        System.out.println(start.nbs);
        System.out.println(start.nbs.size());
    }
}