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

public class Hangman extends ConsoleProgram {
	public static final int GUESS_TIMES = 8;

	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
    public void run() {
    	println("Welcome to Hangman!");
    	canvas.reset();
    	initialRealWord();
    	initialWordNow();
    	guessWord();
    	finalResult();
		
	}
    
    /* pick the word from the lexicon*/
    private void initialRealWord(){
    	RandomGenerator rgen = RandomGenerator.getInstance();
    	int WordIndex = rgen.nextInt(lexicon.getWordCount());
    	realWord = lexicon.getWord(WordIndex);
    }
    
    /* initial the wordNow*/
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
    		canvas.noteIncorrectGuess(charGuess);
    	}
    	canvas.displayWord(wordNow);
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
    		println("The word was: " + realWord);
    		println("You lose");

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
