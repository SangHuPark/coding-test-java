class Solution {
    public String[] solution(String[] picture, int k) {
        String[] answer = new String[picture.length * k];
        int idx = 0;
        
        for (String str : picture) {
            String[] strArr = str.split("");
            String tmp = "";
            for (int i = 0; i < strArr.length; i++) {
                tmp += strArr[i].repeat(k);
            }
            for (int i = 0; i < k; i++) {
                    answer[idx++] = tmp;    
            }
        }
        
        return answer;
    }
}