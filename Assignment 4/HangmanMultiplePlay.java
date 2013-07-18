/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class HangmanMultiplePlay extends ConsoleProgram {
	public static final int GUESS_TIMES = 8;

	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
    public void run() {
    	println("Welcome to Hangman!");
    	initialRealWord();
    	initialWordNow();
    	guessWord();
    	finalResult();
    	playANewGame();
		
	}
    
    /* pick the word from the lexicon, and initial the wordNow*/
    private void initialRealWord(){
    	RandomGenerator rgen = RandomGenerator.getInstance();
    	int WordIndex = rgen.nextInt(lexicon.getWordCount());
    	realWord = lexicon.getWord(WordIndex);
    }
    
    private void initialWordNow(){
    	wordNow = "";
    	for (int i = 0; i < realWord.length(); i++){
    		wordNow += "-";
    	}

    }
    
    private void guessWord(){
    	while (guessCount != 0 ){
        	println("The word now looks like this: " + wordNow);
        	println("You have " + (guessCount == 1 ? "one guess" : (guessCount + " guesses")) + " left.");
        	inputGuess();
        	compareGuess();
        	if (wordNow.equals(realWord)) break;

    	}
    	
    }
    
    private void inputGuess(){
    	guess = readLine("Your guess: ");
		charGuess = guess.charAt(0);

    	while (guess.length() > 1 || !Character.isLetter(charGuess)){
    		println("Wrong input.");
        	guess = readLine("Your guess: ");
    		charGuess = guess.charAt(0);
    	}
		charGuess = Character.toUpperCase(charGuess);
  	
    }
    
    private void compareGuess(){
     	int index = realWord.indexOf(charGuess);
    	if (index != -1) {
    		println("That guess is correct");
    		wordNow = updateWord(wordNow, charGuess);
    	} else {
    		guessCount--;
    		println("There are no " + charGuess + "'s in the word.");
    	}
    }
    
    
    private String updateWord(String str, char ch){
    	for (int i = 0; i < realWord.length(); i++){
    		if (ch == realWord.charAt(i)){
    			str = str.substring(0, i) + ch + str.substring(i+1, str.length());
    		}
    	}
    	return str;    	
    }
    

    private void finalResult(){
    	if (wordNow.equals(realWord)){
    		println("");
    		println("You guess the word: " + realWord);
    		println("You win.");
    	}
    	else {
    		println("");
    		println("You're completely hung.");
    		println("You lose");
    		tryAgain();

    	}
    }
    
    private void tryAgain(){
    	println("");
    	int input = readInt("Do you want to try again (Enter 1)? (Enter 0 if you don't want to try again): ");
    	while (input !=0 && input != 1) {
    		println("");
    		println("Wrong input");
        	input = readInt("Do you want to try again (Enter 1)? (Enter 0 if you don't want to try again): ");
    	}
    	
    	if (input == 0) {
    		println("");
    		println("The word was: " + realWord);
    	} else if (input == 1){
    		println("");
    		println("Let's try again.");
        	initialWordNow();
        	guessCount = GUESS_TIMES;
        	guessWord();
        	finalResult();
        	tryAgain();
    	} 
    	
    }

    private void playANewGame(){
    	println("");
    	int input = readInt("Do you want to start a new game (Enter 1)? (Enter 0 if you don't want to continue): ");
    	while (input !=0 && input != 1) {
    		println("");
    		println("Wrong input");
        	input = readInt("Do you want to start a new game (Enter 1)? (Enter 0 if you don't want to continue): ");
    	}
    	
    	if (input == 0) {
    		println("");
    		println("Thanks for playing. 88");
    	} else if (input == 1){
    		println("");
    		println("This is a new game.");
        	initialRealWord();
        	initialWordNow();
        	guessCount = GUESS_TIMES;
        	guessWord();
        	finalResult();
        	playANewGame();
    	} 
    }

    
    
    
    
    
    
    
    /* Private instance variables */
 //   private RandomGenerator rgen = RandomGenerator.getInstance();
 //   private int WordIndex; /* the word index number to find the word in lexicon*/
    private String wordNow;/*keep tracking the guessing word*/
    private String realWord; /*the real word*/
    private HangmanLexicon lexicon = new HangmanLexicon();
    private int guessCount = GUESS_TIMES; /*initial guesses count*/
    private String guess; /* the input by the user*/
    private char charGuess;
	private HangmanCanvas canvas;
}
