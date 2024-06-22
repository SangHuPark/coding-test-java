import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. 수열의 크기 입력
 * 2. 수열의 원소 입력
 *
 * [ NGE() ]
 * 3. 수열의 원소를 돌며 오큰수를 찾는다
 *  3-1. 현재 스택이 비어있지 않고 && 스택의 peek 이 가르키는 인덱스가 현재 원소보다 작다면 반복
 *      3-1-1. 현재 원소가 더 크므로 pop 한 인덱스에 현재 원소 값 저장
 *  3-2. 스택에 현재 인덱스 push
 * 4. 변경 작업이 끝나고 스택이 비어있지 않다면 스택에 담긴 인덱스는 모두 -1 저장
 * 5. 최종 배열 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int elementCount;
    static int[] elements;
    static Stack<Integer> idxStack;

    public static void NGE() {
        for (int idx = 0; idx < elementCount; idx++) {

            while (!idxStack.isEmpty() && elements[idxStack.peek()] < elements[idx]) {
                elements[idxStack.pop()] = elements[idx];
            }

            idxStack.push(idx);
        }

        while (!idxStack.isEmpty()) {
            elements[idxStack.pop()] = -1;
        }

        for (int idx = 0; idx < elementCount; idx++) {
            sb.append(elements[idx]).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        init();

        NGE();
    }

    public static void init() throws IOException {
        elementCount = Integer.parseInt(br.readLine().trim());

        elements = new int[elementCount];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < elementCount; idx++) {
            elements[idx] = Integer.parseInt(st.nextToken());
        }

        idxStack = new Stack<>();
    }
}