class Solution {
    public int[] solution(int[] arr, int n) {
        int tmp = arr.length % 2 == 1 ? 0 : 1;
        
        for (int i = tmp; i < arr.length; i+=2) {
            arr[i] += n;
        }
        
        return arr;
    }
}