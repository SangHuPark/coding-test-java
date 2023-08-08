class Solution {
    public int[] solution(int[] num_list) {
        int[] result = new int[num_list.length + 1];
        int leng = num_list.length;
        int num1 = num_list[leng - 1];
        int num2 = num_list[leng - 2];
        
        if (num1 > num2)
            result[leng] = num1 - num2;
        else
            result[leng] = num1 * 2;
        
        for (int i = 0; i < leng; i++)
            result[i] = num_list[i];
        
        return result;
    }
}