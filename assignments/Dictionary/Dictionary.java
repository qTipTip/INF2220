import java.util.ArrayList;
public class Dictionary{

	BinarySearchTree dictionaryTree = new BinarySearchTree();	
	DictionaryFileReader fileReader = new DictionaryFileReader();
	SpellChecker wordChecker = new SpellChecker(dictionaryTree);
	private int positiveSearches = 0;
	private long elapsedTime = 0;
	public void populateDictionary(){
		for(String word : fileReader.getWords()){
			dictionaryTree.insert(word);
		}
	}
	public boolean lookupWord(String word){
		//This function returns true if the word is present in the dictionary, and false if the word either does not exist, or exists in an altered form

		if(dictionaryTree.contains(word)){
			System.out.println("Word found: " + word);
			positiveSearches++;
			return true;
		} else {
			long startTime = System.nanoTime();
			System.out.println("Suggested words for: " + word);
			for(String suggestion : wordChecker.checkWord(word)){
				if (dictionaryTree.contains(suggestion)){
					System.out.println(suggestion);	
				}
			}
			long endTime = System.nanoTime();
			elapsedTime = (endTime - startTime) / 1000000; //Converting to milliseconds
			return false;
		}
	}

	public void printContents(){
		dictionaryTree.printTree();
	}
	public void printStatistics(){
		dictionaryTree.printStatistics();
		System.out.println("Total number of successful searches: " + positiveSearches);
	}

	public int getPositiveSearches(){
		return positiveSearches;
	}

	public void removeWord(String word){
		if(!dictionaryTree.contains(word)){
			System.out.println(word + " not present in dictionary");
			return;
		}
		dictionaryTree.remove(word);
		System.out.println(word + " removed from dictionary...");
	}
	public void addWord(String word){
		if(dictionaryTree.contains(word)){
			System.out.println(word + " already in dictionary...");
			return;
		}
		dictionaryTree.insert(word);
		System.out.println(word + " added to dictionary... ");

	}

	public void printMinMax(){
		System.out.println("Min : " + dictionaryTree.findMin());
		System.out.println("Max : " + dictionaryTree.findMax());
	}

	public long getTime(){
		return elapsedTime;
	}

}

