import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1.
 */
public class Main {
    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static int elementCount;
    public static int selectElementCount;

    public static int[] selectElementList;

    public static void permutation(int startElement, int selectIdx) {
        // 기저 조건: 현재 Idx 가 주어진 길이와 같다면 출력
        if(selectIdx == selectElementCount) {
            for(int idx = 0; idx < selectElementCount; idx++) {
                sb.append(selectElementList[idx]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int element = startElement; element <= elementCount; element++) {
            selectElementList[selectIdx] = element;

            permutation(element + 1, selectIdx + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());

        elementCount = Integer.parseInt(st.nextToken());
        selectElementCount = Integer.parseInt(st.nextToken());

        selectElementList = new int[elementCount];

        permutation(1, 0);

        System.out.println(sb);
    }
}