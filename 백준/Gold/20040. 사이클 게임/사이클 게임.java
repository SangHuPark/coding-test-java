import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int nodeCnt, gameCnt;
    static int[] parents;

    public static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (parents[findA] != findB)
            parents[findB] = findA;
    }

    public static int find(int idx) {
        if (parents[idx] == idx)
            return idx;

        return parents[idx] = find(parents[idx]);
    }

    public static void make() {
        parents = new int[nodeCnt];

        for (int idx = 0; idx < nodeCnt; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        for (int idx = 1; idx <= gameCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int firstDot = Integer.parseInt(st.nextToken());
            int secondDot = Integer.parseInt(st.nextToken());

            int findFirstDot = find(firstDot);
            int findSecondDot = find(secondDot);

//            // 부모 그 자체 점이 아니라면
//            if (firstDot != findFirstDot && secondDot != findSecondDot) {
                // 이미 부모가 같다면 싸이클
            if (findFirstDot == findSecondDot) {
                System.out.println(idx);
                return;
            }
//            }

            // 합집합
            union(firstDot, secondDot);
        }

        System.out.println(0);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCnt = Integer.parseInt(st.nextToken());
        gameCnt = Integer.parseInt(st.nextToken());

        make();
    }
}