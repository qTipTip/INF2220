import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class DictionaryReader{
	Scanner scanner;
	String[] words;

	File infile = new File("~/university/inf2220/obligs/oblig1/ordbok1_utf.txt");
	public DictionaryReader(){

		try {
			Scanner scanner = new Scanner(infile);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		System.out.println(scanner);
		while(scanner.hasNextLine()){
			String word = scanner.nextLine();
			System.out.println(word);
		}
	}

}
