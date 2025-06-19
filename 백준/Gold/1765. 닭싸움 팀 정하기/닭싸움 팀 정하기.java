import java.io.*;
import java.util.*;

/**
 * 1. 친구면 union
 * 2. 원수면 'E 1 4' 의 경우 graph[1].add(4) (반대 포함)로 연결한다.
 *  2-1. 이후 1 은 for (Node friend : graph[4]) 에 연결된 다른 정점들과 union 한다.
 *  2-2. 4도 1에 연결된 다른 정점들과 union 한다.
 * 3. parents의 결과를 set 에 저장해 크기를 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] parents;
    static List<Integer>[] enemies;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        enemies = new List[N + 1];
        for (int idx = 0; idx <= N; idx++) {
            enemies[idx] = new ArrayList<>();
        }

        set();
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            String relation = st.nextToken();
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 친구면 union
            if (relation.equals("F")) {
                union(from, to);
            } // 원수면 그래프 생성
            else {
                // 각 원수의 원수는 친구니까 union
                for (int enemy : enemies[from]) {
                    union(to, enemy);
                }

                for (int enemy : enemies[to]) {
                    union(from, enemy);
                }

                enemies[from].add(to);
                enemies[to].add(from);
            }
        }

        Set<Integer> answer = new HashSet<>();
        for (int idx = 1; idx <= N; idx++) {
            answer.add(find(idx));
        }
        System.out.println(answer.size());
    }

    public static void set() {
        parents = new int[N + 1];
        for (int idx = 0; idx <= N; idx++) {
            parents[idx] = idx;
        }
    }

    public static int find(int x) {
        if (parents[x] == x)
            return x;

        return parents[x] = find(parents[x]);
    }

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (aParent < bParent)
            parents[aParent] = bParent;
        else
            parents[bParent] = aParent;
    }

}