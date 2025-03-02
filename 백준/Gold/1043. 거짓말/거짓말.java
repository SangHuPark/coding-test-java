import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int nodeCnt, partyCnt;
    static int[] parents;
    static int truthCnt;
    static List<Integer> truthList;
    static List<Integer>[] partyList;

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if (truthList.contains(bParent)) {
            parents[aParent] = bParent;
            return;
        }

        parents[bParent] = aParent;
    }

    public static int find(int x) {
        if (parents[x] == x)
            return x;

        return parents[x] = find(parents[x]);
    }

    public static void make() {
        parents = new int[nodeCnt + 1];

        for (int idx = 0; idx <= nodeCnt; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCnt = Integer.parseInt(st.nextToken());
        partyCnt = Integer.parseInt(st.nextToken());

        make();

        st = new StringTokenizer(br.readLine().trim());
        truthCnt = Integer.parseInt(st.nextToken());
        if (truthCnt == 0) {
            System.out.println(partyCnt);
            return;
        }

        truthList = new ArrayList<>();
        for (int idx = 0; idx < truthCnt; idx++) {
            truthList.add(Integer.parseInt(st.nextToken()));
        }

        partyList = new List[partyCnt];
        for (int idx = 0; idx < partyCnt; idx++) {
            partyList[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < partyCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int guestCnt = Integer.parseInt(st.nextToken());

            int x = Integer.parseInt(st.nextToken());
            partyList[idx].add(x);
            for (int guestIdx = 1; guestIdx < guestCnt; guestIdx++) {
                int guest = Integer.parseInt(st.nextToken());
                union(x, guest);
                partyList[idx].add(guest);
            }
        }

        int answer = 0;
        for (int idx = 0; idx < partyCnt; idx++) {
            boolean flag = true;
            for (int num : partyList[idx]) {
                if (truthList.contains(find(parents[num]))) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}