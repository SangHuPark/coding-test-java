import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int[][] numList = new int[9][9];
            Boolean check = true;

            for (int i = 0; i < 9; i++) {
                List<Integer> input = Arrays.stream(br.readLine().split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                for (int j = 0; j < 9; j++) {
                    numList[i][j] = input.get(j);
                }
            }

            for (int i = 0; i < 9; i++) {
                int[] row = new int[9];
                int[] col = new int[9];

                for (int j = 0; j < 9; j++) {
                    row[numList[i][j] - 1]++;
                    col[numList[j][i] - 1]++;
                }

                for (int k = 0; k < 9; k++) {
                    if (row[k] == 0) check = false;
                    if (col[k] == 0) check = false;
                }
            }

            for (int i = 0; i < 9; i+=3) {
                for (int j = 0; j < 9; j+=3) {
                    int[] box = new int[9];

                    for (int n = i; n < i + 3; n++) {
                        for (int m = j; m < j + 3; m++) {
                            box[numList[n][m] - 1]++;
                        }
                    }

                    for (int k = 0; k < 9; k++) {
                        if (box[k] == 0) check = false;
                    }
                }
            }

            int answer = check ? 1 : 0;
            System.out.printf("#%d %d\n", t+1, answer);
        }
    }
}