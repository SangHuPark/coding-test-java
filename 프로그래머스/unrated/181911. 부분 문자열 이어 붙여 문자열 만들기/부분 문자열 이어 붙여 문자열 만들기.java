class Solution {
    public String solution(String[] my_strings, int[][] parts) {
        String ans = new String();
        int idx = 0;
        
        for (int[] arr : parts) {
            int s = arr[0];
            int e = arr[1];
            // for (int i = s; i <= e; i++)
            //     ans += my_strings[idx].charAt(i);
            ans += my_strings[idx].substring(s, e + 1);
            idx++;
        }

        // return str.substring(s, e);
        return ans;
    }
}