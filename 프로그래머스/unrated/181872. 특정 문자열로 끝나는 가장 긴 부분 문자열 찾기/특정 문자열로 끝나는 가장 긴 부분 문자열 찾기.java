class Solution {
    public String solution(String myString, String pat) {
        String ans = "";
//         String[] strArr = myString.split("");
        
//         for (int i = myString.length() - 1; i >= 0; i--) {
//             for (String str : strArr) {
//                 ans += str;
//             }
            
//             if (ans.endsWith(pat)) {
//                 break;
//             } else {
//                 ans = "";
//             }
            
//             strArr[i] = "";
//         }
        
        for (int i = myString.length(); i >= 0; i--) {
            myString = myString.substring(0, i);
            if(myString.endsWith(pat)) {
                return myString;
            } 
        }
        
        return ans;
    }
}