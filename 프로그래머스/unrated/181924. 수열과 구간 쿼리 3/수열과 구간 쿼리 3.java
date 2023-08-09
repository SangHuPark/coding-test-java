class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int num1 = 0;
        int num2 = 0;
        int cup = 0;
        
        for (int i = 0; i < queries.length; i++) {
            num1 = queries[i][0];
            num2 = queries[i][1];
            cup = arr[num1];
            arr[num1] = arr[num2];
            arr[num2] = cup;
        }
        
        return arr;
    }
}