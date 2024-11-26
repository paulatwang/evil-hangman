import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HangmanEvilTest {
    private HangmanEvilSolution solution;
    private ArrayList<String> sampleWordSet;

    @BeforeEach
    public void init() {
        sampleWordSet = new ArrayList<>();
        sampleWordSet.add("apple");
        sampleWordSet.add("table");
        sampleWordSet.add("chair");
        sampleWordSet.add("stone");
        sampleWordSet.add("bread");
        sampleWordSet.add("fruit");
        sampleWordSet.add("clock");
        sampleWordSet.add("plant");
        sampleWordSet.add("house");
        sampleWordSet.add("water");
        sampleWordSet.add("smile");
        sampleWordSet.add("laptop");
        sampleWordSet.add("music");
        sampleWordSet.add("green");
        sampleWordSet.add("cloud");
        sampleWordSet.add("paper");
        sampleWordSet.add("light");
        sampleWordSet.add("night");
        sampleWordSet.add("dream");
        sampleWordSet.add("fight");
//        ArrayList<String> containsE = new ArrayList<>();
//        for (String word: wordSet){
//            if (word.contains(Character.toString('e'))){
//                containsE.add(word);
//            }
//        }

        this.solution = new HangmanEvilSolution(sampleWordSet);

    }

    @Test
    public void testGetWordSet(){
        ArrayList<String> expected = sampleWordSet;
        ArrayList<String> actual = solution.getWordSet();
        assertEquals(expected, actual, "WordSet should be the same as the sampleWordSet");
    }


    // getWordFamily tests
    @Test
    public void testGetIndexesOfCharacterWithOneLetter() {
        char c = 'e';
        String word = "dream";

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        ArrayList<Integer> actual = solution.getIndexesOfCharacter(c, word);
        assertEquals(expected, actual, "Indexes of 'e' in 'dream' is [2]");
    }

    @Test
    public void testGetIndexesOfCharacterWithTwoLetters() {
        char c = 'e';
        String word = "green";

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        ArrayList<Integer> actual = solution.getIndexesOfCharacter(c, word);
        assertEquals(expected, actual, "Indexes of 'e' in 'green' is [2, 3]");
    }

    @Test
    public void testCreatePartialSolutionWithOneLetter(){
        int length = 5;
        char c = 'e';
        String word = "dream";
        ArrayList<Integer> indexes = new ArrayList<>();
        indexes.add(2);

        String expected = "--e--";
        String actual = solution.createPartialSolution(length, c, indexes);
        assertEquals(expected, actual, "Partial solution should be --e--");
    }

    @Test
    public void testCreatePartialSolutionWithTwoLetters(){
        int length = 5;
        char c = 'e';
        String word = "green";
        ArrayList<Integer> indexes = new ArrayList<>();
        indexes.add(2);
        indexes.add(3);

        String expected = "--ee-";
        String actual = solution.createPartialSolution(length, c, indexes);
        assertEquals(expected, actual, "Partial solution should be --ee-");
    }
//
//    @Test
//    public void testCreatePartialSolution() {
//        char c = 'e';
//        HashSet<String> expectedKeys = new HashSet<>();
//        expectedKeys.add("----e");
//        double expected = input1.length();
//        double actual = solution.
//        assertEquals(expected, actual, "Similarity should be length of the word.");
//    }
}
