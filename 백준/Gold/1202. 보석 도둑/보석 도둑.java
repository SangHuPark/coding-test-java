import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Gem {
        int weight;
        int cost;

        Gem (int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        public String toString() {
            return "[ " + this.weight + ", " + this.cost + " ]";
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int gemCnt, bagCnt;
    static Gem[] gemList;
    static int[] bagList;
    static PriorityQueue<Integer> pq;

    static long costPrefixSum;

    public static void main(String[] args) throws IOException {
        init();

        costPrefixSum = 0;
        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (-1) * Integer.compare(o1, o2);
            }
        });
        for (int idx = 0, gem = 0; idx < bagCnt; idx++) {
            while (gem < gemCnt && gemList[gem].weight <= bagList[idx]) {
                pq.add(gemList[gem++].cost);
            }

            if (!pq.isEmpty()) {
                costPrefixSum += pq.poll();
            }
        }

        System.out.println(costPrefixSum);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        gemCnt = Integer.parseInt(st.nextToken());
        bagCnt = Integer.parseInt(st.nextToken());

        gemList = new Gem[gemCnt];
        for (int idx = 0; idx < gemCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            gemList[idx] = new Gem(weight, cost);
        }
        Arrays.sort(gemList, new Comparator<Gem>() {
            @Override
            public int compare(Gem o1, Gem o2) {
                if (o1.weight == o2.weight)
                    return (-1) * Integer.compare(o1.cost, o2.cost);

                return Integer.compare(o1.weight, o2.weight);
            }
        });

        bagList = new int[bagCnt];
        for (int idx = 0; idx < bagCnt; idx++) {
            bagList[idx] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(bagList);
    }
}