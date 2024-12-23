import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String pipe;
    static Stack<Character> pipes;

    public static void main(String[] args) throws IOException {
        init();

        int openCnt = 0;
        int sumPiece = 0;
        for (char piece : pipe.toCharArray()) {
            if (piece == '(') {
                openCnt++;
                pipes.push('(');
            } else if (piece == ')') {
                openCnt--;
                if (pipes.peek() == '(') {
                    sumPiece += openCnt;
                } else {
                    sumPiece++;
                }
                pipes.push(')');
            }
        }

        System.out.println(sumPiece);
    }

    public static void init() throws IOException {
        pipe = br.readLine().trim();
        pipes = new Stack<>();
    }
}