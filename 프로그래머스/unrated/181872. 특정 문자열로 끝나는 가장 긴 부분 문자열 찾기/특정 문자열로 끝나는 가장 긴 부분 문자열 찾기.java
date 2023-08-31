class Solution {
    public String solution(String myString, String pat) {
        String ans = "";
        String[] strArr = myString.split("");
        
        for (int i = myString.length() - 1; i >= 0; i--) {
            for (String str : strArr) {
                ans += str;
            }
            
            if (ans.endsWith(pat)) {
                break;
            } else {
                ans = "";
            }
            
            strArr[i] = "";
        }
        
        return ans;
    }
}