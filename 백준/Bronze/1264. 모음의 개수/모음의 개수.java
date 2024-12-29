import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        while (true) {
            char[] chars = br.readLine().trim().toCharArray();
            
            if (chars[0] == '#')
                return;
            
            int cnt = 0;
            for (char input : chars) {
                input = Character.toLowerCase(input);

                if (input == 'a' || input == 'e' || input == 'i' || input == 'o' || input == 'u') {
                    cnt++;
                }
            }

            System.out.println(cnt);
        }

    }
}