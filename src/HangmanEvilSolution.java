import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class HangmanEvilSolution {
    private ArrayList<String> wordSet;
    private ArrayList<Character> partialSolution;
    private HashMap<ArrayList<Character>, ArrayList<String>> wordFamily;
    private int missingChars;

    public HangmanEvilSolution(ArrayList<String> wordSet){
        this.wordSet = wordSet;
        this.missingChars = wordSet.get(0).length(); // get length of solution
        this.partialSolution = new ArrayList<>(); // initialize partial solution to ___
        for (int i = 0; i < missingChars; i++) {
            partialSolution.add('_');
        }
    }

    public HashMap<ArrayList<Character>, ArrayList<String>> getWordFamily(char c) {
        // if word contains c, then create partial solution as index and add to word family
        for (String word: wordSet){ // filter matched words to those only containing c
            if (word.contains(Character.toString(c))){
                int index = word.indexOf(c); // identify first instance of letter in string
                ArrayList<Character> ps = new ArrayList<>(); // create partial solution
                for (int i = 0; i < word.length(); i++){
                    ps.add('-');
                }
                ps.set(index, c);
                wordFamily.putIfAbsent(ps, new ArrayList<>()); // add to word family
                wordFamily.get(ps).add(word);
            }
        }
        return wordFamily;
    }

    public void printProgress(){
        for (char c : partialSolution) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public boolean isSolved(){
        return missingChars == 0;
    }

    public ArrayList<String> getWordSet(){
        return wordSet;
    }



}
