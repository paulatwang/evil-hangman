import java.util.*;

public class HangmanEvil {

    private HangmanEvilSolution solution;
    private Scanner inputScanner;
    private ArrayList<Character> display;
    private int WORD_LENGTH;
    private HashSet<Character> previousGuesses;
    private TreeSet<Character> incorrectGuesses;

    public HangmanEvil() {
        this("engDictionary.txt");
    }

    public HangmanEvil(String filename) {
        // Load dictionary and read as wordList
        FileIO fileio = new FileIO();
        fileio.loadDictionary(filename);
        HashMap<Integer, ArrayList<String>> wordList = fileio.getWordList();

        // Setup empty guesses
        this.previousGuesses = new HashSet<>();
        this.incorrectGuesses = new TreeSet<>();

        // Setup user input scanner
        this.inputScanner = new Scanner(System.in);

        // Setup solution
        WORD_LENGTH = new Random().nextInt(wordList.size());
        this.solution = new HangmanEvilSolution(WORD_LENGTH, wordList.get(WORD_LENGTH));

    }


    public void start(){
        while (!solution.isSolved()){
            char guess = promptForGuess();
            addGuess(guess);

        }
        printVictory();
    }

    private void addGuess(char guess){
        previousGuesses.add(guess);
        boolean containsLetter = solution.updateSolution(guess);
        if (!containsLetter){
            incorrectGuesses.add(guess);
        }
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








}


