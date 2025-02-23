import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int cardCnt;
    static PriorityQueue<Integer> cardPrefix;

    public static void main(String[] args) throws IOException {
        init();

        int sum = 0, first = 0, second = 0;
        while (!cardPrefix.isEmpty()) {
            int size = cardPrefix.size();
            if (size > 1) {
                first = cardPrefix.poll();
                second = cardPrefix.poll();
                int tmp = first + second;
                sum += tmp;
                cardPrefix.add(tmp);
            } else
                break;
        }

        System.out.println(sum);

    }

    public static void init() throws IOException {
        cardCnt = Integer.parseInt(br.readLine().trim());

        cardPrefix = new PriorityQueue<>();
        for (int idx = 0; idx < cardCnt; idx++) {
            cardPrefix.add(Integer.parseInt(br.readLine().trim()));
        }
    }
}