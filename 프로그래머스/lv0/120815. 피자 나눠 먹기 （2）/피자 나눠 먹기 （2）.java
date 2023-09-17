class Solution {
    public int solution(int n) {
//         for (int i = 1; i < n + 6; i++) {
//             if ((n * i) % 6 == 0) {
//                 return n * i / 6;
//             }
//         }
        
//         return 0;
        int answer = 1;

        while(true){
            if(6*answer%n==0) break;
            answer++;
        }

        return answer;
    }
}