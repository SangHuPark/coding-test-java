import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] numList;
    static int result;

    public static int isGood(int targetIdx) {
        int left = 0;
        int right = N-1;

        while(true) {

            if (left == targetIdx)
                left++;
            else if (right == targetIdx)
                right--;

            if (left >= right)
                break;

            if (numList[left] + numList[right] > numList[targetIdx])
                right--;
            else if (numList[left] + numList[right] < numList[targetIdx])
                left++;
            else {
                return 1;
            }

        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        init();

        result = 0;
        for (int targetIdx = 0; targetIdx < N; targetIdx++) {
            result += isGood(targetIdx);
        }

        System.out.println(result);

    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        numList = new int[N];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx++) {
            numList[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numList);
    }
}
