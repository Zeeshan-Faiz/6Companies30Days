package Walmart;

/*
Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number 
of the battleships on board.
Battleships can only be placed horizontally or vertically on board. In other words, they can only 
be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any 
size. At least one horizontal or vertical cell separates between two battleships (i.e., there 
are no adjacent battleships).

Example 1:
Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
Output: 2

Example 2:
Input: board = [["."]]
Output: 0
*/

public class Q419BattleShipsInBoard {

    public int countBattleships(char[][] board) {
        
        int nr = board.length;
        int nc = board[0].length;
        int count = 0;
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (board[i][j] == 'X') {
                    count++;
                    dfs(board, i, j);
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] board, int row, int col) {
        
        int nr = board.length;
        int nc = board[0].length;
        board[row][col] = '.';
        if ((row - 1) >= 0 && board[row - 1][col] == 'X') {
            dfs(board, row - 1, col);
        }
        if ((row + 1) < nr && board[row + 1][col] == 'X') {
            dfs(board, row + 1, col);
        }
        if (((col - 1) >= 0) && board[row][col - 1] == 'X') {
            dfs(board, row, col - 1);
        }
        if ((col + 1) < nc && board[row][col + 1] == 'X') {
            dfs(board, row, col + 1);
        }
    }
}