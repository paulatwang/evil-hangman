import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HangmanEvilSolution {
    private final int WORD_LENGTH;
    private ArrayList<String> possibleWords;
    private ArrayList<Character> display;


    public HangmanEvilSolution(int wordLength, ArrayList<String> wordSet){
        this.WORD_LENGTH = wordLength;
        this.possibleWords = wordSet;
        this.display = updateDisplay("_".repeat(WORD_LENGTH));
    }

    public void updateGuess(char guessedLetter){
        // initialize new wordFamily
        HashMap<String, ArrayList<String>> wordFamily = new HashMap<>();

        // fill in wordFamily from possibleWords
        for (String word: this.possibleWords){
            if (word.contains(Character.toString(guessedLetter))){
                ArrayList<Integer> letterIndexes = getIndexesOfCharacter(guessedLetter, word);
                String ps = createPartialSolution(guessedLetter, letterIndexes);
                wordFamily.putIfAbsent(ps, new ArrayList<>());
                wordFamily.get(ps).add(word);
            }
        }

        // update new possibleWords and display
        String newSolution = getLongestWordFamily(wordFamily);
        this.display = updateDisplay(newSolution);
        this.possibleWords = wordFamily.get(newSolution);

    }

    private ArrayList<Character> updateDisplay(String newOutput){
        ArrayList<Character> updated = new ArrayList<>();
        for (char c : newOutput.toCharArray()){
            updated.add(c);
        }
        return updated;
    }

    // This method returns the key of the longest value in wordFamily
    private String getLongestWordFamily(HashMap<String, ArrayList<String>> wordFamily){
        String longestKey = "";
        int size = 0;
        for (HashMap.Entry<String, ArrayList<String>> entry : wordFamily.entrySet()){
            ArrayList<String> listOfValues = entry.getValue();
            if (listOfValues.size() > size){
                longestKey = entry.getKey();
                size = listOfValues.size();
            }
        }
        return longestKey;
    }

    // This method iterates over the string and find all occurrences of a target character
    private ArrayList<Integer> getIndexesOfCharacter(char targetChar, String str){
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == targetChar) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    // This method returns a String consisting of the partial solution
    // inputs: letter, listOfPositions
    // outputs: String (e.g., "--e--")
    private String createPartialSolution (char letter, ArrayList<Integer> listOfPositions){
        ArrayList<Character> partialSolution = this.display;

        for (int idx : listOfPositions){ // update partial solution with new letters
            partialSolution.set(idx, letter);
        }

        for (int i = 0; i < WORD_LENGTH; i++){
            if (partialSolution.get(i) == '_'){
                partialSolution.set(i, '-');
            }
        }

        StringBuilder sb = new StringBuilder(); // convert solution to String
        for (char c : partialSolution) {
            sb.append(c);
        }
        return sb.toString();
    }


    public void printProgress(){
        for (char c : display) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public boolean isSolved(){
        for (char c : this.display){
            if (c == '_'){
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getPossibleWords(){
        return possibleWords;
    }



}
