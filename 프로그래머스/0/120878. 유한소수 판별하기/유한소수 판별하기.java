class Solution {
    public int solution(int a, int b) {
        for(int num = Math.min(a, b); num >= 2; num--) {
            if(a < num || b < num) continue;
            
            if(a % num == 0 && b % num == 0) {
                a /= num;
                b /= num;
            }
        }
        
        return isFinite(b);
    }
    
    public int isFinite(int num) {
        int div = 5;
        while(num >= div) {
            if(num % div != 0)
                break;
            
            num /= div;
        }
        
        div = 2;
        while(num >= div) {
            if(num % div != 0)
                break;
            
            num /= div;
        }
        
        if(num == 1) return 1;
        
        return 2;
    }
}