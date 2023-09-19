class Solution {
    public String solution(int age) {
        String answer = new String();
        char[] arr = String.valueOf(age).toCharArray();
        for (char tmp : arr) {
            char ch = (char)(Character.getNumericValue(tmp) + 'a');
            answer += Character.toString(ch);
        }
        return answer;
    }
}