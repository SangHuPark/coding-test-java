import java.util.*;

class Solution {
    public double solution(int[] numbers) {
        // double answer = Arrays.stream(numbers).sum() / (double) numbers.length;
        // return answer;
        return Arrays.stream(numbers).average().orElse(0);
    }
}