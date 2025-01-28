import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Fibo {
        int zeroCnt;
        int oneCnt;

        Fibo (int zeroCnt, int oneCnt) {
            this.zeroCnt = zeroCnt;
            this.oneCnt = oneCnt;
        }

        public static Fibo plus(Fibo o1, Fibo o2) {
            return new Fibo(o1.zeroCnt + o2.zeroCnt, o1.oneCnt + o2.oneCnt);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int fiboNum;
    static int zeroPrintCnt, onePrintCnt;
    static Fibo[] printCnts;

    public static Fibo fibo(int num) {
        if (num == 0) {
            return printCnts[0];
        } else if (num == 1) {
            return printCnts[1];
        } else if (!Objects.isNull(printCnts[num])){
            return printCnts[num];
        } else {
            return printCnts[num] = Fibo.plus(fibo(num - 1), fibo(num - 2));
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            init();
            Fibo answer = fibo(fiboNum);
            sb.append(answer.zeroCnt).append(" ").append(answer.oneCnt).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        fiboNum = Integer.parseInt(br.readLine().trim());

        printCnts = new Fibo[41];
        printCnts[0] = new Fibo(1, 0);
        printCnts[1] = new Fibo(0, 1);
    }

}