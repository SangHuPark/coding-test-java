class Solution {
    public String solution(String cipher, int code) {
        String result = "";
//         String[] arr = cipher.split("");
        
//         for (int i = 0; i < arr.length; i++) {
//             if ((i+1) % code == 0) {
//                 result += arr[i];
//             }
//         }
        
        for (int i = code-1; i < cipher.length(); i += code) {
            result += cipher.substring(i, i+1);
        }
        
        return result;
    }
}