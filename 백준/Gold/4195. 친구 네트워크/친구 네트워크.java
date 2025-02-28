import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int relationCnt;
    static Map<String, Integer> relations;

    static int[] parents;
    static int[] level;

    public static int union(int A, int B) {
        A = find(A);
        B = find(B);

        if (A != B) {
            parents[B] = A;
            level[A] += level[B];

            level[B] = 1;
        }

        return level[A];
    }

    public static int find(int target) {
        if (target == parents[target])
            return target;

        return parents[target] = find(parents[target]);
    }

    public static void set() {
        int multipleSize = relationCnt << 1;

        parents = new int[multipleSize];
        level = new int[multipleSize];

        for (int idx = 0; idx < multipleSize; idx++) {
            parents[idx] = idx;
            level[idx] = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            relationCnt = Integer.parseInt(br.readLine().trim());

            set();

            // 이름 : idx
            relations = new HashMap<>();
            for (int idx = 0; idx < relationCnt; idx++) {
                st = new StringTokenizer(br.readLine().trim());

                String aPerson = st.nextToken();
                String bPerson = st.nextToken();

                if (!relations.containsKey(aPerson))
                    relations.put(aPerson, relations.size());

                if (!relations.containsKey(bPerson))
                    relations.put(bPerson, relations.size());

                sb.append(union(relations.get(aPerson), relations.get(bPerson))).append("\n");
            }
        }

        System.out.println(sb);
    }
}