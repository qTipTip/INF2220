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
	 * shift value. Keeps track of the number of trailing wildcards, as these alter the
	 * bad character shift values needed.
	 */
	private void generateBadCharTable(){
		int[] table = new int[256];
		char[] tempNeedle = stripChar(needle);
		for (int i = 0; i < table.length; ++i) {
			table[i] = tempNeedle.length;	
		}
		for (int i = 0; i < tempNeedle.length - 1 ; ++i) {
			table[tempNeedle[i]] = tempNeedle.length - 1 - i;	
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
			for (int i = windowStop-1, j = needle.length - 1; i >= windowStart; i--){
				if(needle[j] == '_'){
					j--;
				}else if(needle[j] == haystack[i]){
					j--;
				} else {
					windowStart += shiftTable[haystack[i]];
					windowStop += shiftTable[haystack[i]];
					fullMatch = false;
					break;
				}
			}
			if(fullMatch){
				System.out.println("======================");
				System.out.println("Needle : " + new String(needle));
				System.out.println("----------------------");
				System.out.println("Match  : " + getSubString(windowStart, windowStop));
				System.out.println("Start  : " + windowStart);
				System.out.println("Stop   : " + (windowStop-1));
				windowStart += 1;
				windowStop += 1;
			}
		}
	}

	private char[] stripChar(char[] array){
		int end = 0;
		int start = 0;
		for(int i = array.length - 1; i >= 0; i--){
			if(array[i] != '_'){
				end = i;
				break;		
			}
		}
		for(int i = end; i >= 0; i--){
			if(array[i] == '_'){
				start = i+1;	
				break;
			}
		}
		System.out.println(new String(array).substring(start, end + 1).toCharArray());
		return new String(array).substring(start, end + 1).toCharArray();
	}
}
