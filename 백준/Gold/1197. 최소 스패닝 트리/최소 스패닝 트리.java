import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 정점의 개수 V, 간선의 개수 E 를 저장하는 vertexCount, edgeCount
 * 2. from, to, weight 정보를 저장하는 edgeList
 *  2-1. from, to, weight 를 멤버로 가진 Edge 클래스
 * 3. 크루스칼 알고리즘을 이용한 최소 신장 트리 생성
 *  3-1. 정점 간 합집합 단계에서 부모가 같다면 싸이클을 생성하므로 연결 불가
 *  3-2. 그렇지 않다면 가중치 누적 계산
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

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int vertexCount, edgeCount;

    public static Edge[] edgeList;

    public static int[] parents;

    public static boolean union(int from, int to) {
        int fromParent = find(from);
        int toParent = find(to);

        if(fromParent == toParent) {
            return false;
        }

        if(fromParent > toParent) {
            parents[toParent] = fromParent;
            return true;
        }

        parents[fromParent] = toParent;
        return true;
    }

    public static int find(int idx) {
        if(parents[idx] == idx) {
            return idx;
        }

        return parents[idx] = find(parents[idx]);
    }

    public static void make() {
        parents = new int[vertexCount+1];

        for(int idx = 0; idx < vertexCount + 1; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        vertexCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        edgeList = new Edge[edgeCount];
        for(int idx = 0; idx < edgeCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[idx] = new Edge(from, to, weight);
        }

        Arrays.sort(edgeList);

        make();

        long totalWeight = 0;
        for(Edge edge : edgeList) {
            if(!union(edge.from, edge.to)) {
                continue;
            }

            totalWeight += edge.weight;
        }

        System.out.println(totalWeight);
    }
}