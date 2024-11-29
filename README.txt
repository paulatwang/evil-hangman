# CIT 591 Homework 6 - Evil Hangman

Completed alone.

This program is a twist on the traditional Hangman game. In Hangman, the word that the user is trying to guess is fixed before they start playing. In Evil Hangman, the program maintains a set of words that match the pattern of correctly provided letters so far, but does not commit to any particular word in advance. If a user guesses a letter, the program will choose to assign that letter to the location(s) in the partial solution that leaves the most potential matches remaining. It might be the case that leaving the letter out keeps the most options open; in that case, you’d report the letter as being an incorrect guess.

#### Tools for completing this assignment
- File I/O
- Loops & Iteration
- Getting User Input from a Scanner
- Reading and Understanding Provided Code
- Working with Data Structures
- Unit Testing

#### Cohesion and Coupling Analysis

##### Cohesion
This program implements high cohesion given that:
- Classes group related functions together:
    - The FileIO class groups all file input-output functions in one class.
    - The HangmanEvil class groups all gameplay methods.
    - The HangmanEvilSolution class groups all methods related to creating and resolving the solution.
- Within each class, each method performs a specific and well-defined task:
    - In FileIO class:
        - dictionaryToList: creates a list from a provided dictionary
        - loadDictionary: attempts to load the dictionaryToList method
    - In HangmanEvil:
        - promptForGuess: takes in user input for the guesses
        - addGuess: adds the guesses to the appropriate incorrectGuesses or previousGuesses variables, and calls for an updated solution
        - start: implements the methods in the correct order
        - printVictory: prints the ending victory statement
    - In HangmanEvilSolution:
        - getLongestWordFamily: determines the longest array in a word family
        - getIndexesOfCharacter: creates a list of all indexes where a letter appears in a word
        - createPartialSolution: creates a string for the partial solution
        - updateDisplay: updates the gameplay display
        - printProgress: prints the gameplay display
        - isSolved: determines whether the game should end or not
        - getPossibleWords: gets a list of all the possible words that could be a potential solution
        - updateSolution: uses the input guess to determine the next list of words for a potential solution

##### Coupling
This program implements low cohesion given that:
- FileIO and HangmanEvilSolution classes are entirely independent from one another
- Only the HangmanEvil class relies on FileIO and HangmanEvilSolution

#### Git Commits
