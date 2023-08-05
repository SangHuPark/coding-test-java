class Solution {
    public int solution(int a, int b) {
        
        int n = 0;
        
        if ( Integer.parseInt(new StringBuilder()
                                    .append(a).append(b).toString())
           >= Integer.parseInt(new StringBuilder()
                                    .append(b).append(a).toString()) )
            n = Integer.parseInt(new StringBuilder()
                                    .append(a).append(b).toString());
        else
            n = Integer.parseInt(new StringBuilder()
                                    .append(b).append(a).toString());
        
        return n;
    }
}