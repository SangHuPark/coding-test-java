class Solution {
    public String solution(int age) {
        String answer = new String();
        char[] arr = String.valueOf(age).toCharArray();
        System.out.println(arr[0]);
        for (char tmp : arr) {
            char ch = (char)(Character.getNumericValue(tmp) + 'a');
            answer += Character.toString(ch);
        }
        return answer;
    }
}