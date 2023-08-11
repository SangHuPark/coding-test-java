class Solution {
    public String solution(String my_string, int[] index_list) {
        String ans = "";
        
        for (int idx : index_list) {
            ans += my_string.charAt(idx);
        }
        
        return ans;
    }
}