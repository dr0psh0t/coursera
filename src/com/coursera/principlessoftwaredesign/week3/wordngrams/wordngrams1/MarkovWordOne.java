package com.coursera.principlessoftwaredesign.week3.wordngrams.wordngrams1;
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
	private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}

	public int indexOf(String[] words, String target, int start) {
    	for (int k = start; k < words.length; k++) {
    		if (words[k].equals(target)) {
    			return k;
			}
		}
    	return -1;
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<>();
	    int advIndex = 1;

	    for (int k = 0; k < myText.length; k++) {
	    	if (advIndex < myText.length) {
				if (myText[k].equals(key)) {
					follows.add(myText[advIndex]);
				}
			}
	    	advIndex++;
		}

		return follows;
    }
}
