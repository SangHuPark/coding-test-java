import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 3
 * 3 17 1 39 8 41 2 32 99 2
 * 22 8 5 123 7 2 63 7 3 46
 * 6 63 2 3 58 76 21 33 8 1
 *
 * #1 200
 * #2 208
 * #3 121
 */

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            List<Integer> input = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            int answer = 0;
            for (int tmp : input) {
                if (tmp % 2 == 1) answer += tmp;
            }

            System.out.printf("#%d %d\n", i+1, answer);
        }

    }
}