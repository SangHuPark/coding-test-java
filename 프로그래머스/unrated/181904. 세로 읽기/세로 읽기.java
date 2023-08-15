class Solution {
    public String solution(String my_string, int m, int c) {
//         char[] ch = my_string.toCharArray();
//         String res = new String();
        
//         for (int i = 0; i < my_string.length() / m; i++) {
//             res += ch[i * m + c - 1];
//         }
        
//         return res;
        
        String ans = "";

        for (int i = c - 1; i < my_string.length(); i += m) {
            ans += my_string.charAt(i);
        }
        
        return ans;
    }
}