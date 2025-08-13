class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] words = {"aya", "ye", "woo", "ma"};

        for (String bab : babbling) {
            String origin = new String(bab);
            for (String w : words) {
                bab = bab.replace(w, " "); // 발견 시 제거
            }
            bab = bab.replace(" ", "");
            if (bab.length() == 0) {
                answer++;
            }
        }
        
        return answer;
    }
}