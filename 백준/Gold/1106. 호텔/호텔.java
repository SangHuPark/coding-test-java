import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class City implements Comparable<City> {
        int price;
        int people;

        City (int price, int people) {
            this.price = price;
            this.people = people;
        }

        public int compareTo(City o1) {
            if (this.price == o1.price) {
                return (-1) * Integer.compare(this.people, o1.people);
            }

            return Integer.compare(this.price, o1.price);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int INF = 1000 * 100 + 1;

    static int target, cityCount;
    static City[] cities;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        init();

        // price 를 투자했을 때 유치할 수 있는 고객의 최댓값
        for (int idx = 0; idx < cityCount; idx++) {
            int cityPrice = cities[idx].price;
            int cityPeople = cities[idx].people;

            for (int price = cityPrice; price < INF; price++) {
                dp[price] = Math.max(dp[price], dp[price - cityPrice] + cityPeople);
            }
        }

        for (int idx = 0; idx < INF; idx++) {
            int price = dp[idx];
            if (price >= target) {
                System.out.print(idx);
                return;
            }
        }
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        target = Integer.parseInt(st.nextToken());
        cityCount = Integer.parseInt(st.nextToken());

        cities = new City[cityCount];
        for (int idx = 0; idx < cityCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int price = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            cities[idx] = new City(price, people);
        }
        Arrays.sort(cities);

        dp = new int[INF];
        dp[0] = 0;
    }
}