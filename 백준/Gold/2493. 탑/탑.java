import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. 탑의 수를 저장하는 topCount
 * 2. 탑들의 높이를 저장하는 1차원 배열 topHeightArray
 *  2-1. 탑의 순서가 같도록 인덱스 1번 부터 저장
 * 3. 이전 탑의 높이와 순서를 저장하는 beforeTopHeightStack
 *  3-1. int[] 를 Stack 의 인자로 저장
 *  3-2. 0 번째 인덱스에 탑의 높이를, 1 번째 인덱스에 탑의 순서를 저장하는 1차원 배열
 * 4. 인덱스 1번부터 탑의 개수만큼 반복
 *  4-1. 현재 탑의 높이를 저장하는 nowTopHeight
 * 5. beforeTopHeightStack 이 비어있지 않다면 nowTopHeight 와 이전 탑의 높이를 비교
 *  5-1. 같거나 크다면 이전 탑의 인덱스를 저장
 *  5-2. 그렇지 않다면 pop
 * 6. 만약 beforeTopHeightStack 이 비었다면 0 저장
 *  6-1. 현재 탑의 높이와 순서를 beforeTopHeightStack 에 저장
 */
public class Main {
    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static int topCount;

    public static int[] topHeightArray;

    public static Stack<int[]> beforeTopHeightStack;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        topCount = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        topHeightArray = new int[topCount + 1];
        for(int idx = 1; idx <= topCount; idx++) {
            topHeightArray[idx] = Integer.parseInt(st.nextToken());
        }

        beforeTopHeightStack = new Stack<int[]>();
        int nowTopHeight;
        for(int topIdx = 1; topIdx <= topCount; topIdx++) {
            nowTopHeight = topHeightArray[topIdx];

            while(!beforeTopHeightStack.isEmpty()) {
                if(beforeTopHeightStack.peek()[0] >= nowTopHeight) {
                    sb.append(beforeTopHeightStack.peek()[1]).append(" ");
                    break;
                }

                beforeTopHeightStack.pop();
            }

            if(beforeTopHeightStack.isEmpty()) {
                sb.append(0).append(" ");
            }

            beforeTopHeightStack.push(new int[] {nowTopHeight, topIdx});
        }

        System.out.println(sb);
    }
}