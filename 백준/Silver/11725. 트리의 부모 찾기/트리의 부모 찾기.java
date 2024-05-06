import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [ init() ]
 * 1. 노드의 개수 입력
 * 2. 노드의 연결 정보 입력
 *  2-1. 무방향이므로 양쪽에 연결
 * 3. 부모, 방문 배열 초기화
 *
 * [ setParent() ]
 * 4. 루트 노드인 1번에서 시작
 *  4-1. 연결 정보를 따라 연결된 노드의 부모를 현재 노드로 저장
 *
 * [ main() ]
 * 5. 2번 노드부터 부모 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nodeCount;
    static ArrayList<ArrayList<Integer>> edgeList;

    static int[] parents;
    static boolean[] visited;

    public static void setParent(int curNode) {
        visited[curNode] = true;

        for (int nextNode : edgeList.get(curNode)) {
            if (visited[nextNode])
                continue;

            parents[nextNode] = curNode;
            setParent(nextNode);
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        setParent(1);

        for (int node = 2; node < nodeCount+1; node++) {
            sb.append(parents[node]).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        nodeCount = Integer.parseInt(br.readLine().trim());

        edgeList = new ArrayList<>();
        for (int idx = 0; idx < nodeCount+1; idx++) {
            edgeList.add(new ArrayList<>());
        }

        for (int edgeIdx = 0; edgeIdx < nodeCount-1; edgeIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            edgeList.get(first).add(second);
            edgeList.get(second).add(first);
        }

        parents = new int[nodeCount + 1];
        visited = new boolean[nodeCount + 1];
    }
}