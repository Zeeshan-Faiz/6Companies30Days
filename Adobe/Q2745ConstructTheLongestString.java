package Adobe;

/*
You are given three integers x, y, and z. You have x strings equal to "AA", y strings equal to "BB", 
and z strings equal to "AB". You want to choose some (possibly all or none) of these strings and 
concatenate them in some order to form a new string. This new string must not contain "AAA" or "BBB" 
as a substring. Return the maximum possible length of the new string.
A substring is a contiguous non-empty sequence of characters within a string.

Example 1:
Input: x = 2, y = 5, z = 1
Output: 12
Explanation: We can concactenate the strings "BB", "AA", "BB", "AA", "BB", and "AB" in that order. Then, our new string is "BBAABBAABBAB". 
That string has length 12, and we can show that it is impossible to construct a string of longer length.

Example 2:
Input: x = 3, y = 2, z = 2
Output: 14
Explanation: We can concactenate the strings "AB", "AB", "AA", "BB", "AA", "BB", and "AA" in that order. Then, our new string is "ABABAABBAABBAA". 
That string has length 14, and we can show that it is impossible to construct a string of longer length.
*/

public class Q2745ConstructTheLongestString {

    public int longestString(int x, int y, int z) {

        // find the min between x and y
        int ans = Math.min(x, y);
        ans = ans + (ans + 1 + z);
        if (x == y) {
            ans = ans - 1;
        }
        return ans * 2;
    }
}