import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static int[] bubbleSort;

    public static void main(String[] args) throws IOException {
        init();

        int changeCnt = 0;
        for (int compareCount = 0; compareCount < N; compareCount++) {
            for (int idx = 0; idx < N - 1; idx++) {
                if (bubbleSort[idx] > bubbleSort[idx+1]) {
                    changeCnt++;

                    int tmp = bubbleSort[idx];
                    bubbleSort[idx] = bubbleSort[idx+1];
                    bubbleSort[idx+1] = tmp;

                    if (changeCnt == K) {
                        System.out.println(bubbleSort[idx] + " " + bubbleSort[idx + 1]);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        bubbleSort = new int[N];
        for (int idx = 0; idx < N; idx++) {
            bubbleSort[idx] = Integer.parseInt(st.nextToken());
        }
    }
}