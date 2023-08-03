class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String answer = "";
        
        char[] s1 = my_string.toCharArray();
        char[] s2 = overwrite_string.toCharArray();
        
        int num = 0;
        for (int i = s; i < overwrite_string.length() + s; i++) {
            s1[i] = s2[num];
            num++;
        }
        
        for (int i = 0; i < my_string.length(); i++)
            answer += s1[i];
        
        System.out.println(answer);
        return answer;
    }
}