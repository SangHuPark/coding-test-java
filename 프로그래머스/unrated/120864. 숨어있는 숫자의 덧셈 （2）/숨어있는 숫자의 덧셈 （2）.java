class Solution {
    public int solution(String my_string) {
        int answer = 0;
        my_string = my_string.replaceAll("[A-z]", " ");
        String[] strArr = my_string.split(" ");
        
        for (String str : strArr) {
            if (!str.equals("")) {
                answer += Integer.parseInt(str);
            }
        }
        
        return answer;
    }
}