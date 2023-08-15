class Solution {
    public int solution(String my_string, String is_prefix) {
        int res = 0;
        String[] strArr = new String[my_string.length()];
        
        for (int i = 0; i < my_string.length(); i++) {
            strArr[i] = my_string.substring(0, i);
        }
        
        for (int j = 0; j < my_string.length(); j++) {
            if (strArr[j].equals(is_prefix)) {
                res = 1;
                break;
            }
        }
        
        return res;
    }
}