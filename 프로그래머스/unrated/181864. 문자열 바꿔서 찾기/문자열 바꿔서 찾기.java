class Solution {
    public int solution(String myString, String pat) {
        char[] arr = myString.toCharArray();
        String str = new String();
        
        for (char ch : arr) {
            switch (ch) {
                case ('A'):
                    str += "B";
                    break;
                case ('B'):
                    str += "A";
                    break;
                default:
                    return 0;
            }
        }
        
        return str.contains(pat) ? 1 : 0;
    }
}