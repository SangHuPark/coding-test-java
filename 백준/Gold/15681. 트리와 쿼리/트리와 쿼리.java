import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, R, Q;
    static List<Integer>[] graph;
    static int[] queries;

    static int[] edgeCntArr;

    public static void countChild(int curNode, int prevNode) {
        edgeCntArr[curNode] = 1;

        for (int child : graph[curNode]) {
            if (child == prevNode) continue;
            countChild(child, curNode);
            edgeCntArr[curNode] += edgeCntArr[child];
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        countChild(R, -1);

        for (int query : queries) {
            sb.append(edgeCntArr[query]).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for (int idx = 1; idx <= N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < N-1; idx++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            graph[parent].add(child);
            graph[child].add(parent);
        }

        queries = new int[Q];
        for (int idx = 0; idx < Q; idx++) {
//            st = new StringTokenizer(br.readLine());
            queries[idx] = Integer.parseInt(br.readLine());
        }

        edgeCntArr = new int[N+1];
    }
}