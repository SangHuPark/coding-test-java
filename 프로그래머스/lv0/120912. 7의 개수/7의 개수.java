class Solution {
    public int solution(int[] array) {
        int answer = 0;
        
        for (int numStr : array) {
            String[] strArr = String.valueOf(numStr).split("");
            
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i].equals("7")) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}