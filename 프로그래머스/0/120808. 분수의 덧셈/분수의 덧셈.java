class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
        
        int gcd = GCD(denom1, denom2); // 최대공약수
        int lcm = (denom1 * denom2) / gcd; // 최소공배수
        
        int num = numer1 * (lcm / denom1) + numer2 * (lcm / denom2);
        int den = lcm;
        int gcd2 = GCD(num, den);
        answer[0] = num / gcd2;
        answer[1] = den / gcd2;
        
        return answer;
    }
    
    public int GCD(int a, int b) {
        int mod = a % b;
        if (mod == 0)
            return b;
        else
            return GCD(b, mod);
    }
}