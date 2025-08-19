class Solution {
    static final int[] deltaRow = {-1, -1, -1, 0, 1, 1, 1, 0};
    static final int[] deltaCol = {-1, 0, 1, 1, 1, 0, -1, -1};
    
    static int len;
    
    public int solution(int[][] board) {
        len = board.length;
        
        int answer = 0;
        boolean[][] checked = new boolean[board.length][board.length];
        
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board.length; col++) {
                if(board[row][col] == 0) continue;
                
                checked[row][col] = true;
                for(int idx = 0; idx < 8; idx++) {
                    int nextRow = row + deltaRow[idx];
                    int nextCol = col + deltaCol[idx];
                    
                    if(isArrange(nextRow, nextCol)) continue;
                    
                    checked[nextRow][nextCol] = true;
                }
            }
        }
        
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board.length; col++) {
                // System.out.print(checked[row][col] ? "T " : "F ");
                
                if(checked[row][col]) continue;
                
                answer++;
            }
            // System.out.println();
        }
        
        return answer;
    }
    
    public boolean isArrange(int row, int col) {
        return row < 0 || row >= len || col < 0 || col >= len;
    }
}