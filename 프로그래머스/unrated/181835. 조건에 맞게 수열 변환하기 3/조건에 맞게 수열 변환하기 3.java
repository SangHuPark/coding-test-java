class Solution {
    public int[] solution(int[] arr, int k) {
        int[] answer = new int[arr.length];
        boolean bool = k % 2 == 1 ? true : false;
        
        if (bool) {
            for (int i = 0; i < arr.length; i++)
                answer[i] = arr[i] * k;
        } else { 
            for (int i = 0; i < arr.length; i++)
                answer[i] = arr[i] + k;
        }
        
        return answer;
    }
}