import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            List<Integer> input = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            int N = input.get(0);
            int M = input.get(1);

            List<Integer> A = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Integer> B = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            int answer = 0;
            if (N < M) {
                answer = searchMax(N, M, A, B);
            } else {
                answer = searchMax(M, N, B, A);
            }

            System.out.printf("#%d %d\n", t+1, answer);
        }
    }

    public static int searchMax(int n, int m, List<Integer> nList, List<Integer> mList) {
        int tmp = 0;

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= m - n; i++) {
            List<Integer> sub = mList.subList(i, n + i);
            for (int j = 0; j < n; j++) {
                tmp += nList.get(j) * sub.get(j);
            }
            result.add(tmp);
            tmp = 0;
        }
        result.sort(Comparator.reverseOrder());

        return result.get(0);
    }
}