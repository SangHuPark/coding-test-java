import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException {
        int[] arr = new int[] {1, 1, 2, 2, 2, 8};

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < 6; idx++) {
            int num = Integer.parseInt(st.nextToken());

            System.out.print(arr[idx] - num + " ");
        }
    }
}