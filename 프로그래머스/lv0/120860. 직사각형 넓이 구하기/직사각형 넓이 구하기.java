class Solution {
    public int solution(int[][] dots) {
        int height = 0;
        int leng = 0;
        int x = dots[0][0]; // 1
        int y = dots[0][1]; // 1
        
        for (int i = 1; i < dots.length; i++) {
            if (dots[i][0] == x) {
                height = dots[i][1] > y ? dots[i][1] - y : y - dots[i][1];
            }
            if (dots[i][1] == y) {
                leng = dots[i][0] > x ? dots[i][0] - x : x - dots[i][0];
            }
        }
        
        return height * leng;
    }
}