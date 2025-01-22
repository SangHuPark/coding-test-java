import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Num implements Comparable<Num> {
        int num;
        int cost;

        Num(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        public int compareTo(Num o) {
            if (this.cost < o.cost) {
                return 1;
            } else if (this.cost > o.cost) {
                return -1;
            } else {
                if (this.num < o.num) {
                    return -1;
                } else if (this.num > o.num) {
                    return 1;
                } else {
                    return 0;
                }
            }
//            return (-1) * Integer.compare(this.cost, o.cost);
        }

        public String toString() {
            return "{ " + num + ", " + cost + " }";
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, targetNum;
    static Deque<Num> q;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            init();

            int printIdx = 1;
            while (!q.isEmpty()) {
                int curMaxCost = Integer.MIN_VALUE;
                for (Num num : q) {
                    curMaxCost = Math.max(curMaxCost, num.cost);
                }

                Num curNum = q.pollFirst();

                if (curNum.cost == curMaxCost) {
                    if (curNum.num == targetNum) {
                        sb.append(printIdx).append("\n");
                        break;
                    }
                    printIdx++;
                } else {
                    q.addLast(curNum);
                }
            }

        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        targetNum = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        q = new ArrayDeque<>();
        for (int idx = 0; idx < N; idx++) {
            int cost = Integer.parseInt(st.nextToken());
            q.addLast(new Num(idx, cost));
        }
    }

}