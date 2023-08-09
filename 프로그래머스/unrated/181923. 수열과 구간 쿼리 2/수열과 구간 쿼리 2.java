class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int num = Integer.MAX_VALUE;
        int[] numArray = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0]; j < queries[i][1] + 1; j++) {
                if (queries[i][2] < arr[j] && num > arr[j])
                    num = arr[j];
            }
            numArray[i] = num != Integer.MAX_VALUE ? num : -1;
            num = Integer.MAX_VALUE;
            
        }
        
        return numArray;
    }
}