class Solution {
    public String solution(String n_str) {
        String[] arr = n_str.split("");
        int idx = 0;
        
        for (String str : arr) {
            if (!str.equals("0")) {
                break;
            }    
            idx++;
        }
        
        return n_str.substring(idx);
    }
}