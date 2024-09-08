package Adobe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled 
from 1 to 10 as shown in the figure above.
Given the array reservedSeats containing the numbers of seats already reserved, for example, 
reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.
Return the maximum number of four-person groups you can assign on the cinema seats. A four-person
group occupies four adjacent seats in one single row. Seats across an aisle (such as [3,3] and 
[3,4]) are not considered to be adjacent, but there is an exceptional case on which an aisle
split a four-person group, in that case, the aisle split a four-person group in the middle, which 
means to have two people on each side.

Example 1:
Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
Output: 4
Explanation: The figure above shows the optimal allocation for four groups, where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.

Example 2:
Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
Output: 2

Example 3:
Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
Output: 4
*/

public class Q1386CinemaSeatAllocation {
    
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] i : reservedSeats) {
            map.putIfAbsent(i[0], new ArrayList<>());
            map.get(i[0]).add(i[1]);
        }

        int ans = 2 * (n - map.size());

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> seats = entry.getValue();

            boolean isLeftAisle = false, isRightAisle = false, isMiddle = false;

            for (int seat : seats) {

                if (seat >= 2 && seat <= 5)
                    isLeftAisle = true;
                if (seat >= 6 && seat <= 9)
                    isRightAisle = true;
                if (seat >= 4 && seat <= 7)
                    isMiddle = true;

                if (isLeftAisle && isRightAisle && isMiddle) {
                    break;
                }
            }

            if (!isLeftAisle)
                ans += 1;
            if (!isRightAisle)
                ans += 1;
            if (isLeftAisle && isRightAisle && !isMiddle)
                ans += 1;

        }

        return ans;
    }
}