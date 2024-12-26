import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        long n = Long.parseLong(br.readLine().trim());
        System.out.println(findLayers(n));
    }

    public static int findLayers(long n) {
        if (n == 1) return 1; // 중심 방은 바로 리턴

        long range = 2; // 각 층의 시작 번호
        int layer = 1; // 층수

        while (range <= n) {
            range += 6L * layer; // 다음 층의 범위를 계산
            layer++;
        }

        return layer;
    }
}
