class Solution {
    public int solution(String my_string) {
        int answer = 0;
        String[] cal = my_string.split(" ");
        answer = Integer.parseInt(cal[0]);
        
        for (int i = 1; i < cal.length; i++) {
            if (i % 2 != 0)
                if (cal[i].equals("+"))
                    answer += Integer.parseInt(cal[i+1]);
                else
                    answer -= Integer.parseInt(cal[i+1]);
        }
        
        return answer;
    }
}