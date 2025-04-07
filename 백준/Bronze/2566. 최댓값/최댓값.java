import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int max = Integer.MIN_VALUE;
        int maxRow = 0, maxCol = 0;
        for (int row = 0; row < 9; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < 9; col++) {
                int num = Integer.parseInt(st.nextToken());
                if (max < num) {
                    max = num;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }
        maxRow++;
        maxCol++;
        System.out.println(max);
        System.out.println(maxRow + " " + maxCol);
    }
}