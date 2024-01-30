import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1부터 N 중 중복 없이 M개를 고른다 --> 순열(Permutation)
 */
public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int ELEMENT_COUNT;
    static int SELECT_COUNT;

    static int[] elementList;
    static int[] selectElementList;
    static boolean[] elementUsedCheckList;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());
        ELEMENT_COUNT = Integer.parseInt(st.nextToken());
        SELECT_COUNT = Integer.parseInt(st.nextToken());

        elementList = new int[ELEMENT_COUNT];
        for(int idx = 0; idx < ELEMENT_COUNT; idx++) {
            elementList[idx] = (idx + 1);
        }

        selectElementList = new int[SELECT_COUNT];
        elementUsedCheckList = new boolean[ELEMENT_COUNT];

        permutation(0);

        System.out.println(sb);
    }

    public static void permutation(int selectIdx) {
        // 1. 기저 조건: 모든 원소를 선택한 경우
        if(selectIdx == SELECT_COUNT) {
            // 순열은 기저 조건에 반복문이 들어간다.
            // 모두 선택 후 이를 출력
            for(int idx = 0; idx < SELECT_COUNT; idx++) {
                sb.append(selectElementList[idx]).append(" ");
            }
            sb.append("\n");

            return;
        }

        // 2. 전 처리 로직
        for(int elementIdx = 0; elementIdx < ELEMENT_COUNT; elementIdx++) {
            // 이미 선택된 원소인지 검사 --> 선택했다면 이 후 로직 수행 x
            if(elementUsedCheckList[elementIdx]) {
                continue;
            }

            // 해당 원소가 아직 사용되지 않은 것
            elementUsedCheckList[elementIdx] = true;
            selectElementList[selectIdx] = elementList[elementIdx];

            // 3. 재귀 호출 --> 사용한 원소를 check 후 다음 위치로 넘어가기 위한 재귀 호출
            permutation(selectIdx + 1);

            // 4. 후 처리 로직 --> 위의 재귀 호출이 모두 동작 후 맨 밑단부터 돌아올 때 수행하는 로직
            // --> 사용 한 원소의 Check 상태를 복구
            elementUsedCheckList[elementIdx] = false;
        }

    }
}