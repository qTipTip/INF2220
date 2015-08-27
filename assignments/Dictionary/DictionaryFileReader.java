import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
public class DictionaryFileReader{
	Scanner scanner = null;
	ArrayList<String> readWords;
	DictionaryFileReader(){
		try {
			scanner = new Scanner(new BufferedReader(new FileReader("ordbok1_utf.txt")));
			readWords = new ArrayList<>();
			while(scanner.hasNext()){
				readWords.add(scanner.next());
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} finally {
			if (scanner != null){
				scanner.close();
			}
		}
	}

	public ArrayList<String> getWords(){
		return readWords;
	}
}
