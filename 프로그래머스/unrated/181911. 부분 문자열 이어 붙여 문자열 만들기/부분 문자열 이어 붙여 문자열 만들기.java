class Solution {
    public String solution(String[] my_strings, int[][] parts) {
        String ans = new String();
        int idx = 0;
        
        for (int[] arr : parts) {
            int s = arr[0];
            int e = arr[1];
            ans += my_strings[idx].substring(s, e + 1);
            idx++;
        }

        return ans;
    }
}