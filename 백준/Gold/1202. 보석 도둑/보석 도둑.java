import java.io.*;
import java.util.*;

public class Main {
    static class Crystal {
        int weight;
        int cost;

        Crystal(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static Crystal[] crystals;
    static int[] bags;

    public static void main(String[] args) throws IOException {
        init();

        PriorityQueue<Integer> answer = new PriorityQueue<>((o1, o2) -> {
            return (-1) * Integer.compare(o1, o2);
        });

        long sum = 0;
        int cristalIdx = 0;
        for (int bagIdx = 0; bagIdx < K; bagIdx++) {
            while (cristalIdx < N && crystals[cristalIdx].weight <= bags[bagIdx]) {
                answer.add(crystals[cristalIdx++].cost);
            }

            if (!answer.isEmpty())
                sum += answer.poll();
        }

        System.out.println(sum);

    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        crystals = new Crystal[N];
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            crystals[idx] = new Crystal(weight, cost);
        }
        Arrays.sort(crystals, (o1, o2) -> {
            if (o1.weight == o2.weight)
                return (-1) * Integer.compare(o1.cost, o2.cost);

            return Integer.compare(o1.weight, o2.weight);
        });

        bags = new int[K];
        for (int idx = 0; idx < K; idx++) {
            bags[idx] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(bags);
    }
}