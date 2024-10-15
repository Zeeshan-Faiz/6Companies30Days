package Google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
There are n cities numbered from 1 to n. You are given an array edges of size n-1, where 
edges[i] = [ui, vi] represents a bidirectional edge between cities ui and vi. There exists a 
unique path between each pair of cities. In other words, the cities form a tree.
A subtree is a subset of cities where every city is reachable from every other city in the 
subset, where the path between each pair passes through only the cities from the subset. Two 
subtrees are different if there is a city in one subtree that is not present in the other.
For each d from 1 to n-1, find the number of subtrees in which the maximum distance between any 
two cities in the subtree is equal to d.
Return an array of size n-1 where the dth element (1-indexed) is the number of subtrees in which 
the maximum distance between any two cities is equal to d.
Notice that the distance between the two cities is the number of edges in the path between them.

Example 1:
Input: n = 4, edges = [[1,2],[2,3],[2,4]]
Output: [3,4,0]
Explanation:
The subtrees with subsets {1,2}, {2,3} and {2,4} have a max distance of 1.
The subtrees with subsets {1,2,3}, {1,2,4}, {2,3,4} and {1,2,3,4} have a max distance of 2.
No subtree has two nodes where the max distance between them is 3.

Example 2:
Input: n = 2, edges = [[1,2]]
Output: [1]

Example 3:
Input: n = 3, edges = [[1,2],[2,3]]
Output: [2,1]
*/

public class Q1617CountMaxDistance {

    List<Integer>[] tree, subTree;
    int subTreeRoot, maxPath;
    boolean[] inStack; // do not allow to travel upwards in the rooted tree
    int[] ans;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        buildTree(n, edges);
        ans = new int[n - 1];
        solve(0);
        return ans;
    }

    @SuppressWarnings("unchecked")
    private void buildTree(int n, int[][] edges) {
        tree = new List[n];
        subTree = new List[n];
        inStack = new boolean[n];
        for (int i = 0; i < n; ++i) {
            tree[i] = new ArrayList<>();
            subTree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            tree[e[0] - 1].add(e[1] - 1);
            tree[e[1] - 1].add(e[0] - 1);
        }
    }

    private void solve(int i) {
        subTreeRoot = i;
        inStack[i] = true;
        buildSubTree(0, 0, Collections.singletonList(i), new ArrayList<>());
        for (int e : tree[i]) {
            if (inStack[e]) {
                continue;
            }
            solve(e);
        }
    }

    private void buildSubTree(int i, int j, List<Integer> fronteer, List<Integer> next) {
        if (i >= fronteer.size()) {
            buildNextLevel(next);
            return;
        }
        int curr = fronteer.get(i);
        if (j >= tree[curr].size()) {
            buildSubTree(i + 1, 0, fronteer, next);
        } else {
            int succ = tree[curr].get(j);
            if (inStack[succ]) {
                buildSubTree(i, j + 1, fronteer, next);
                return;
            }
            inStack[succ] = true;
            next.add(succ);
            subTree[curr].add(succ);
            buildSubTree(i, j + 1, fronteer, next);
            next.remove(next.size() - 1);
            subTree[curr].remove(subTree[curr].size() - 1);
            buildSubTree(i, j + 1, fronteer, next);
            inStack[succ] = false;
        }
    }

    private void buildNextLevel(List<Integer> next) {
        if (next.isEmpty()) { // subTree is done, add it to the answer
            maxPath = 0;
            computeMaxPath(subTreeRoot);
            if (maxPath > 1) {
                ++ans[maxPath - 2];
            }
        } else {
            buildSubTree(0, 0, next, new ArrayList<>());
        }
    }

    private int computeMaxPath(int i) {
        int max = 0, prevMax = 0;
        for (int child : subTree[i]) {
            int h = computeMaxPath(child);
            if (h >= max) {
                prevMax = max;
                max = h;
            } else {
                prevMax = Integer.max(prevMax, h);
            }
        }
        maxPath = Integer.max(maxPath, prevMax + max + 1);
        return max + 1;
    }
}