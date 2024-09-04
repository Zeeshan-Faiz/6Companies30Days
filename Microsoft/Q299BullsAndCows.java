package Microsoft;

/*
You are playing the Bulls and Cows game with your friend.

You write down a secret number and ask your friend to guess what the number is. When your friend 
makes a guess, you provide a hint with the following info:
    The number of "bulls", which are digits in the guess that are in the correct position.
    The number of "cows", which are digits in the guess that are in your secret number but are 
    located in the wrong position. Specifically, the non-bull digits in the guess that could be 
    rearranged such that they become bulls.

Given the secret number secret and your friend's guess guess, return the hint for your friend's 
guess. The hint should be formatted as "xAyB", where x is the number of bulls and y is the number 
of cows. Note that both secret and guess may contain duplicate digits.

Example 1:
Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1807"
  |
"7810"

Example 2:
Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1123"        "1123"
  |      or     |
"0111"        "0111"
Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
*/

public class Q299BullsAndCows {
    
    int bulls =0;
        int cows =0;
        int[] secretFreq =new int[10];

        int[] guessFreq =new int[10];

        // O(n)
        for(int i=0;i<secret.length();i++){
            char secretChar = secret.charAt(i);

            char guessChar = guess.charAt(i);

            if(secretChar == guessChar){
                bulls++;
            } else{
                secretFreq[secretChar -'0']++;

                guessFreq[guessChar -'0']++;
            }
        }

        // O(1)
        for(int i=0;i<10;i++){
            cows += Math.min(secretFreq[i], guessFreq[i]);
        }

        return bulls +"A"+ cows +"B";
}