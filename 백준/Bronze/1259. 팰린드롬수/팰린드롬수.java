import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String N;

    public static void main(String[] args) throws IOException {
        init();
    }

    public static void init() throws IOException {

        while (true) {
            N = br.readLine();

            if (N.equals("0"))
                break;

            String pelin = new String("");
            for (int idx = N.length()-1; idx >= 0; idx--) {
                char c = N.charAt(idx);
                pelin = pelin.concat(String.valueOf(c));
            }

            if (N.equals(pelin))
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }
}