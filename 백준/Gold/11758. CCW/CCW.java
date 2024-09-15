import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int POINT_COUNT = 2;

    static int[] p1, p2, p3;

    public static int solution() {
        int result = 99;

//        int ccw = (p1[0] * p2[1]) + (p2[0] * p3[1]) + (p3[0] * p1[1]) - ((p2[0] * p1[1]) + (p3[0] * p2[1]) + (p1[0] * p3[1]));

        int ccw = (p2[0] - p1[0]) * (p3[1] - p2[1]) - (p3[0] - p2[0]) * (p2[1] - p1[1]);
//        System.out.println(ccw);

        if (ccw < 0) {
            result = -1;
        } else if (ccw == 0) {
            result = 0;
        } else {
            result = 1;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {

        init();

        System.out.println(solution());
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        p1 = new int[POINT_COUNT];
        p1[0] = Integer.parseInt(st.nextToken());
        p1[1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p2 = new int[POINT_COUNT];
        p2[0] = Integer.parseInt(st.nextToken());
        p2[1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p3 = new int[POINT_COUNT];
        p3[0] = Integer.parseInt(st.nextToken());
        p3[1] = Integer.parseInt(st.nextToken());
    }
}