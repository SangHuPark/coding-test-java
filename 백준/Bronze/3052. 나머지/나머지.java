import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(set.size());
    }

    public static void init() throws IOException {
        set = new HashSet<>();

        for (int idx = 0; idx < 10; idx++) {
            set.add(Integer.parseInt(br.readLine().trim()) % 42);
        }
    }
}