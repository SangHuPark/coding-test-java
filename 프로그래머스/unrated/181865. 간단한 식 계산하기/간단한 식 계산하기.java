class Solution {
    public int solution(String binomial) {
        int ans = 0;
        String[] strArr = binomial.split(" ");
        
        if (strArr[1].equals("+"))
            ans = Integer.valueOf(strArr[0]) + Integer.valueOf(strArr[2]);
        else if (strArr[1].equals("-"))
            ans = Integer.valueOf(strArr[0]) - Integer.valueOf(strArr[2]);
        else if (strArr[1].equals("*"))
            ans = Integer.valueOf(strArr[0]) * Integer.valueOf(strArr[2]);
        
        
        return ans;
    }
}