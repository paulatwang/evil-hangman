import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class HangmanEvilSolution {
    private ArrayList<String> wordSet;
    private ArrayList<Character> display;
    private HashMap<String, ArrayList<String>> wordFamily;
    private int missingChars;
    private char currentLetter;

    public HangmanEvilSolution(ArrayList<String> wordSet){
        this.wordSet = wordSet;
        this.missingChars = wordSet.get(0).length(); // get length of solution
        this.display = new ArrayList<>(); // initialize display to ___
        for (int i = 0; i < missingChars; i++) {
            display.add('_');
        }
    }

    // This method returns a String consisting of the partial solution
    // inputs: wordLength, letter, listOfPositions
    // outputs: String (e.g., "--e--")
    public String createPartialSolution (int wordLength, char letter, ArrayList<Integer> listOfPositions){
        ArrayList<Character> partialSolution = new ArrayList<>();
        for (int i = 0; i < wordLength; i++){  // initialize partial solution with '--'
            partialSolution.add('-');
        }
        for (int idx : listOfPositions){ // update partial solution with letter
            partialSolution.set(idx, letter);
        }
        StringBuilder sb = new StringBuilder(); // convert solution to String
        for (char c : partialSolution) {
            sb.append(c);
        }
        return sb.toString();
    }

    // This method iterates over the string and find all occurrences of a target character
    public ArrayList<Integer> getIndexesOfCharacter(char targetChar, String str){
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == targetChar) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public HashMap<String, ArrayList<String>> getWordFamily(char c) {
        // if word contains c, then create partial solution as index and add to word family
        for (String word: wordSet){ // filter matched words to those only containing c
            if (word.contains(Character.toString(c))){
                ArrayList<Integer> letterIndexes = getIndexesOfCharacter(c, word);
                String ps = createPartialSolution(word.length(), c, letterIndexes);
                wordFamily.putIfAbsent(ps, new ArrayList<>()); // add to word family
                wordFamily.get(ps).add(word);
            }
        }
        return wordFamily;
    }

    public void printProgress(){
        for (char c : display) {
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
