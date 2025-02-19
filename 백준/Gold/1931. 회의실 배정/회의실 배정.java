import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Time implements Comparable<Time> {
        int start;
        int end;

        Time (int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Time o1) {
            if (this.end < o1.end)
                return -1;
            else if (this.end > o1.end)
                return 1;
            else {
                return Integer.compare(this.start, o1.start);
            }
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int timeCnt;
    static List<Time> times;

    public static void main(String[] args) throws IOException {
        init();

        int curEnd = 0;
        int total = 0;
        for (Time time : times) {
            if (time.start >= curEnd) {
                total++;
                curEnd = time.end;
            }
        }

        System.out.println(total);
    }

    public static void init() throws IOException {
        timeCnt = Integer.parseInt(br.readLine().trim());

        times = new ArrayList<>();
        for (int idx = 0; idx < timeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            times.add(new Time(start, end));
        }
        Collections.sort(times);

    }
}