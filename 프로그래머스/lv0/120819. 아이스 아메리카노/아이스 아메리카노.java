class Solution {
    public int[] solution(int money) {
//         int count = 0;
//         int change = money % 5500;
//         while (money >= 5500) {
//             money -= 5500;
//             count++;
//             change = money % 5500;
//         }
        
//         int[] answer = {count, change};
        
//         return answer;
        
        return new int[] { money / 5500, money % 5500 };
    }
}