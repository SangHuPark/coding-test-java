class Solution {
    public int solution(int a, int b, int c) {
        int answer = a + b + c;
        
        if(a == b && b == c && a == c)
            answer = answer * (a*a + b*b + c*c) * (a*a*a + b*b*b + c*c*c);
        // else if((a == b && b != c) || (a == c && b != c) || (b == c && a != b))
        else if(a == b || b == c || a == c)
            answer = answer * (a*a + b*b + c*c);
        
        return answer;
    }
}