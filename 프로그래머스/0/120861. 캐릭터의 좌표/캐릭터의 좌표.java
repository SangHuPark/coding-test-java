class Solution {
    // up, down, left, right
    static final String up = "up", down = "down", left = "left", right = "right";
    static final int[] deltaX = {0, 0, -1, 1};
    static final int[] deltaY = {1, -1, 0, 0};
    
    static int N, M;
    
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = new int[2];
        
        N = board[0] / 2;
        M = board[1] / 2;
        int x = 0, y = 0;
        for(String key : keyinput) {
            int nextX = x;
            int nextY = y;
            if(up.equals(key)) {
                nextX = x + deltaX[0];
                nextY = y + deltaY[0];
            } else if(down.equals(key)) {
                nextX = x + deltaX[1];
                nextY = y + deltaY[1];
            } else if(left.equals(key)) {
                nextX = x + deltaX[2];
                nextY = y + deltaY[2];
            } else if(right.equals(key)) {
                nextX = x + deltaX[3];
                nextY = y + deltaY[3];
            }
            
            if(isArrange(nextX, nextY))
                continue;
            
            System.out.println(nextX + " " + nextY);
            
            x = nextX;
            y = nextY;
        }
        
        answer[0] = x;
        answer[1] = y;
        
        return answer;
    }
    
    public static boolean isArrange(int x, int y) {
        return x < -N || x > N || y < -M || y > M;
    }
}