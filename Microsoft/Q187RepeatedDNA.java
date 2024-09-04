package Microsoft;

/*
The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
    For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.
Given a string s that represents a DNA sequence, return all the 10-letter-long sequences 
(substrings) that occur more than once in a DNA molecule. You may return the answer in any order.

Example 1:
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]

Example 2:
Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]
*/

public class Q187RepeatedDNA {

    public List<String> findRepeatedDnaSequences(String s) {

        HashMap<String, Integer> freq = new HashMap<>();

        // find all substrings of length 10 and add in map
        // if the substring is already present in map then increase it's counter
        for (int i = 0; i < s.length() - 9; i++) {
            String dna = s.substring(i, i + 10);
            freq.put(dna, freq.getOrDefault(dna, 0) + 1);
        }

        List<String> list = new ArrayList();
        // add all the substrings which has counter > 1
        for (Map.Entry<String, Integer> e : freq.entrySet()) {
            if (e.getValue() > 1)
                list.add(e.getKey());
        }
        return list;
    }
}