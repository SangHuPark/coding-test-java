import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int map[][];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        for (int idx = 0; idx < 2; idx++) {
            for (int row = 0; row < rowSize; row++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < colSize; col++) {
                    map[row][col] += Integer.parseInt(st.nextToken());
                }
            }
        }

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
    }
}
