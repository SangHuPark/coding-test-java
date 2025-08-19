class Solution {
    public int[] solution(int[] arr, int[] queries) {
        int start = 0, end = 0;
        for(int idx = 0; idx < queries.length; idx++) {
            int query = queries[idx];
            if(idx % 2 == 0) {
                if (end == 0)
                    end = query;
                else
                    end = query + start;
            } else {
                if (start == 0)
                    start = query;
                else
                    start += query;
            }
        }
        
        int idx = 0;
        int[] answer = new int[end - start + 1];
        for(int arrIdx = start; arrIdx <= end; arrIdx++) {
            answer[idx++] = arr[arrIdx];
        }
        
        return answer;
    }
}