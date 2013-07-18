/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return strList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return strList.get(index); 

	}
	
	// This is the HangmanLexicon constructor
	public HangmanLexicon(){
	     try {
	    	  BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
	    	  while(true) {
	    		  String line = rd.readLine();
		    	  if(line == null) break;
		    	  strList.add(line);
	    	  }
	          rd.close();
	     } catch(IOException ex) {
	          throw new ErrorException(ex);
	     }     
	    


	}
	

	private ArrayList<String> strList = new ArrayList<String>();


}
