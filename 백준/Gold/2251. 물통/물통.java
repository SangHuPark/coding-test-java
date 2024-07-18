import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int sizeA, sizeB, sizeC;

    static boolean[][] visited;
    static PriorityQueue<Integer> pq;

    public static void pour(int curA, int curB, int curC) {
        if (visited[curA][curB])
            return;

        if (curA == 0) {
            pq.add(curC);
        }
        visited[curA][curB] = true;

        // 2-1. A -> B
        if (curA + curB > sizeB)
            pour(curA + curB - sizeB, sizeB, curC);
        else
            pour(0, curA + curB, curC);

        // 2-2. B -> A
        if (curB + curA > sizeA)
            pour(sizeA, curB + curA - sizeA, curC);
        else
            pour(curB + curA, 0, curC);

        // 2-3. C -> B
        if (curC + curB > sizeB)
            pour(curA, sizeB, curC + curB - sizeB);
        else
            pour(curA, curB+curC, 0);

        // 2-4. C -> A
        if (curC + curA > sizeA)
            pour(sizeA, curB, curC + curA - sizeA);
        else
            pour(curA+curC, curB, 0);

        // 2-5. A -> C
        pour(0, curB, curA + curC);

        // 2-6. B -> C
        pour(curA, 0, curB + curC);
    }

    public static void main(String[] args) throws Exception {
        init();

        pour(0, 0, sizeC);

        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }

        System.out.println(sb);
    }

    public static void init() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        sizeA = Integer.parseInt(st.nextToken());
        sizeB = Integer.parseInt(st.nextToken());
        sizeC = Integer.parseInt(st.nextToken());

        visited = new boolean[201][201];
        pq = new PriorityQueue<>();
    }
}