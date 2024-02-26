import java.io.*;
import java.util.StringTokenizer;

/**
 * 1. 도시의 수 N 을 저장하는 cityNum
 * 2. 도시 사이 길을 저장하는 map
 *  2-1. 0 은 길이 없는 도시
 * 3. 1번 ~ N번 도시에서 출발해 다시 순회하는 최소 비용
 *  3-1. DFS 탐색을 통한 최소 비용 갱신
 *  3-2. 탐색 중 현재 최소 비용보다 크면 백트래킹
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int cityNum;
    public static int[][] map;

    public static boolean[] visited;

    public static int minResult;

    public static void mapDFS(int cityCount, int start, int cur, int curPrice) {
        if(cityCount == cityNum) {
            if(map[cur][start] != 0) {
                curPrice += map[cur][start];
                minResult = Math.min(minResult, curPrice);
            }
            return;
        }

        if(curPrice > minResult) {
            return;
        }

        for(int idx = 0; idx < cityNum; idx++) {
            if(visited[idx] || map[cur][idx] == 0) {
                continue;
            }

            visited[idx] = true;
            mapDFS(cityCount + 1, start, idx, curPrice + map[cur][idx]);
            visited[idx] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. 도시의 수 N 을 저장하는 cityNum
        cityNum = Integer.parseInt(br.readLine().trim());

        // 2. 도시 사이 길을 저장하는 map
        map = new int[cityNum][cityNum];
        for(int row = 0; row < cityNum; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0; col < cityNum; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        minResult = Integer.MAX_VALUE;
        for(int city = 0; city < cityNum; city++) {
            visited = new boolean[cityNum];
            visited[city] = true;
            mapDFS(1, city, city, 0);
        }

        System.out.println(minResult);
    }
}