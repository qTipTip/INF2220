# Generalized Pattern Matching With Wildcards
## Third mandatory assignment in INF2220
The task at hand is to implement a generalization of the Boyer-Moore-Horspool bad
character shift algorithm to allow wildcards to be used in the needle.

* Implement a pattern matching algorithm using bad character shift.
* Algorithm must be able to handle several wildcards.
* Implementation must be able to find all occurences of the needle and report the position.
* Program must format the results in an easy-to-read manner.
* The program must output the text in the haystack that matched the needle.
* The program must take two filenames as an argument. First being the needle and second the haystack.

### Bad Character Shift explained:
Bad character shift is a table over all possible characters, that specify how far to shift
if one finds the character at the end of the needle. That means you have to subtract the
number of characters already matched before jumping. Each character is given a shift valuethat is equal to the length of the needle minus the index of the last occurence of the character. Any character not occuring in the needle is given a character shift equal to the
length of the word.

#### Preprocessing
Create a table of 256 members, one for each character in the alphabet. Initialize all the values with the length of the needle.
Generate the bad character shift values for each character in the needle.


#### Algorithm Complexity

