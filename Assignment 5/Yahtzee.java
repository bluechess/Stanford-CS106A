/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		do{
			nPlayers = dialog.readInt("Enter number of player (<= 4 players)");
		} while(nPlayers > MAX_PLAYERS);
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		score = new int[nPlayers][N_CATEGORIES];
		initScore();
		for(int i = 0; i < N_SCORING_CATEGORIES; i++){
			for(int n = 1; n <= nPlayers; n++){
				RollDice3Times(n);
				updateScorecard(n);
			}
		}
		fianlScoreCheck();
		whoIsTheWinner();
	}
	
	/* initial score, set all 13 categories' score at -1;
	 * set upper score, lower score, upper bonus and total score at 0*/
	private void initScore(){
		for(int n = 0; n < nPlayers; n++){
			for(int i = 0; i < N_CATEGORIES; i++){
				score[n][i] = -1;
			}
		}
		for(int n = 0; n < nPlayers; n++){
			score[n][UPPER_SCORE-1] = 0;
			score[n][LOWER_SCORE-1] = 0;
			score[n][UPPER_BONUS-1] = 0;
			score[n][TOTAL-1] = 0;
		}


	}
	
	/* one player play one turn, each turn you can roll dice 3 times*/
	
	private void RollDice3Times(int player){
		// first try
		display.printMessage(playerNames[player-1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(player);

		for(int i = 0; i < N_DICE; i++){
			dice[i] = rgen.nextInt(1, 1+N_DICE);
		}
		display.displayDice(dice);
		
		// 2nd try and 3rd try
		for(int n = 0; n < 2; n++){
			display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"");
			display.waitForPlayerToSelectDice();
			for(int i = 0; i < N_DICE; i++){
				if(display.isDieSelected(i)){
					dice[i] = rgen.nextInt(1, 1+N_DICE);
				}
			}
			display.displayDice(dice);
		}
		
	}
	

	
	/* method to update the score card */
	private  void updateScorecard(int player){
		display.printMessage("Select a category for this roll");
		int category = display.waitForPlayerToSelectCategory();
		while(categoryAlreadyChosen(player, category)){
			display.printMessage("The category is already chosen, please select another one!");
			category = display.waitForPlayerToSelectCategory();
		}
		
		boolean p = YahtzeeMagicStub.checkCategory(dice, category);
		
		if(p){
			score[player-1][category-1] = calculateScore(category);
		} else {
			score[player-1][category-1] = 0;
		}
		display.updateScorecard(category, player, score[player-1][category-1]);
		
		// update total score
		score[player-1][TOTAL-1] += score[player-1][category-1];
		display.updateScorecard(TOTAL, player, score[player-1][TOTAL-1]);
	}

	
	/* check if the category is already chosen
	 * if == -1, so not used*/
	private boolean categoryAlreadyChosen(int player, int category){
		if(score[player-1][category-1] != -1){
			return true;
		} else {
			return false;
		}
	}
	
	/* calculate score for each turn*/
	private int calculateScore(int category){
		int thisScore = 0;
        switch (category) {
        	case ONES:  thisScore = sumSpecialDices(ONES);
        		break;
        	case TWOS:  thisScore = sumSpecialDices(TWOS);
        		break;
        	case THREES:  thisScore = sumSpecialDices(THREES);
                break;
        	case FOURS:  thisScore = sumSpecialDices(FOURS);
                break;
        	case FIVES:  thisScore = sumSpecialDices(FIVES);
                break;
        	case SIXES:  thisScore = sumSpecialDices(SIXES);
                break;
        	case THREE_OF_A_KIND:  thisScore = sumAllDices();
                break;
        	case FOUR_OF_A_KIND:  thisScore = sumAllDices(); 
                break;
        	case FULL_HOUSE:  thisScore = 25;
                break;
        	case SMALL_STRAIGHT: thisScore = 30; 
                break;
        	case LARGE_STRAIGHT: thisScore = 40;
                break;
        	case YAHTZEE: thisScore = 50;
                break;
        	case CHANCE: thisScore = sumAllDices();
        		break;
        	default: thisScore = 0;
                 break;
    }
		return thisScore;
	}
	
	/* calculate the sum of all of the 1's (or 2-6) showing on the dice,
	 * thus called the sumSpecialDice*/
	private int sumSpecialDices(int number){
		int sum = 0;
		for(int i = 0; i < N_DICE; i++){
			if(dice[i] == number){
				sum += dice[i];
			}
		}
		return sum;
	}
	
	/* calculate the sum of all 5 dices*/
	private int sumAllDices(){
		int sum = 0;
		for(int i = 0; i < N_DICE; i++){
			sum += dice[i];
		}
		return sum;
	}
	
	/* after all turns, update the final score card*/
	private void fianlScoreCheck(){
		// update Upper Score
		for(int n = 0; n < nPlayers; n++){
			for(int i = 0; i < 6; i++){
					score[n][UPPER_SCORE-1] += score[n][i];
			}
		}
		
		// update Upper Bonus
		for(int n = 0; n < nPlayers; n++){
			if(score[n][UPPER_SCORE-1] >= 63){
				score[n][UPPER_BONUS-1] = 35;
			}
		}
		
		// update Lower Score
		for(int n = 0; n < nPlayers; n++){
			for(int i = 8; i < 15; i++){
					score[n][LOWER_SCORE-1] += score[n][i];
			}
		}
		
		// update final Total score and update the display
		for(int n = 1; n <= nPlayers; n++){
			score[n-1][TOTAL-1] = score[n-1][UPPER_SCORE-1] + score[n-1][LOWER_SCORE-1] + score[n-1][UPPER_BONUS-1];
			display.updateScorecard(UPPER_SCORE, n, score[n-1][UPPER_SCORE-1]);
			display.updateScorecard(LOWER_SCORE, n, score[n-1][LOWER_SCORE-1]);
			display.updateScorecard(UPPER_BONUS, n, score[n-1][UPPER_BONUS-1]);
			display.updateScorecard(TOTAL, n, score[n-1][TOTAL-1]);
		}
		
	}
	
	/* final message and check who wins the game*/
	private void whoIsTheWinner(){
		int highest = 0;
		int player = 0;
		for(int n = 1; n <= nPlayers; n++){
			if(score[n-1][TOTAL-1] > highest){
				highest = score[n-1][TOTAL-1];
				player = n;
			}
		}
		
		display.printMessage("Congratulations, " + playerNames[player-1] + " , you're the winner with a total score of " + highest);
	}
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[][] score;
	private int[] dice = new int[N_DICE];

}
