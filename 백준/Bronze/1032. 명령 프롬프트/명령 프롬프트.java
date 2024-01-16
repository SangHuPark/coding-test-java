import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int fileNames = Integer.parseInt(br.readLine().trim());

        String[] answer = br.readLine().trim().split("");
        for (int line = 1; line < fileNames; line++) {
            String[] file = br.readLine().trim().split("");
            for (int idx = 0; idx < answer.length; idx++) {
                if (!answer[idx].equals(file[idx]))
                    answer[idx] = "?";
            }
        }

        for (int idx = 0; idx < answer.length; idx++) {
            sb.append(answer[idx]);
        }

        System.out.println(sb);
    }
}