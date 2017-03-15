/*
 Trie map used for small scale string completion for non ambiguous strings - more considerations have to be taken for multi worded phrases & for oddly capitalised phrases
 - consider including array[length of string] of boolean values in the endOfPhrase node - indicating whether individual chars are uppercase or not 
 Trie Map originally sourced/inspired http://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
*/

package impl;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class TrieMapAutoComplete {
	

	private TrieNode root;
	
	/*Node inner class*/
	private class TrieNode {
		private char value;
		private boolean isEndOfPhraseNode;
		private boolean isCapitalisedWord = false;
		private HashMap<Character, TrieNode> childNodes = new HashMap<Character, TrieNode>();
		
		
		public char getValue(){
			return value;
		}
		
		public TrieNode(){}
	
		public TrieNode(char arg){
			value = arg;
		}
		
		public boolean isEndOfPhraseNode(){
			return isEndOfPhraseNode;
		}
		
		public boolean isCapitalisedWord(){
			return isCapitalisedWord;
		}
		
		public void setCapitalisedWord(){
			isCapitalisedWord = true;
		}
		
		public void setEndOfPhraseNode(){
			isEndOfPhraseNode = true;
		}
	}
	
	
	
	public TrieMapAutoComplete(){
		root = new TrieNode();
	}
	
	
	public void insert(String word){
		
		HashMap<Character, TrieNode> childNodes = root.childNodes;
		
        for(int i=0; i < word.length(); i++){
            char aLetter = word.charAt(i);
 
            TrieNode temp;
            if(childNodes.containsKey(Character.toUpperCase(aLetter))){
                    temp = childNodes.get(Character.toUpperCase(aLetter));
            } else {            	
                temp = new TrieNode(aLetter);
                childNodes.put(Character.toUpperCase(aLetter), temp);
            }
 
            childNodes = temp.childNodes;
 
            /*set the node to EndOfPhraseNode*/
            if(i==word.length()-1) {
                temp.setEndOfPhraseNode();   
                /*if the first letter should be capital*/
                if(word.charAt(0) == Character.toUpperCase(word.charAt(0))) {
                	temp.setCapitalisedWord();
                }
            }
        }
	}
	
	public String getWordAutocomplete(String word) {
		HashMap<Character, TrieNode> childNodes = root.childNodes;
		String wordToReturn = "";
		
		for(int i = 0; i< word.length(); ++i) {
			char aLetter = word.charAt(i);
			
			
			TrieNode temp;
			temp = childNodes.get(Character.toUpperCase(aLetter));	
			
			/*HashMap returns null if key isn't present in Map*/
			if(temp == null) {
				return null;
			}

			wordToReturn += temp.getValue();
			
			/*if at end of word - and it's end of word node (this part is redundant I guess*/
			if(i == word.length()-1 && temp.isEndOfPhraseNode()) {
				if(temp.isCapitalisedWord) {
					return (Character.toUpperCase(wordToReturn.charAt(0)) + wordToReturn.substring(1, wordToReturn.length()));					
				} else {
					return (Character.toLowerCase(wordToReturn.charAt(0)) + wordToReturn.substring(1, wordToReturn.length()));	
				}
			}
			
			/*if we're at end of word to search for - and there's only one child node - for auto-complete*/
			if(i == word.length()-1) {
				/*if the number of children is only 1 - we can try and fetch the full word*/
				if(temp.childNodes.size()==1) {
					/*While there's only 1 child - continually traverse down tree*/
					while(temp.childNodes.size() <= 1) {
						List<TrieNode> theList = new ArrayList<TrieNode>(temp.childNodes.values());
						/*only one child - get the single element in the array */
						temp = theList.get(0);
						wordToReturn += temp.getValue();
						
						/*ambiguous word - multiple options exist*/
						if(temp.childNodes.size() > 1) {
							return null;
						} else if (temp.isEndOfPhraseNode()) {
							if(temp.isCapitalisedWord) {
								return (Character.toUpperCase(wordToReturn.charAt(0)) + wordToReturn.substring(1, wordToReturn.length()));					
							} 
							return (Character.toLowerCase(wordToReturn.charAt(0)) + wordToReturn.substring(1, wordToReturn.length()));	
						}
					}
				} 
			}
			childNodes = temp.childNodes;
		}
		
		/*if we're at this point -> no full word found => return null*/
		return null;
	}
}
