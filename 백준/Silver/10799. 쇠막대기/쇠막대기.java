import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String pipe;

    public static void main(String[] args) throws IOException {
        init();

        int sumPiece = 0;
        int pipeStartCount = 0;
        
        for (int idx = 0; idx < pipe.length(); idx++) {
            char piece = pipe.charAt(idx);

            if (piece == '(') {
                pipeStartCount++;
            } // piece == ')'
            else {
                pipeStartCount--;

                // 레이저
                if (pipe.charAt(idx - 1) == '(') {
                    sumPiece += pipeStartCount;
                } // 막대기 끝 처리
                else {
                    sumPiece++;
                }
            }
        }

        System.out.println(sumPiece);
    }

    public static void init() throws IOException {
        pipe = br.readLine().trim();
    }
}
