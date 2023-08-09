class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int min = Integer.MAX_VALUE;
        int[] numArray = new int[queries.length];
        int idx = 0;
        
//         for (int i = 0; i < queries.length; i++) {
//             for (int j = queries[i][0]; j < queries[i][1] + 1; j++) {
//                 if (queries[i][2] < arr[j] && num > arr[j])
//                     num = arr[j];
//             }
//             numArray[i] = num != Integer.MAX_VALUE ? num : -1;
//             num = Integer.MAX_VALUE;
            
//         }

        for (int[] query : queries) {
            int s = query[0], e = query[1], k = query[2];
            
            for (int i = s;i <= e;i++)
                if (arr[i] > k)
                    min = Math.min(arr[i], min);

            numArray[idx++] = min == Integer.MAX_VALUE ? -1 : min;
            min = Integer.MAX_VALUE;
        }
        
        return numArray;
    }
}