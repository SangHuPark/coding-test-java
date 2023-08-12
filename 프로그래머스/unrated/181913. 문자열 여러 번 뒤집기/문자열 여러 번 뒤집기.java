class Solution {
    public String solution(String my_string, int[][] queries) {

        /* for (int[] query : queries) {
            int s = query[0];
            int e = query[1];
            int idx = e;
            char[] ans = my_string.toCharArray();
            for (int i = s; i <= e; i++)
                ans[i] = ans[idx--];
        }
        
        return new String(ans); */
        
        char[] arr = my_string.toCharArray();
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            for (int j = start; j <= (start+end)/2; j++) {
                char temp = arr[j];
                arr[j] = arr[start+end-j];
                arr[start+end-j] = temp;
            }
        }
        return new String(arr);
    }
}