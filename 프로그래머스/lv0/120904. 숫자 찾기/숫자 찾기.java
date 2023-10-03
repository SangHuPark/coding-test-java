class Solution {
    public int solution(int num, int k) {
        String[] strArr = (num + "").split("");
        
        for (int i = 0; i < strArr.length; i++) {
            if (Integer.parseInt(strArr[i]) == k) {
                return i+1;
            }
        }
        
        return -1;
    }
}