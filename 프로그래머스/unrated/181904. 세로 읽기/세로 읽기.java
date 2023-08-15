class Solution {
    public String solution(String my_string, int m, int c) {
        // String[] strArr = new String[my_string.length() / m];
        char[] ch = my_string.toCharArray();
        String res = new String();
        
        for (int i = 0; i < my_string.length() / m; i++) {
            res += ch[i * m + c - 1];
        }
        
        // for (int j = 0; j < my_string.length() / m; j++) {
        //     char[] chArr = strArr[j].toCharArray();
        //     res += Character.toString(chArr[c]);
        // }
        
        return res;
    }
}