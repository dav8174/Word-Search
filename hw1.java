import java.io.*;
import java.util.*;

public class hw1{
    public static void main(String[] args){
        String file = args[0];
        String start = args[1];
        String target = args[2];

        if(start.length() != target.length()){
            System.out.println("no solution");
        }

        ArrayList<Word> wordList = new ArrayList<Word>();
        Word startWord = null;
        Word targetWord = null;

        try {
            File f = new File(file);
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Word newWord = new Word(data);
                wordList.add(newWord);
                if (data.equals(start)){
                    startWord = newWord;
                    startWord.distFromSrc = 0;
                } else if (data.equals(target)){
                    targetWord = newWord;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        for(int i = 0; i < wordList.size(); i++){
            wordList.get(i).fillNBS(wordList);
        }

        findPath findPath = new findPath(startWord, targetWord, wordList);

        if(targetWord.distFromSrc == -1)
            System.out.println("no solution");
        else{
            ArrayList<Word> path = findPath.path;
            for(int i = 0; i < path.size(); i++){
                System.out.println(path.get(i).word);
            }
        }
    }
}