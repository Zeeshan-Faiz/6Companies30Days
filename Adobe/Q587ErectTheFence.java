package Adobe;

/*
You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
Fence the entire garden using the minimum length of rope, as it is expensive. The garden is well-fenced 
only if all the trees are enclosed.
Return the coordinates of trees that are exactly located on the fence perimeter. You may return 
the answer in any order.

Example 1:
Input: trees = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
Explanation: All the trees will be on the perimeter of the fence except the tree at [2, 2], which will be inside the fence.

Example 2:
Input: trees = [[1,2],[2,2],[4,2]]
Output: [[4,2],[2,2],[1,2]]
Explanation: The fence forms a line that passes through all the trees.
*/

public class Q587ErectTheFence {

    public int[][] outerTrees(int[][] trees) {
        
        if (trees.length <= 3)
            return trees;
        int current = 0;
        for (int i = 1; i < trees.length; i++) {
            if (trees[i][0] < trees[current][0])
                current = i;
        }
        boolean[] seens = new boolean[trees.length];
        seens[current] = true;
        int next = (current + 1) % trees.length;
        int dist = getDist(trees[current], trees[next]);
        int[] arr = new int[trees.length];
        int idx = 0;
        for (int i = 0; i < trees.length; i++) {
            if (seens[i])
                continue;
            int XPro = getXPro(trees[current], trees[next], trees[i]);
            if (XPro > 0) {
                next = i;
                dist = getDist(trees[current], trees[next]);
                idx = 0;
            } else if (XPro == 0) {
                int distNext = getDist(trees[current], trees[i]);
                if (distNext > dist) {
                    arr[idx++] = next;
                    next = i;
                    dist = distNext;
                } else {
                    arr[idx++] = i;
                }
            }
        }
        for (int i = 0; i < idx; i++) {
            seens[arr[i]] = true;
        }
        seens[next] = true;
        int target = current;
        current = next;
        while (current != target) {
            next = target;
            dist = getDist(trees[current], trees[next]);
            idx = 0;
            for (int i = 0; i < trees.length; i++) {
                if (seens[i])
                    continue;
                int XPro = getXPro(trees[current], trees[next], trees[i]);
                if (XPro > 0) {
                    next = i;
                    dist = getDist(trees[current], trees[next]);
                    idx = 0;
                } else if (XPro == 0) {
                    int distNext = getDist(trees[current], trees[i]);
                    if (distNext > dist) {
                        arr[idx++] = next;
                        next = i;
                        dist = distNext;
                    } else {
                        arr[idx++] = i;
                    }
                }
            }
            for (int i = 0; i < idx; i++) {
                seens[arr[i]] = true;
            }
            seens[next] = true;
            current = next;
        }
        int count = 0;
        for (boolean seen : seens) {
            if (seen)
                count++;
        }
        int[][] res = new int[count][];
        idx = 0;
        for (int i = 0; i < trees.length; i++) {
            if (seens[i])
                res[idx++] = trees[i];
        }
        return res;
    }

    public int getDist(int[] p1, int[] p2) {
        return (p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]);
    }

    public int getXPro(int[] p1, int[] p2, int[] p3) {
        return (p2[0] - p1[0]) * (p3[1] - p2[1]) - (p3[0] - p2[0]) * (p2[1] - p1[1]);
    }
}