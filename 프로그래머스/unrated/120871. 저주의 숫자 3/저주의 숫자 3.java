class Solution {
    public int solution(int n) {
        int answer = 1;
        
        int cnt=1;
        boolean check=false;
        while(true){     
            check=false;

            if(answer % 3 == 0){
                answer++;
                continue;
            }    

            if(String.valueOf(answer).contains("3")){
                answer++;
                check = true;
            }

            if(check) continue;

            if(cnt == n) break;
            
            answer++;
            cnt++;
        }
        
        return answer;
    }
}