import java.util.*;

class Solution {
    public int[] solution(int n, int[] slicer, int[] num_list) {
        ArrayList<Integer> list = new ArrayList<>();
        
        int a = slicer[0];
        int b = slicer[1];
        int c = slicer[2];
        
        if (n == 1) {
            for (int i = 0; i <= b; i++) {
                int temp = num_list[i];
                list.add(temp);
            }
        } else if (n == 2) {
            for (int i = a; i < num_list.length; i++) {
                int temp = num_list[i];
                list.add(temp);
            }
        } else if (n == 3) {
            for (int i = a; i <= b; i++) {
                int temp = num_list[i];
                list.add(temp);
            }
        } else if (n == 4) {
            for (int i = a; i <= b; i+=c) {
                int temp = num_list[i];
                list.add(temp);
            }
        }
        
        int[] ans = list.stream().mapToInt(j->j).toArray();
        return ans;
    }
}