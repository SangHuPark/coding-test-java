class Solution {
    public int solution(int[] num_list) {
        int temp = num_list[0];
        
        if (num_list.length > 10) {
            for (int i = 1; i < num_list.length; i++)
                temp += num_list[i];
        } else {
            for (int i = 1; i < num_list.length; i++)
                temp *= num_list[i];
        }
        
        return temp;
    }
}