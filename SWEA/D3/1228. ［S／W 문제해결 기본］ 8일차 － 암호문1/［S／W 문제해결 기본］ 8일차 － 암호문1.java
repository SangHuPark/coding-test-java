import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 원본 암호문의 길이 N 을 저장하는 pwLength
 * 2. 원본 암호문을 저장하는 LinkedList pwLinkedList
 * 3. 명령어의 개수를 저장하는 commandCount
 * 4. '|' 를 기준으로 x, y를 저장하는 point, numCount
 * 5.  numCount 만큼 반복하며 pwLinkedList 의 point 번 위치에 값 추가
 *  5-1. point++
 */
public class Solution {
    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static int pwLength;
    public static LinkedList<Integer> pwLinkedList;
    public static int commandCount;

    public static int point;
    public static int numCount;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        for(int testCase = 1; testCase <= 10; testCase++) {
            // 1. 원본 암호문의 길이 N 을 저장하는 pwLength
            pwLength = Integer.parseInt(br.readLine().trim());

            // 2. 원본 암호문을 저장하는 LinkedList pwLinkedList
            st = new StringTokenizer(br.readLine().trim());
            List<Integer> inputPwList = new ArrayList<>();
            for (int idx = 0; idx < pwLength; idx++) {
                inputPwList.add(Integer.parseInt(st.nextToken()));
            }
            pwLinkedList = new LinkedList<>(inputPwList);

            // 3. 명령어의 개수를 저장하는 commandCount
            commandCount = Integer.parseInt(br.readLine().trim());

            // 4. 'I ' 를 기준으로 x, y를 저장하는 point, numCount
            st = new StringTokenizer(br.readLine().trim(), "I ");
            for (int commandIdx = 0; commandIdx < commandCount; commandIdx++) {
                point = Integer.parseInt(st.nextToken());
                numCount = Integer.parseInt(st.nextToken());
                // 5.  numCount 만큼 반복하며 pwLinkedList 의 point 번 위치에 값 추가
                for (int numIdx = 0; numIdx < numCount; numIdx++) {
                    // 5-1. point++
                    pwLinkedList.add(point++, Integer.parseInt(st.nextToken()));
                }

            }

            sb.append("#").append(testCase);
            for(int idx = 0; idx < 10; idx++) {
                sb.append(" ").append(pwLinkedList.get(idx));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}