import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanEvilTest {
    private HangmanEvilSolution solution;
    private ArrayList<String> sampleWordSet;
    private char letter;
    private String word1;
    private String word2;
    private ArrayList<Integer> word1Index;
    private ArrayList<Integer> word2Index;

    @BeforeEach
    public void init() {
        sampleWordSet = new ArrayList<>();
        sampleWordSet.add("apple");
        sampleWordSet.add("table");
        sampleWordSet.add("stone");
        sampleWordSet.add("bread");
        sampleWordSet.add("house");
        sampleWordSet.add("water");
        sampleWordSet.add("smile");
        sampleWordSet.add("green");
        sampleWordSet.add("paper");
        sampleWordSet.add("dream");

        this.solution = new HangmanEvilSolution(5, sampleWordSet);

        this.letter = 'e';
        this.word1 = "dream";
        this.word2 = "green";

        this.word1Index = new ArrayList<>();
        this.word1Index.add(2);

        this.word2Index = new ArrayList<>();
        this.word2Index.add(2);
        this.word2Index.add(3);



    }

    @Test
    public void testGetPossibleWords(){
        ArrayList<String> expected = sampleWordSet;
        ArrayList<String> actual = solution.getPossibleWords();
        assertEquals(expected, actual, "WordSet should be the same as the sampleWordSet");
    }

    @Test
    public void testUpdateSolutionReturnTrue(){
        boolean expected = true;
        boolean actual = solution.updateSolution('e');
        assertEquals(expected, actual, "updateGuess should return true.");
    }

    @Test
    public void testUpdateSolutionReturnFalse(){
        boolean expected = false;
        boolean actual = solution.updateSolution('z');
        assertEquals(expected, actual, "updateGuess should return false.");
    }


    // getWordFamily tests
    @Test
    public void testGetIndexesOfCharacterWithOneLetter() {
        ArrayList<Integer> expected = word1Index;
        ArrayList<Integer> actual = solution.getIndexesOfCharacter(letter, word1);
        assertEquals(expected, actual, "Indexes of 'e' in 'dream' is [2]");
    }

    @Test
    public void testGetIndexesOfCharacterWithTwoLetters() {
        ArrayList<Integer> expected = word2Index;
        ArrayList<Integer> actual = solution.getIndexesOfCharacter(letter, word2);
        assertEquals(expected, actual, "Indexes of 'e' in 'green' is [2, 3]");
    }

    @Test
    public void testCreatePartialSolutionWithOneLetter(){
        String expected = "--e--";
        String actual = solution.createPartialSolution(letter, word1Index);
        assertEquals(expected, actual, "Partial solution should be --e--");
    }

    @Test
    public void testCreatePartialSolutionWithTwoLetters(){
        String expected = "--ee-";
        String actual = solution.createPartialSolution(letter, word2Index);
        assertEquals(expected, actual, "Partial solution should be --ee-");
    }



    @Test
    public void testIsSolved(){
        boolean expected = false;
        boolean actual = solution.isSolved();
        assertEquals(expected, actual, "isSolved should be false.");
    }



}
