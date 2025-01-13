import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int cacheSize;
    static char[] chars;
    static char[] cache;

    public static int findPos(char input) {
        for (int idx = 0; idx < cacheSize; idx++) {
            if (cache[idx] == input || cache[idx] == 0)
                return idx;
        }

        return -1;
    }

    public static void shiftAndInsert(char input, int pos) {
        if (pos == -1) {
            pos = 0;
        } else if (cache[pos] == 0) {
            cache[pos] = input;
            return;
        }

        for (int idx = pos; idx < cacheSize - 1; idx++) {
            if (cache[idx+1] == 0) {
                cache[idx] = input;
                return;
            }
            cache[idx] = cache[idx + 1];
        }

        cache[cacheSize-1] = input;
    }

    public static void print() {
        for (int idx = 0; idx < cacheSize; idx++) {
            if (cache[idx] == 0)
                break;

            sb.append(cache[idx]);
        }
        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        int simulNum = 1;
        while (true) {
            st = new StringTokenizer(br.readLine().trim());
            cacheSize = Integer.parseInt(st.nextToken());

            if (cacheSize == 0)
                break;

            sb.append("Simulation ").append(simulNum++).append("\n");

            cache = new char[cacheSize];

            chars = st.nextToken().toCharArray();

            for (char input : chars) {
                if (input == '!') {
                    print();
                    continue;
                }

                int pos = findPos(input);
                shiftAndInsert(input, pos);
            }
        }

        System.out.println(sb);
    }
}