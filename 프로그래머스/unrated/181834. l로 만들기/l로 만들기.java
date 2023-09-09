class Solution {
    public String solution(String myString) {
//         String answer = "";
//         char[] arr = myString.toCharArray();
        
//         for (char ch : arr) {
//             if (ch < 108)
//                 ch = 'l';
//             answer += Character.toString(ch);
//         }
        
//         return answer;
        return myString.replaceAll("[a-k]", "l");
    }
}