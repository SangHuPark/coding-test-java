import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int numCount;
    static List<Integer> numArr;

    public static void main(String[] args) throws IOException {
        numCount = Integer.parseInt(br.readLine().trim());
        numArr = new LinkedList<>();

        for (int idx = 0; idx < numCount; idx++) {
            numArr.add(Integer.parseInt(br.readLine().trim()));
        }

        Collections.sort(numArr);

        for (int num : numArr) {
            sb.append(num).append('\n');
        }

        System.out.println(sb);
    }
}