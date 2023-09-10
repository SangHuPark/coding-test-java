class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        int row = 0;
        int col = 0;
        int cnt = 1;
        int direction = 0;
        
        while (cnt <= n*n) {
            answer[row][col] = cnt++;
            
            if (direction == 0) { // 오른쪽 방향으로 이동
                if (col == n - 1 || answer[row][col + 1] != 0) {
                    direction = 1;
                    row++;
                } else {
                    col++;
                }
            } else if (direction == 1) { // 아래쪽 방향으로 이동
                if (row == n - 1 || answer[row + 1][col] != 0) {
                    direction = 2;
                    col--;
                } else {
                    row++;
                }
            } else if (direction == 2) { // 왼쪽 방향으로 이동
                if (col == 0 || answer[row][col - 1] != 0) {
                    direction = 3;
                    row--;
                } else {
                    col--;
                }
            } else if (direction == 3) { // 위쪽 방향으로 이동
                if (row == 0 || answer[row - 1][col] != 0) {
                    direction = 0;
                    col++;
                } else {
                    row--;
                }
            }   
        }
        
        return answer;
    }
}