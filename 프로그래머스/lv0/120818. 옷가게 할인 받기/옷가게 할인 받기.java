class Solution {
    public int solution(int price) {
        int num = price / 10000;
        int sale = num >= 50 ? 80 : num >= 30 ? 90 : num >= 10 ? 95 : 100;
        int answer = price * sale / 100;
        
        return answer;
    }
}