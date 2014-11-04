// This is the main class for the pattern matching program
// for the third mandatory assignment in INF2220 - Fall 2014.
// 
// Given a needle and a haystack, it prints out every occurence of the needle along
// with their position in the haystack. 
class PatternMatcher{

	public static void main(String[] args){
		
		String needle = "";
		String haystack = "";

		try {
			needle = args[0];
			haystack = args[1];
		} catch(Exception e) {
			System.out.println();	
			System.out.println("Please specify a needle and a haystack as command line arguments");
			System.out.println();
		}
		
		BoyerMoore needleFinder = new BoyerMoore(needle, haystack);
	}
}
