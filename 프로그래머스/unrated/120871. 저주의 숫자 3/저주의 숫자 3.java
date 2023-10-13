class Solution {
    public int solution(int n) {
        int answer = 0;
        
//         int cnt=1;
//         boolean check=false;
//         while(true){     
//             check=false;

//             if(answer % 3 == 0){
//                 answer++;
//                 continue;
//             }    

//             if(String.valueOf(answer).contains("3")){
//                 answer++;
//                 check = true;
//             }

//             if(check) continue;

//             if(cnt == n) break;
            
//             answer++;
//             cnt++;
//         }
        
        for (int i = 1; i <= n; i++) {
            answer++;
            if (answer % 3 == 0 || String.valueOf(answer).contains("3")) {
                i--;
            }
        }
        
        return answer;
    }
}