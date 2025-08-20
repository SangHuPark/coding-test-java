class Solution {
    public int solution(String A, String B) {
        if(A.equals(B))
            return 0;
        
        int len = A.length();
        for(int idx = 1; idx <= len; idx++) {
            A = A.substring(len-1, len) + A.substring(0, len-1);
            if(A.equals(B))
                return idx;
        }
        
        return -1;
    }
}