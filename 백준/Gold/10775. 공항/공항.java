import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int gateCnt, planeCnt;

    static int[] parents;

    public static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (parents[findA] != parents[findB])
            parents[findA] = findB;
    }

    public static int find(int idx) {
        if (parents[idx] == idx)
            return idx;

        return  parents[idx] = find(parents[idx]);
    }

    public static void make() {
        parents = new int[gateCnt + 1];

        for (int idx = 0; idx <= gateCnt; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        gateCnt = Integer.parseInt(br.readLine().trim());
        planeCnt = Integer.parseInt(br.readLine().trim());

        make();

        int answer = 0;
        for (int idx = 1; idx <= planeCnt; idx++) {
            int plane = Integer.parseInt(br.readLine().trim());
            int gate = find(plane);

            if (gate == 0)
                break;

            answer++;
            union(gate, gate-1);
        }

        System.out.println(answer);
    }
}