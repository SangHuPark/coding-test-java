class Solution {
    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};
    
    static int N;
    
    public int[][] solution(int n) {
        N = n;
        
        int[][] answer = new int[N][N];
        answer[0][0] = 1;
        
        int row = 0, col = 0;
        int delta = 0, num = 2;
        while (num <= n*n) {
            int nextRow = row + deltaRow[delta];
            int nextCol = col + deltaCol[delta];

            if(isArrange(nextRow, nextCol) || answer[nextRow][nextCol] != 0) {
                delta = (delta+1) % 4;
                continue;
            }
            
            row = nextRow;
            col = nextCol;
            answer[nextRow][nextCol] = num++;
        }
        
        return answer;
    }
    
    static boolean isArrange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}