package Microsoft;

/*
An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by 
rounding down the average of the cell and the eight surrounding cells (i.e., the average of the 
nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not present, 
we do not consider it in the average (i.e., the average of the four cells in the red smoother).
Given an m x n integer matrix img representing the grayscale of an image, return the image 
after applying the smoother on each cell of it.

Example 1:
Input: img = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[0,0,0],[0,0,0],[0,0,0]]
Explanation:
For the points (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the points (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0

Example 2:
Input: img = [[100,200,100],[200,50,200],[100,200,100]]
Output: [[137,141,137],[141,138,141],[137,141,137]]
Explanation:
For the points (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
For the points (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
For the point (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
*/

public class Q661ImageSmoother {

    public int[][] imageSmoother(int[][] img) {
        int n = img.length;
        int m = img[0].length;

        int ans[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = helper(img, i, j);
            }
        }
        return ans;
    }

    static int helper(int arr[][], int i, int j) {
        
        int n = arr.length, m = arr[0].length;
        int count = 1;
        int sum = arr[i][j];

        // Up Element
        if (i - 1 >= 0) {
            sum += arr[i - 1][j];
            count++;
            // upper left diagonal
            if (j - 1 >= 0) {
                sum += arr[i - 1][j - 1];
                count++;
            }
            // upper right diagonal
            if (j + 1 < m) {
                sum += arr[i - 1][j + 1];
                count++;
            }
        }

        // Down Element
        if (i + 1 < n) {
            sum += arr[i + 1][j];
            count++;
            // Left down diagonal
            if (j - 1 >= 0) {
                sum += arr[i + 1][j - 1];
                count++;
            }
            // Right down diagonal
            if (j + 1 < m) {
                sum += arr[i + 1][j + 1];
                count++;
            }
        }
        // Right Element
        if (j + 1 < m) {
            sum += arr[i][j + 1];
            count++;
        }
        // left element
        if (j - 1 >= 0) {
            sum += arr[i][j - 1];
            count++;
        }

        // return average
        return sum / count;
    }
}