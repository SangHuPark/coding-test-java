class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = n - 1;
        int colEnd = n - 1;
        int cnt = 1;
        
        while (rowStart <= rowEnd && colStart <= colEnd) {
            
            for (int i = colStart; i <= colEnd; i++) {
                answer[rowStart][i] = cnt++;
            }
            rowStart++;

            // 위에서 아래로
            for (int i = rowStart; i <= rowEnd; i++) {
                answer[i][colEnd] = cnt++;
            }
            colEnd--;

            // 오른쪽에서 왼쪽으로
            for (int i = colEnd; i >= colStart; i--) {
                answer[rowEnd][i] = cnt++;
            }
            rowEnd--;

            // 아래에서 위로
            for (int i = rowEnd; i >= rowStart; i--) {
                answer[i][colStart] = cnt++;
            }
            colStart++;
        }
        
        return answer;
    }
}