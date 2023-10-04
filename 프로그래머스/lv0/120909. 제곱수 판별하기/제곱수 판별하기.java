class Solution {
    public int solution(int n) {
        double num = Math.sqrt(n);
        return Math.floor(num) == num ? 1 : 2;
    }
}