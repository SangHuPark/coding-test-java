import java.util.stream.*;

class Solution {
    public int[] solution(int[] numbers) {
        return IntStream.of(numbers).map(i->i*2).toArray();
    }
}