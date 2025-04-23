import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, taesu, scoreSize;
    static List<Integer> scores;

    public static void main(String[] args) throws IOException {
        init();

        int pos = 0;
        for (int idx = 0; idx < N; idx++) {
            int score = scores.get(idx);
            if (taesu <= score) {
                pos++;
            } else {
                break;
            }
        }

        if (N == scoreSize && scores.get(N-1) >= taesu) {
            System.out.println(-1);
        } else {
            int idx = 0;
            for (idx = 0; idx < N; idx++) {
                if (scores.get(idx) <= taesu)
                    break;
            }
            System.out.println(idx+1);
        }

    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        taesu = Integer.parseInt(st.nextToken());
        scoreSize = Integer.parseInt(st.nextToken());

        if (N > 0) {
            st = new StringTokenizer(br.readLine().trim());

            // 내림차순 List 생성
            scores = new LinkedList<>();
            for (int idx = 0; idx < N; idx++) {
                int num = Integer.parseInt(st.nextToken());
                scores.add(num);
            }
        }

    }

}