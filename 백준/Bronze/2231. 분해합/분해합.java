import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;

    public static void main(String[] args) throws IOException {
        init();

        boolean status = false;
        
        for (int result = 1; result <= 1000000; result++) {
            String str = String.valueOf(result);

            int tmp = result;
            for (int idx = 0; idx < str.length(); idx++) {
                int tmp2 = str.charAt(idx) - '0';
                tmp += tmp2;
            }

            if (tmp == N) {
                System.out.println(result);
                status = true;
                break;
            }
        }
        
        if (!status)
            System.out.println(0);

    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
    }
}