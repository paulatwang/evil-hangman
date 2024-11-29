import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileIO {
    private HashMap<Integer, ArrayList<String>> wordList;

    public FileIO(){}

    public HashMap<Integer, ArrayList<String>> getWordList(){
        return this.wordList;
    }

    public void loadDictionary(String filename){
        try {
            this.wordList = dictionaryToList(filename);
        } catch (IOException e) {
            System.out.printf(
                    "Couldn't read from the file %s. Verify that you have it in the right place and try running again. \n",
                    filename);
            e.printStackTrace();
            System.exit(0);
        }
    }

    private HashMap<Integer, ArrayList<String>>  dictionaryToList(String filename) throws IOException {
        HashMap<Integer, ArrayList<String>> localWordList = new HashMap<>();
        try (
                FileInputStream fs = new FileInputStream(filename);
                Scanner scnr = new Scanner(fs)
        ){
            while (scnr.hasNext()) {
                String word = scnr.next();
                int length = word.length();
                localWordList.putIfAbsent(length, new ArrayList<>());
                localWordList.get(length).add(word);
            }
        }
        return localWordList;
    }
}
