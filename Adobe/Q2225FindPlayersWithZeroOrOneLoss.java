package Adobe;

import java.util.*;

/*
You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the 
player winneri defeated player loseri in a match.
Return a list answer of size 2 where:
    answer[0] is a list of all players that have not lost any matches.
    answer[1] is a list of all players that have lost exactly one match.

The values in the two lists should be returned in increasing order.
Note:
    You should only consider the players that have played at least one match.
    The testcases will be generated such that no two matches will have the same outcome.

Example 1:
Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
Output: [[1,2,10],[4,5,7,8]]
Explanation:
Players 1, 2, and 10 have not lost any matches.
Players 4, 5, 7, and 8 each have lost one match.
Players 3, 6, and 9 each have lost two matches.
Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].

Example 2:
Input: matches = [[2,3],[1,3],[5,4],[6,4]]
Output: [[1,2,5,6],[]]
Explanation:
Players 1, 2, 5, and 6 have not lost any matches.
Players 3 and 4 each have lost two matches.
Thus, answer[0] = [1,2,5,6] and answer[1] = [].
*/

public class Q2225FindPlayersWithZeroOrOneLoss {

    public List<List<Integer>> findWinners(int[][] matches) {

        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> allPlayers = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> losers = new HashSet<>();
        Set<Integer> nonLosers = new HashSet<>();
        Set<Integer> lostOneMatch = new HashSet<>();

        // iterate and add all players and loser players
        for (int[] match : matches) {
            allPlayers.add(match[0]);
            allPlayers.add(match[1]);
            losers.add(match[1]);

            int losingCount = map.getOrDefault(match[1], 0);
            map.put(match[1], losingCount + 1);
        }

        // add all nonLoser players
        for (Integer player : allPlayers) {
            if (!losers.contains(player)) {
                nonLosers.add(player);
            }
        }

        // add players whose lost count is exactly 1
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                lostOneMatch.add(entry.getKey());
            }

        }

        // sort both the list and add in answer
        List<Integer> nonLosersList = new ArrayList<>(nonLosers);
        Collections.sort(nonLosersList);
        ans.add(new ArrayList<>(nonLosersList));

        List<Integer> lostOneMatchList = new ArrayList<>(lostOneMatch);
        Collections.sort(lostOneMatchList);
        ans.add(lostOneMatchList);

        return ans;
    }
}