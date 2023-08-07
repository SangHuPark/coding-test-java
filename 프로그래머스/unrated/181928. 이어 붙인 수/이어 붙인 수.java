class Solution {
    public int solution(int[] num_list) {
        int answer = 0;
        String numStr1 = "";
        String numStr2 = "";
        
        for(int i = 0; i < num_list.length; i++) {
            if(num_list[i] % 2 == 1)
                numStr1 += new StringBuilder().append(num_list[i]).toString();
            else
                numStr2 += new StringBuilder().append(num_list[i]).toString();
        }
        
        answer = Integer.parseInt(numStr1) + Integer.parseInt(numStr2);
        
        return answer;
    }
}