import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 정점의 개수와 간선의 개수를 입력받는다.
 * 2. 간선의 정보를 입력받는다.
 *  2-1. from, to, weight 를 저장하는 Edge 클래스 타입의 배열
 *
 * [ main() ]
 * 3. 간선을 돌며 최소 신장 트리를 완성한다.
 *  3-1. union 의 결과가 참이면 weight 를 누적 하고 연결 횟수를 카운팅한다.
 *  3-2. 연결 횟수가 정점 - 1 개이면 종료한다.
 */
public class Main {
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nodeNum, edgeCount;
    static Edge[] edgeList;

    static int[] parents;

    public static boolean union(int rootA, int rootB) {
        int parentA = find(rootA);
        int parentB = find(rootB);

        if (parentA == parentB) {
            return false;
        }

        if (parentA > parentB) {
            parents[parentB] = parentA;
        } else {
            parents[parentA] = parentB;
        }

        return true;
    }

    public static int find(int root) {
        if (parents[root] == root) {
            return root;
        }

        return parents[root] = find(parents[root]);
    }

    public static void makeSet() {
        parents = new int[nodeNum+1];
        for (int idx = 0; idx < nodeNum+1; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        Arrays.sort(edgeList);
        makeSet();

        int connectCount = 0;
        int weightSum = 0;
        for (Edge edge : edgeList) {

            if(union(edge.from, edge.to)) {
                weightSum += edge.weight;
                connectCount++;
            }

            if (connectCount == nodeNum-1) {
                break;
            }
        }

        System.out.println(weightSum);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeNum = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        edgeList = new Edge[edgeCount];
        for (int idx = 0; idx < edgeCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[idx] = new Edge(from, to, weight);
        }
    }
}