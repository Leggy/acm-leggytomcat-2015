package acm2015.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ProblemB {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		String[] words = input.split(" ");

		//----sorting word hack
		String word1 = "";
		String word2 = "";
		
		if(words[0].length() == words[1].length()){
			if(words[0].compareTo(words[1]) < 0){
				word1 = words[0];
				word2 = words[1];
			} else {
				word1 = words[1];
				word2 = words[0];
			}
			
		} else if (words[0].length() < words[1].length()){
			word1 = words[0];
			word2 = words[1];
		} else {
			word1 = words[1];
			word2 = words[0];
		}
		//--end sorting word hack
		
		

		if (word1.equals(word2)) {
			System.out.println(word1 + " is identical to " + word2);
			return;
		}
		
		char[] word1Array = word1.toCharArray();
		char[] word2Array = word2.toCharArray();
		
		Map<Character, Integer> word1Map = new HashMap<Character, Integer>();
		Map<Character, Integer> word2Map = new HashMap<Character, Integer>();
		
		for(char c :  word1Array){
			if(word1Map.containsKey(c)){
				word1Map.put(c, word1Map.get(c) + 1);
			} else {
				word1Map.put(c, 1);
			}
		}
		
		for(char c :  word2Array){
			if(word2Map.containsKey(c)){
				word2Map.put(c, word2Map.get(c) + 1);
			} else {
				word2Map.put(c, 1);
			}
		}
		
		int differences = 0;
		if(word1.length() == word2.length()){

			for(char c : word1Map.keySet()){
				if(word1Map.get(c) != word2Map.get(c)){
					if (word2Map.containsKey(c)) {
					differences += Math.abs(word1Map.get(c) - word2Map.get(c));
					}
					else {
						differences += word1Map.get(c);
					}
				}
			}
			for(char c : word2Map.keySet()){
				if (!word1Map.containsKey(c)) {
					differences += word2Map.get(c);
				}
			}
			if(differences == 0){
				System.out.println(word1 + " is an anagram of " + word2);
			} else if (differences <= 2){
				System.out.println(word1 + " is almost an anagram of " + word2);
			} else {
				System.out.println(word1 + " is nothing like " + word2);
			}
			
		} else {

			for(char c : word1Map.keySet()){
				if(word1Map.get(c) != word2Map.get(c)){
					if (word2Map.containsKey(c)) {
					differences += Math.abs(word1Map.get(c) - word2Map.get(c));
					}
					else {
						differences += word1Map.get(c);
					}
				}
			}
			for(char c : word2Map.keySet()){
				if (!word1Map.containsKey(c)) {
					differences += word2Map.get(c);
				}
			}
			
			if(differences <= 1){
				System.out.println(word1 + " is almost an anagram of " + word2);
			} else {
				System.out.println(word1 + " is nothing like " + word2);
			}
		}
	}

}
