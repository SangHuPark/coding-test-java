import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 정점의 개수 V 와 간선의 개수 E
 * 2. 시작 정점을 저장하는 start
 * 3. 간선의 정보를 저장하는 edgeList
 *  3-1. 인접 정보를 나타내는 Node 클래스
 * 4. 다익스트라 알고리즘을 이용한 최단비용 갱신
 *  4-1. 이동 가능한 정점 중 최소 비용 정점 찾기
 *  4-2. 선택한 정점을 기준으로 최소 비용 갱신
 * 5. 갱신한 최소 비용 출력
 */
public class Main {
    static class Node {
        int to;
        int weight;
        Node link;

        public Node(int to, int weight, Node link) {
            this.to = to;
            this.weight = weight;
            this.link = link;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int V, E;

    public static int start;

    public static Node[] edgeList;

    public static void main(String[] args) throws IOException {
        // 1. 정점의 개수 V 와 간선의 개수 E
        st = new StringTokenizer(br.readLine().trim());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 2. 시작 정점을 저장하는 start
        start = Integer.parseInt(br.readLine().trim());

        // 3. 간선의 정보를 저장하는 edgeList
        edgeList = new Node[V+1];
        for (int idx = 0; idx < E; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[from] = new Node(to, weight, edgeList[from]);
        }

        // 방문 여부를 저장하는 visited
        boolean[] visited = new boolean[V+1];
        // start 부터 도착지까지 최소 거리를 저장하는 minDist
        int[] minDist = new int[V+1];
        // 최소 거리 무한대 초기화
        for(int idx = 0; idx < V+1; idx++) {
            minDist[idx] = Integer.MAX_VALUE;
        }

        // start 비용 0 초기화
        minDist[start] = 0;

        // 4. 다익스트라 알고리즘을 이용한 최단비용 갱신
        for(int idx = 0; idx < V; idx++) {
            // 4-1. 이동 가능한 정점 중 최소 비용 정점 찾기
            int minNodeWeight = Integer.MAX_VALUE; // 최소 비용 저장
            int minNodeIdx = 0; // 그때의 정점 번호 저장

            for(int searchIdx = 1; searchIdx < V+1; searchIdx++) {
                // 방문하지 않은 정점 중 최소 비용을 가진 정점 찾기
                if(!visited[searchIdx] && minDist[searchIdx] < minNodeWeight) {
                    minNodeWeight = minDist[searchIdx];
                    minNodeIdx = searchIdx;
                }
            }

            // 최소 비용을 가진 정점이 없다면 백트래킹
            if(minNodeIdx == 0) {
                break;
            }

            // 최소 비용을 가진 정점 방문 처리
            visited[minNodeIdx] = true;

            // 4-2. 선택한 정점을 기준으로 최소 비용 갱신
            for(Node next = edgeList[minNodeIdx]; next != null; next = next.link) {
                // 현재 저장된 비용과 새로운 정점을 경유해서 가는 비용 중 작은 값으로 갱신
                minDist[next.to] = Math.min(minDist[next.to], minNodeWeight + next.weight);
            }
        }

        // 5. 갱신한 최소 비용 출력
        for (int idx = 1; idx < V+1; idx++) {
            if(minDist[idx] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(minDist[idx]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}