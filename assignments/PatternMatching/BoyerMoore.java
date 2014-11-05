/*
 * @author Ivar Haugaløkken Stangeby
 */
public class BoyerMoore{
	
	private char[] needle;
	private char[] haystack;

	private int[] shiftTable;

	public BoyerMoore(String needle, String haystack){
		this.needle	= needle.toCharArray();
		this.haystack = haystack.toCharArray();
	}	



	/** 
	 * Generates the table containing bad
	 * character shift values.Shift values for characters not occuring
	 * in the needle is set to the length
	 * of the needle.And if the shift value is higher than the shift value
	 * for the wildcard occuring the furthest to the right, then use the wildcard
	 * shift value.
	 */
	private void generateBadCharTable(){
		int[] table = new int[256];
		int minWildcardShift = needle.length;
		for (int i = 0; i < table.length; ++i) {
			table[i] = needle.length;	
		}
		for (int i = 0; i < needle.length - 1; ++i) {
			table[needle[i]] = needle.length - 1 - i;	
			System.out.println("Setting character shift for " + needle[i] + " to " + (needle.length - 1 - i));
		}
		this.shiftTable = table;
	}

	/** 
	 * Returns a substring given a start index,
	 * a stop index.These substrings are the strings that matches
	 * our needle
	 *
	 * @param int start the index denoting the start of the substring in the haystack. Inclusive
	 * @param int stop  the index denoting the end of the substring in the haystack. Not inclusive
	 * @return Returns a Substring as a character array, these are the substrings that matches the needle.
	 */
	private String getSubString(int start, int stop){
		return new String(haystack).substring(start, stop);
	}

	public void startSearch(){
		int windowStart = 0;
		int windowStop = needle.length;
		generateBadCharTable();
		while(windowStop <= haystack.length){
			boolean fullMatch = true;
			System.out.println("Current window: " + getSubString(windowStart, windowStop));
			for (int i = windowStop-1, j = needle.length - 1; i >= windowStart; i--){
				if(needle[j] == '_'){
					System.out.println("Wildcard is matching " +  haystack[i]);		
					j--;
				}else if(needle[j] == haystack[i]){
					System.out.println(needle[j] + " is matching " + haystack[i]);
					j--;
				} else {
					System.out.println(needle[j] + " is not matching " + haystack[i]);
					System.out.println("Shifting window by: " + shiftTable[haystack[i]]);
					windowStart += Math.min(shiftTable['_'], shiftTable[haystack[i]]);
					windowStop += Math.min(shiftTable['_'], shiftTable[haystack[i]]);
					fullMatch = false;
					break;
				}
			}
			if(fullMatch){
				System.out.println("Found a match as: " + getSubString(windowStart, windowStop));	
				windowStart += 1;
				windowStop += 1;
			}
			System.out.println("Current windowstart: " + windowStart);
			System.out.println("Current windowstop: " + windowStop);
		}
	}
}
