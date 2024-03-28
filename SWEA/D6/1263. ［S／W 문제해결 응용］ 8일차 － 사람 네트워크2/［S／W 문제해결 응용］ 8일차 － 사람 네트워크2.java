import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 입력의 첫 번째 정수는 인접 행렬의 size
 * 2. 인접 행렬을 저장하는 graph
 *  2-1. 가중치 합을 저장해야 하므로 int
 * 3. 첫 번째 노드부터 경유지로 두고 플로이드 워셜을 통한 모든 쌍의 최단 경로 구하기
 * 4. 갱신된 각 노드의 가중치 합 중 최소값 출력
 */
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int INFINITY;

    public static int size;
    public static int[][] graph;
    public static int minWeight;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            size = Integer.parseInt(st.nextToken());

            graph = new int[size][size];
            INFINITY = 1000000000;
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    graph[row][col] = Integer.parseInt(st.nextToken());
                    // 자기 자신을 제외하고 연결이 안된 경우 무한대 저장
                    if (row != col && graph[row][col] == 0) {
                        graph[row][col] = INFINITY;
                    }
                }
            }

            for (int stopoverNode = 0; stopoverNode < size; stopoverNode++) {
                for (int startNode = 0; startNode < size; startNode++) {
                    if (stopoverNode == startNode) continue;
                    for (int endNode = 0; endNode < size; endNode++) {
                        if (endNode == startNode) continue;
                        graph[startNode][endNode] = Math.min(graph[startNode][stopoverNode] + graph[stopoverNode][endNode], graph[startNode][endNode]);
                    }
                }
            }

            minWeight = Integer.MAX_VALUE;
            for (int row = 0; row < size; row++) {
                int rowTotal = 0;
                for (int col = 0; col < size; col++) {
                    if (graph[row][col] == INFINITY) continue;

                    rowTotal += graph[row][col];
                }
                minWeight = Math.min(minWeight, rowTotal);
            }

            sb.append("#").append(tc).append(" ").append(minWeight).append("\n");
        }

        System.out.println(sb);
    }
}