class Solution {
    public int solution(int[] common) {
        int lastIdx = common.length - 1;
        
        int tmp1 = common[1] - common[0];
        int tmp2 = common[2] - common[1];
        
        // 등차수열
        if (Math.abs(tmp1) == Math.abs(tmp2))
            return common[lastIdx] + tmp1;
        // 등비수열
        else
            return common[lastIdx] * (common[1] / common[0]);
        
    }
}