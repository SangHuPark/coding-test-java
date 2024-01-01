import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            br.readLine();
            List<Integer> input = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            int[] arr = new int[101];
            for (int i = 0; i < input.size(); i++) {
                arr[input.get(i)]++;
            }

            int answer = 0;
            int max = 0;

            for (int i = 0; i < 101; i++) {
                if (arr[i] >= max) {
                    answer = i;
                    max = arr[i];
                }
            }

            System.out.printf("#%d %d\n", t+1, answer);
        }
    }
}