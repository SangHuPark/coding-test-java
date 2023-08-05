class Solution {
    public int solution(int a, int b) {
        /*
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
        */
        String strA = String.valueOf(a);
        String strB = String.valueOf(b);
        String strSum1 = strA + strB;
        String strSum2 = strB + strA;
        return Math.max(Integer.valueOf(strSum1), Integer.valueOf(strSum2));
        
    }
}