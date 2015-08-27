import java.util.Scanner;
public class main {
	private static Dictionary myDictionary = new Dictionary();

	public static void main(String[] args){
		myDictionary.populateDictionary();	
		myDictionary.removeWord("familie");
		myDictionary.addWord("familie");

		// User Interface

		boolean running = true;
		System.out.println("====INF2220 DICTIONARY====");

		while(running){
			int selection = mainMenu();
			switch(selection){
				case 1:
					lookupWord();
					break;
				case 2:
					addWord();
					break;
				case 3:
					removeWord();
					break;
				case 4:
					showStatistics();
					break;
				case 5:
					System.out.println("Quitting...");
					running = false;
					break;
			}
		}
	}
	private static Scanner inputScanner = new Scanner(System.in);
	private static int mainMenu(){
		System.out.println("1) Search for a word");	
		System.out.println("2) Add a word");
		System.out.println("3) Remove a word");
		System.out.println("4) Show statistics");
		System.out.println("5) Quit");
		System.out.println("Enter your selection: ");
		try{
			return inputScanner.nextInt();
		} catch (Exception e){
			e.printStackTrace();	
			return 0;
		}
	}
	private static void lookupWord(){
		boolean running = true;
		while(running){
			System.out.println("Please enter word: (q to return to main menu)");	
			String input = inputScanner.next();	
			if(input.equals("q")){
				running = false;	
				continue;
			}
			if(!myDictionary.lookupWord(input)){
				showRunningStatistics();	
				running = false;
				continue;
			}
		}
		return;
	}
	private static void addWord(){
		System.out.println("Please enter a word to add to the dictionary: ");
		myDictionary.addWord(inputScanner.next());
		return;
	}
	private static void removeWord(){
		System.out.println("Please enter a word to remove from the dictionary: ");
		myDictionary.removeWord(inputScanner.next());
		return;
	}
	private static void showStatistics(){
		myDictionary.printStatistics();
		myDictionary.printMinMax();
	}
	private static void showRunningStatistics(){
		System.out.println("Number of successful searches: " + myDictionary.getPositiveSearches());	
		System.out.println("Time used to generate similar words: " + myDictionary.getTime() + "ms");
	}
}


