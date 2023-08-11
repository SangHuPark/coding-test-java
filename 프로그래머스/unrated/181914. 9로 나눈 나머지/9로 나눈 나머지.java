class Solution {
    public int solution(String number) {
        int ans = 0;
        // String[] strArr = number.split("");
        
        // for (int i = 0; i < strArr.length; i++)
        //     ans += Integer.parseInt(strArr[i]);
        
        for (String ch : number.split(""))
            ans += Integer.parseInt(ch);
        
        ans = ans % 9;
        
        return ans;
    }
}