import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int init = Integer.parseInt(br.readLine().trim());
        int size = (init << 1) - 1;

        for (int idx = 1; idx <= size; idx++) {
            int space, star;

            if (idx <= init) {
                space = init - idx;
                star = 2 * idx - 1;
            } else {
                space = idx - init;
                star = 2 * (size - idx + 1) - 1;
            }

            for (int print = 0; print < space; print++) {
                System.out.print(" ");
            }

            for (int print = 0; print < star; print++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}