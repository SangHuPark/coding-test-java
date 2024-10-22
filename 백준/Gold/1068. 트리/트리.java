import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int root;

    static int N;
    static int[] parents;
    static List<Integer>[] graph;
    static int deadNode;

    static boolean[] visited;
    static int result;

    public static void dfs(int curNode) {
        visited[curNode] = true;

        int childNodeCount = 0;
        for (int childNode : graph[curNode]) {
            if (childNode == deadNode || visited[childNode])
                continue;

            childNodeCount++;
            dfs(childNode);
        }

        if (childNodeCount == 0)
            result++;
    }

    public static void main(String[] args) throws IOException {
        init();

        if (deadNode == root) {
            System.out.println(0);
        } else {
            dfs(root);
            System.out.println(result);
        }

    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        graph = new List[N];
        for (int idx = 0; idx < N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        root = -1;
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx++) {
            int curParent = Integer.parseInt(st.nextToken());

            if (curParent == -1) {
                root = idx;
            } else {
                graph[idx].add(curParent);
                graph[curParent].add(idx);
            }

        }

        deadNode = Integer.parseInt(br.readLine().trim());

        visited = new boolean[N];
    }
}
