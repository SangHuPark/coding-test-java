class Solution {
    public int solution(int[] arr, int idx) {
//         int ans = -1;
        
//         for (int i = idx; i < arr.length; i++) {
//             if (arr[i] == 1) {
//                 ans = i;
//                 break;
//             }
//         }

//         return ans;
        
        for (int i = idx; i < arr.length; i++)
            if (arr[i] == 1)
                return i;
        
        return -1;
    }
}