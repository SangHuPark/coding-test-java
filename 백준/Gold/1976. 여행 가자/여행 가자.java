import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int cityCnt, planCnt;
    static int[] planList;
    static int[] parents;

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent != bParent) {
            parents[bParent] = aParent;
        }
    }

    public static int find(int idx) {
        if (parents[idx] == idx)
            return idx;

        return parents[idx] = find(parents[idx]);
    }

    public static void make() {
        parents = new int[cityCnt + 1];
        for (int idx = 0; idx <= cityCnt; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        cityCnt = Integer.parseInt(br.readLine().trim());
        planCnt = Integer.parseInt(br.readLine().trim());

        make();

        for (int cityIdx = 1; cityIdx <= cityCnt; cityIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int connectCityIdx = 1; connectCityIdx <= cityCnt; connectCityIdx++) {
                int connect = Integer.parseInt(st.nextToken());
                if (connect == 1) {
                    union(cityIdx, connectCityIdx);
                }
            }
        }

        st = new StringTokenizer(br.readLine().trim());
        planList = new int[planCnt];
        for (int idx = 0; idx < planCnt; idx++) {
            planList[idx] = Integer.parseInt(st.nextToken());
        }

        int root = find(planList[0]);
        for (int idx = 1; idx < planCnt; idx++) {
            if (find(planList[idx]) != root) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}