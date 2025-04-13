import java.io.*;
import java.util.*;

public class Main {
    static class Group {
        int num; // 인원 수
        int cost; // 사탕 수

        Group(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, edgeCount, limit;
    static int[] candies, parents, groupPeople, groupCandies;
    static List<Group> groups;
    static int groupSize;
    static long[][] dp;

    public static boolean union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent == bParent)
            return false;

        parents[bParent] = aParent;
        return true;
    }

    public static int find(int x) {
        if (parents[x] == x)
            return x;

        return parents[x] = find(parents[x]);
    }

    public static void set() {
        parents = new int[N + 1];

        for (int idx = 0; idx <= N; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        for (int idx = 1; idx <= groupSize; idx++) {
            Group group = groups.get(idx-1);
            int num = group.num;
            int cost = group.cost;

            for (int dpIdx = 0; dpIdx < limit; dpIdx++) {
                if (dpIdx < num)
                    dp[idx][dpIdx] = dp[idx - 1][dpIdx];
                else
                    dp[idx][dpIdx] = Math.max(dp[idx - 1][dpIdx], dp[idx - 1][dpIdx - num] + cost);
            }
        }

        System.out.println(dp[groupSize][limit-1]);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        set();
        st = new StringTokenizer(br.readLine().trim());
        candies = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            candies[idx] = Integer.parseInt(st.nextToken());
        }

        for (int idx = 0; idx < edgeCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            union(from, to);
        }

        groupPeople = new int[N + 1];
        groupCandies = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            int group = find(idx); // idx 가 속한 그룹 찾기
            groupPeople[group]++; // 해당 그룹의 인원 수 + 1
            groupCandies[group] += candies[idx]; // 해당 그룹의 사탕 수 누적
        }

        groups = new ArrayList<>();
        for (int idx = 1; idx <= N; idx++) {
            // 그룹장만 리스트에 추가
            if (parents[idx] == idx)
                groups.add(new Group(groupPeople[idx], groupCandies[idx]));
        }
        Collections.sort(groups, (o1, o2) -> { return Integer.compare(o1.num, o2.num); });
        groupSize = groups.size();

        dp = new long[groupSize + 1][limit];
    }
}