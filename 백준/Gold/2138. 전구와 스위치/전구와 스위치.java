import java.io.*;
import java.util.*;

/**
 * 1. 현재 전구 상태 origin과 목표 상태 target을 배열로 저장한다.
 * 2. 스위치를 0번에서 누르지 않은 경우, 누른 경우 두 가지로 분기하여 각각 시뮬레이션을 진행한다.
 * 3. 1번 인덱스부터 N-1까지 차례로 보면서, origin[i - 1]과 target[i - 1]이 다르면 i번째 스위치를 눌러야 한다.
 * 4. 스위치를 누르면 i-1, i, i+1 세 개의 전구 상태를 반전시킨다.
 * 5. 마지막까지 확인한 후 origin이 target과 같으면 그때까지 누른 스위치 횟수를, 아니면 불가능(-1)로 처리한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] origin, target;

    public static int simul(int start, int[] arr) {
        int count = start;

        // 1 ~ N-2
        for (int idx = 1; idx < N - 1; idx++) {
            // 이전 상태가 다르면 현재 스위치 누르고 카운팅
            if (arr[idx - 1] != target[idx - 1]) {
                arr[idx-1] = (arr[idx-1] + 1) % 2;
                arr[idx] = (arr[idx] + 1) % 2;
                arr[idx+1] = (arr[idx+1] + 1) % 2;

                count++;
            }
        }

        // N-1
        if (arr[N - 2] != target[N - 2]) {
            arr[N-2] = (arr[N-2] + 1) % 2;
            arr[N-1] = (arr[N-1] + 1) % 2;

            count++;
        }

        // 최종이 같은지 검사
        for (int idx = 0; idx < N; idx++) {
            if (arr[idx] != target[idx]) {
                count = -1;
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        init();

        // 0번 누른 경우
        int[] arr = Arrays.copyOf(origin, N);
        arr[0] = (origin[0] + 1) % 2;
        arr[1] = (origin[1] + 1) % 2;
        int clickFirst = simul(1, arr);

        // 누르지 않은 경우
        // 이전 상태로 복귀
        arr = Arrays.copyOf(origin, N);
        int notClickFirst = simul(0, arr);
        if (notClickFirst == -1)
            System.out.println(clickFirst);
        else if (clickFirst == -1)
            System.out.println(notClickFirst);
        else
            System.out.println(Math.min(clickFirst, notClickFirst));
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        String input = br.readLine().trim();
        origin = new int[N];
        for (int idx = 0; idx < N; idx++) {
            origin[idx] = input.charAt(idx) - '0';
        }

        input = br.readLine().trim();
        target = new int[N];
        for (int idx = 0; idx < N; idx++) {
            target[idx] = input.charAt(idx) - '0';
        }
    }

}