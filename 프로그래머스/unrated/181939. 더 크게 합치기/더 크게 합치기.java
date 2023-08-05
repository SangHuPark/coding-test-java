class Solution {
    public int solution(int a, int b) {
        
        int strSum1 = Integer.parseInt(new StringBuilder()
                                    .append(a).append(b).toString());
        int strSum2 = Integer.parseInt(new StringBuilder()
                                    .append(b).append(a).toString());

        /*
        String strA = String.valueOf(a);
        String strB = String.valueOf(b);
        String strSum1 = strA + strB;
        String strSum2 = strB + strA;
        */
        return Math.max(Integer.valueOf(strSum1), Integer.valueOf(strSum2));
    }
}