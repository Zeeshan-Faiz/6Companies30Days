package Google;

/*
You may recall that an array arr is a mountain array if and only if:
    arr.length >= 3
    There exists some i with 0 < i < arr.length - 1 such that:
        arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
        arr[i] > arr[i + 1] > ... > arr[arr.length - 1]

Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. 
If such an index does not exist, return -1.
You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
    MountainArray.get(k) returns the element of the array at index k (0-indexed).
    MountainArray.length() returns the length of the array.

Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, 
any solutions that attempt to circumvent the judge will result in disqualification.

Example 1:
Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

Example 2:
Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.
*/

public class Q1095FoundInMountainArray {

    public int findInMountainArray(int target, MountainArray mountainArr) {

        int peak = peakIndex(mountainArr);

        int firstTry = agnosticArray(mountainArr, target, 0, peak);
        if (firstTry != -1)
            return firstTry;
        else
            return agnosticArray(mountainArr, target, peak + 1, MountainArray.length() - 1);

    }

    public int peakIndex(MountainArray mountainArr) {

        int start = 0;
        int end = MountainArray.length() - 1;
        int mid = -1;

        while (start < end) {

            mid = (start + end) / 2;
            if (MountainArray.get(mid) > MountainArray.get(mid + 1))
                // we are in descending part
                end = mid;
            else
                // we are in ascending part
                start = mid + 1;
        }
        return start;
    }

    public int agnosticArray(MountainArray mountainArr, int target, int start, int end) {

        // check whether the given array is Ascending or Descending.
        boolean isAsc = MountainArray.get(start) < MountainArray.get(end);

        while (start <= end) {
            int mid = (start + end) / 2;
            if (MountainArray.get(mid) == target)
                return mid;

            if (isAsc) {
                if (MountainArray.get(mid) < target)
                    start = mid + 1;
                else
                    end = mid - 1;
            } else {
                if (MountainArray.get(mid) > target)
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }

    interface MountainArray {
        public static int get(int index) { return index;}

        public static int length() { return 0;}
    }
}