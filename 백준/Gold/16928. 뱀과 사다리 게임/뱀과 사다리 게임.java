import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Move {
        int from;
        int to;

        Move (int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int MAP_SIZE = 100, BRIDGE = 1, SNAKE = 2, DESTINATION = 99;

    static int bridgeCnt, snakeCnt;
    static Map<Integer, Integer> bridges;
    static Map<Integer, Integer> snakes;
    static int[] map;

    static Deque<Integer> q;

    public static void play(int start) {
        q = new ArrayDeque<>();
        boolean[] visited = new boolean[MAP_SIZE];

        q.add(start);
        visited[start] = true;

        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int T = 0; T < size; T++) {
                int pos = q.poll();

                if (pos == DESTINATION) {
                    System.out.println(time);
                    return;
                }

                for (int idx = 1; idx < 7; idx++) {
                    int nextPos = pos + idx;

                    if (nextPos >= 100 || visited[nextPos])
                        continue;

                    if (map[nextPos] == BRIDGE)
                        nextPos = bridges.get(nextPos);
                    else if (map[nextPos] == SNAKE)
                        nextPos = snakes.get(nextPos);

                    visited[nextPos] = true;
                    q.add(nextPos);
                }
            }

            time++;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        play(0);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        bridgeCnt = Integer.parseInt(st.nextToken());
        snakeCnt = Integer.parseInt(st.nextToken());

        map = new int[MAP_SIZE];

        bridges = new HashMap<>();
        for (int idx = 0; idx < bridgeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            map[from] = BRIDGE;
            bridges.put(from, to);
        }

        snakes = new HashMap<>();
        for (int idx = 0; idx < snakeCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            map[from] = SNAKE;
            snakes.put(from, to);
        }
    }
}