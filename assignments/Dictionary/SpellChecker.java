import java.util.ArrayList;

public class SpellChecker{
	BinarySearchTree dictionaryTree;
	ArrayList<String> similarWords;
	char[] alphabetArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'w', 'x', 'y', 'z', 'æ', 'ø', 'å'};
		
	public ArrayList<String> checkWord(String word){
		char[] word_array = word.toCharArray();
		similarWords = new ArrayList<>();		

		similarWord_switch(word_array);
		similarWord_replacedLetter(word_array);
		similarWord_removedLetter(word_array);
		similarWord_addedLetter(word_array);

		return similarWords;
	}

	public SpellChecker(BinarySearchTree dictionaryTree){
		this.dictionaryTree = dictionaryTree;
	}
	private void similarWord_switch(char[] word){
		char[] tmp;
		for(int i = 0; i < word.length - 1; i++){
			tmp = word.clone();
			String similarWord = new String(swap(i, i+1, tmp));
			similarWords.add(similarWord);
		}
	}

	private void similarWord_replacedLetter(char[] word){
		char[] tmp;
		for(int j = 0; j < word.length; j++){
			for(char c : alphabetArray){
				if(c == word[j]){
					continue;
				}
				tmp = word.clone();
				String similarWord = new String(replace(c, j, tmp));
				similarWords.add(similarWord);
			}
		}
	}

	private void similarWord_removedLetter(char[] word){
		char[] tmp;
		for(int i = 0; i < word.length; i++){
			tmp = word.clone();
			String similarWord = new String(remove(i, tmp));
			similarWords.add(similarWord);

		}
	}

	private void similarWord_addedLetter(char[] word){
		char[] tmp;
		for(int i = 0; i < word.length + 1; i++){
			for(char c : alphabetArray){
				tmp = word.clone();
				String similarWord = new String(add(c, i, tmp));
				similarWords.add(similarWord);
			}
		}
	}
	// THE FOLLOWING FOUR METHODS TAKE ONE ARRAY OF CHARACTERS AS INPUT AS WELL AS ANY
	// OTHER INFORMATION THEY MIGHT NEED TO GENERATE A NEW WORD, EITHER BY REPLACING, SWAPPING, REMOVING
	// OR ADDING LETTERS!

	private char[] swap(int a, int b, char[] word){
		// Given two indices, and an array of characters, swaps the letters at index a and b.
		char tmp = word[a];
		word[a] = word[b];
		word[b] = tmp;
		return word;
	}

	private char[] replace(char c, int index, char[] word){
		// Given an ASCII-value and an index, replaces the char at the index with the new character with given ASCII-value.
		word[index] = c;
		return word;
	}

	private char[] remove(int index, char[] word){
		// Creates a new word that contains one less character, and copies each letter except the one to be removed
		// from the old word to the new one.
		char[] new_word = new char[word.length - 1];

		int increment= 0;	
		for(int i = 0; i < word.length - 1; i++){
			if(i == index){
				increment++;
				i++;
			}
			new_word[i-increment] = word[i];				
		}
		return new_word;
	}

	private char[] add(char c, int index, char[] word){
		// Creates a new word that contains one more character, and copies each letter from the old word to the new one, in addition
		// to inserting the new word at specified index with specified ASCII value.
		// MAGIC! DO NOT TOUCH
		char[] new_word = new char[word.length + 1];

		int increment = 0;
		for(int i = 0;i < new_word.length; i++){
			if(i == index){
				increment++;
				new_word[index] = c;
			}else{				
			new_word[i] = word[i-increment];
			}
		}
		return new_word;
	}
}
