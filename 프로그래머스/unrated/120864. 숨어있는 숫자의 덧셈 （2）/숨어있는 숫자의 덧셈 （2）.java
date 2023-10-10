class Solution {
    public int solution(String my_string) {
        int answer = 0;
        String[] strArr = my_string.replaceAll("[A-z]", " ").split(" ");
        
        for (String str : strArr) {
            if (!str.equals("")) {
                answer += Integer.parseInt(str);
            }
        }
        
        return answer;
    }
}