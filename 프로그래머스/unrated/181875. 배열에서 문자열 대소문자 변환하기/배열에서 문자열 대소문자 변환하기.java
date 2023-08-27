class Solution {
    public String[] solution(String[] strArr) {
        String[] ans = new String[strArr.length];
        
        for (int i = 0; i < strArr.length; i++) {
            ans[i] = i % 2 == 0 ? strArr[i].toLowerCase()
                                    : strArr[i].toUpperCase();
        }
        
        return ans;
    }
}