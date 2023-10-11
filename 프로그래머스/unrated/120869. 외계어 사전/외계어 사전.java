class Solution {
    public int solution(String[] spell, String[] dic) {
        for (String temp : dic) {
            int cnt = 0;
            for (int i = 0; i < spell.length; i++) {
                if (temp.contains(spell[i])) {
                    cnt++;
                }
            }
            if (cnt == spell.length)
                return 1;
        }
        
        return 2;
    }
}