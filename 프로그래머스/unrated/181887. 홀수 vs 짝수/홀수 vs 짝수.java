class Solution {
    public int solution(int[] num_list) {
        int num1 = 0;
        int num2 = 0;
        
        for (int i = 0; i < num_list.length; i++) {
            num1 += (i + 1) % 2 != 0 ? num_list[i] : 0;
            num2 += (i + 1) % 2 == 0 ? num_list[i] : 0;
        }
        
        return Math.max(num1, num2);
    }
}