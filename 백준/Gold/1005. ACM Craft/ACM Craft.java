import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nodeCnt, edgeCnt;
    static int[] buildInfoList, inDegree;
    static List<Integer>[] graph;
    static int destination;

    static Deque<Integer> q;

    public static void topologicalSort() {
        q = new LinkedList<>();

        // 각 건물의 소요 시간 저장
        int[] timeCheck = new int[nodeCnt + 1];
        for (int idx = 1; idx <= nodeCnt; idx++) {
            timeCheck[idx] = buildInfoList[idx];

            // 동시에 초기 진입 차수가 0인 정점 큐에 삽입
            if (inDegree[idx] == 0)
                q.offer(idx);
        }

        // 현재 건물의 총 소요시간 = 이전 소요 시간의 누적합 + 현재 소요 시간
        while (!q.isEmpty()) {
            int curNode = q.poll();

            for (Integer next : graph[curNode]) {
                // 다음 건물까지의 소요 시간 저장
                // max를 저장해야 같은 레벨의 모든 건물이 지어진 것을 보장할 수 있음
                timeCheck[next] = Math.max(timeCheck[next], timeCheck[curNode] + buildInfoList[next]);
                inDegree[next]--; // 진출 간선을 제거한 의미로 진입 차수 -1

                // 더 이상 진입 차수가 없다면 사전에 거쳐야 할 정점을 모두 처리한 것이므로 큐에 삽입
                if (inDegree[next] == 0)
                    q.offer(next);
            }
        }

        sb.append(timeCheck[destination] + "\n");
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            init();

            topologicalSort();
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        buildInfoList = new int[nodeCnt + 1];
        for (int idx = 1; idx <= nodeCnt; idx++) {
            buildInfoList[idx] = Integer.parseInt(st.nextToken());
        }

        graph = new List[nodeCnt + 1];
        for (int idx = 0; idx <= nodeCnt; idx++) {
            graph[idx] = new ArrayList<>();
        }

        // 각 정점의 진입 차수 배열
        inDegree = new int[nodeCnt + 1];
        for (int idx = 0; idx < edgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            inDegree[to]++; // 진입 차수 카운팅
        }

        destination = Integer.parseInt(br.readLine().trim());
    }

}