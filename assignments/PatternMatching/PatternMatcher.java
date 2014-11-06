import java.util.Scanner;
import java.io.File;

public class PatternMatcher{

	public static void main(String[] args){
		String needle = "";
		String haystack = "";	
		try {
			needle = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
			haystack = new Scanner(new File(args[1])).useDelimiter("\\Z").next();
		} catch(Exception e) {
			System.out.println();	
			System.out.println("Please specify a needle and a haystack as command line arguments");
			System.out.println();
		}
		
		BoyerMoore needleFinder = new BoyerMoore(needle, haystack);
		needleFinder.startSearch();
	}
}
