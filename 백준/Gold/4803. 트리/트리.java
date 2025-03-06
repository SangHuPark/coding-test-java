import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nodeCnt, edgeCnt;
    static int[] parents;
    static Set<Integer> treeSet, cycleSet;

    public static void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);

        if (findA == findB) {
            parents[findB] = findA;
            parents[findA] = 0;
        } else if (findA < findB)
            parents[findB] = findA;
        else
            parents[findA] = findB;

    }

    public static int find(int idx) {
        if (parents[idx] == idx)
            return idx;

        return parents[idx] = find(parents[idx]);
    }

    public static void make() {
        parents = new int[nodeCnt + 1];

        for (int idx = 0; idx <= nodeCnt; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        for (int T = 1; ; T++) {
            st = new StringTokenizer(br.readLine().trim());
            nodeCnt = Integer.parseInt(st.nextToken());
            edgeCnt = Integer.parseInt(st.nextToken());

            if (nodeCnt == 0 && edgeCnt == 0)
                break;

            sb.append("Case " + T + ": ");

            make();

//            cycleSet = new HashSet<>();
            for (int idx = 0; idx < edgeCnt; idx++) {
                st = new StringTokenizer(br.readLine().trim());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                int findA = find(a);
                int findB = find(b);

//                if (findA == findB) {
//                    cycleSet.add(findA);
//                }

                union(a, b);
            }

            treeSet = new HashSet<>();
            for (int idx = 1; idx <= nodeCnt; idx++) {
                int parent = find(parents[idx]);

                if (parent == 0)
                    continue;

                treeSet.add(parent);
            }

            int treeCnt = treeSet.size();
            if (treeCnt == 0)
                sb.append("No trees.");
            else if (treeCnt == 1)
                sb.append("There is one tree.");
            else
                sb.append("A forest of " + treeCnt + " trees.");
            sb.append("\n");
        }

        System.out.println(sb);
    }

}