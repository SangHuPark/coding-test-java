class Solution {
    public int solution(String binomial) {
        int ans = 0;
        String[] strArr = binomial.split(" ");
        int num1 = Integer.valueOf(strArr[0]);
        int num2 = Integer.valueOf(strArr[2]);
        
        // if (strArr[1].equals("+"))
        //     ans = Integer.valueOf(strArr[0]) + Integer.valueOf(strArr[2]);
        // else if (strArr[1].equals("-"))
        //     ans = Integer.valueOf(strArr[0]) - Integer.valueOf(strArr[2]);
        // else if (strArr[1].equals("*"))
        //     ans = Integer.valueOf(strArr[0]) * Integer.valueOf(strArr[2]);
        
        char op = strArr[1].charAt(0);
        switch (op) {
            case '+':
                ans = num1 + num2;
                break;
            case '-':
                ans = num1 - num2;
                break;
            case '*':
                ans = num1 * num2;
                break;
            default:
                return 0;
        }
        
        return ans;
    }
}