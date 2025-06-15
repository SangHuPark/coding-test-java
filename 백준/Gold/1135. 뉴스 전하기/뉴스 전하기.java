import java.io.*;
import java.util.*;

/**
 * 1. 루트부터 DFS 시작
 * 2. 각 노드는 자식 노드들에 대해 재귀적으로 전파 시간 계산
 * 3. 자식들 중 전파 시간이 큰 순으로 먼저 전파
 * 4. idx번째 자식은 idx+1초 후에 뉴스 받음
 *  4-1. 필요 시간 = idx + 1 + 자식의 전파 시간
 * 5. 이 중 최대 시간이 해당 노드에서 필요한 전파 시간
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node implements Comparable<Node> {
        int idx;
        int childTime;

        Node (int idx , int childTime) {
            this.idx = idx;
            this.childTime = childTime;
        }

        public int compareTo(Node o) {
            return o.childTime - this.childTime;
        }

        public String toString() {
            return idx + " " + childTime;
        }
    }

    static int N;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        graph = new List[N];
        for (int idx = 0; idx < N; idx++) {
            graph[idx] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx++) {
            int root = Integer.parseInt(st.nextToken());
            if (root == -1) continue;

            graph[root].add(idx);
        }

        System.out.println(DFS(0));
    }

    // 2. 각 노드는 자식 노드들에 대해 재귀적으로 전파 시간 계산
    public static int DFS(int node) {
        // 3. 자식들 중 전파 시간이 큰 순으로 먼저 전파
        List<Integer> childTimes = new ArrayList<>();
        // 자식들의 전파 시간 계산
        for (int child : graph[node]) {
            childTimes.add(DFS(child));
        }
        // 전파 시간 기준 내림차순 정렬
        childTimes.sort(Collections.reverseOrder());

        // 4. idx번째 자식은 idx+1초 후에 뉴스 받음
        int maxTime = 0;
        for (int idx = 0; idx < childTimes.size(); idx++) {
            // 4-1. 필요 시간 = idx + 1 + 자식의 전파 시간
            maxTime = Math.max(maxTime, idx + 1 + childTimes.get(idx));
        }

        // 5. 이 중 최대 시간이 해당 노드에서 필요한 전파 시간
        return maxTime;
    }
}