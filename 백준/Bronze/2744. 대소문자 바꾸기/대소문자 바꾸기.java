import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String input = br.readLine().trim();
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < input.length(); idx++) {
            char tmp = input.charAt(idx);
            if (tmp > 'Z')
                tmp = (char) (tmp - 'a' + 'A');
            else
                tmp = (char) (tmp - 'A' + 'a');

            sb.append(tmp);
        }
        System.out.println(sb);
    }
}
