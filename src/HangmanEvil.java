import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class HangmanEvil {
    private HashMap<Integer, ArrayList<String>> wordList;
    private HashSet<Character> previousGuesses;
    private TreeSet<Character> incorrectGuesses;
    private HangmanEvilSolution solution;
    private Scanner inputScanner;

    public HangmanEvil() {
        this("engDictionary.txt");
    }

    public HangmanEvil(String filename) {
        try {
            wordList = dictionaryToList(filename);
        } catch (IOException e) {
            System.out.printf(
                    "Couldn't read from the file %s. Verify that you have it in the right place and try running again. \n",
                    filename);
            e.printStackTrace();
            System.exit(0);
        }

        this.previousGuesses = new HashSet<>();
        this.incorrectGuesses = new TreeSet<>();
        this.inputScanner = new Scanner(System.in);

        int randomLength = new Random().nextInt(wordList.size());
        this.solution = new HangmanEvilSolution(randomLength, wordList.get(randomLength));
    }


    public void start(){
        while (!solution.isSolved()){
            char guess = promptForGuess();
            previousGuesses.add(guess);
            boolean containsLetter = solution.updateGuess(guess);
            if (!containsLetter){
                incorrectGuesses.add(guess);
            }
        }
        printVictory();
    }


    private char promptForGuess() {
        while (true) {
            System.out.println("Guess a letter.\n");
            solution.printProgress();
            System.out.println("Incorrect guesses:\n" + incorrectGuesses.toString());
            String input = inputScanner.next();
            if (input.length() != 1) {
                System.out.println("Please enter a single character.");
            } else if (previousGuesses.contains(input.charAt(0))) {
                System.out.println("You've already guessed that.");
            } else {
                return input.charAt(0);
            }
        }
    }

    private void printVictory() {
        System.out.printf("Congrats! The word was %s%n", solution.getPossibleWords().get(0));
    }

    private static HashMap<Integer, ArrayList<String>> dictionaryToList(String filename) throws IOException {
        FileInputStream fs = new FileInputStream(filename);
        Scanner scnr = new Scanner(fs);

        HashMap<Integer, ArrayList<String>> wordList = new HashMap<>();
        while (scnr.hasNext()) {
            String word = scnr.next();
            int length = word.length();
            wordList.putIfAbsent(length, new ArrayList<>()); // if no key-value pair exists, add it
            wordList.get(length).add(word); // add word to int key
        }
        return wordList;
    }



}


