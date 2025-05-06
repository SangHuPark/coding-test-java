import java.io.*;
import java.util.*;

/**
 * 1. 각 번호 idx에 대해, arr[idx]를 따라 **단방향 DFS**를 수행한다.
 * 2. DFS 중 방문한 번호들은 **현재 경로상 방문 배열(visited[])**에 따로 저장한다.
 * 3. DFS 도중 시작 번호로 돌아오면, **idx를 포함한 싸이클이 존재함을 의미하므로** 그 경로상의 모든 번호를 정답에 포함한다. => 사이클이면서 시작점이 포함된 경우
 * 4. 중복 체크를 피하기 위해 **전역 used[] 배열**로 이전에 탐색된 번호는 제외한다.
 * 5. 모든 idx(1~N)에 대해 DFS를 반복하고, 결과 집합을 정렬하여 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] arr;
    static boolean[] used;

    // 1. 각 번호 idx에 대해, arr[idx]를 따라 **단방향 DFS**를 수행한다.
    public static void DFS(int idx, int start, List<Integer> visited) {
        if (visited.contains(idx)) {
            if (idx == start) {
                for (int visit : visited) {
                    used[visit] = true;
                }

            }
            return;
        }

        visited.add(idx);
        DFS(arr[idx], start, visited);
    }

    public static void main(String[] args) throws IOException {
        init();

        for (int start = 1; start <= N; start++) {
            DFS(start, start, new LinkedList<>());
        }

        int cnt = 0;
        List<Integer> list = new LinkedList<>();
        for (int idx = 1; idx <= N; idx++) {
            if (used[idx]) {
                cnt++;
                list.add(idx);
            }
        }
        list.sort((o1, o2 ) -> {
            return Integer.compare(o1, o2);
        });

        sb.append(cnt).append("\n");
        for (int answer : list) {
            sb.append(answer).append("\n");
        }
        System.out.println(sb);

    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        arr = new int[N + 1];
        used = new boolean[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            arr[idx] = Integer.parseInt(br.readLine().trim());
        }
    }
}