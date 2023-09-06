import java.math.BigInteger;

class Solution {
    public String solution(String a, String b) {
        BigInteger num1 = new BigInteger(a);
        BigInteger num2 = new BigInteger(b);
        
        num1 = num1.add(num2);
        return num1.toString();
    }
}