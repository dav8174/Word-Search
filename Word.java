import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;

public class Word{
    String file;
    String word;
    boolean visited;
    ArrayList<String> nbs; //neighbors

    public boolean oneOff(String other){
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
    
    public ArrayList<String> fillNBS(){
        ArrayList<String> neighbors = new ArrayList<String>();
        try {
            File file = new File(this.file);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              if(oneOff(data))
                neighbors.add(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
          }
        return neighbors;
    }

    Word(String file, String word){
        this.file = file;
        this.word = word;
        this.visited = false;
        this.nbs = this.fillNBS();
    }

}