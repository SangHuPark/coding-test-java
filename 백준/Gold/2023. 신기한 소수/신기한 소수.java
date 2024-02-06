import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [조건]
 * - N 이 4고 1453 이라는 숫자가 신기한 소수라면 1, 14, 145, 1453 모두 소수
 * - N 은 1 이상 8 이하
 *
 * 1. 자리수 N 을 저장하는 numCount
 * 2. N 자리 수의 첫번째 수부터 마지막까지 돌며 모든 자리의 수가 소수인 수 구하기
 * 	2-1. 신기한 소수는 자리 수로 가질 수 있는 수가 {1, 2, 3, 5, 7, 9} 로 한정
 * 3. {1, 2, 3, 5, 7, 9} 중 중복 순열을 이용해 신기한 소수 생성
 * 4. 소수를 판단하는 isPrimeNum 메서드
 * 	4-1. 소수를 구하고자 하는 숫자의 제곱근 값 + 1 까지 반복하며 소수인지 판별
 * 	4-2. 약수로 나온 두 수의 순서가 형태가 되지 않도록
 */
public class Main {
    public static BufferedReader br;
    public static StringBuilder sb;

    public static int selectCount;

    public static int[] digitList;
    public static int[] selectDigitList;

    public static void primePermutation(int selectIdx) {
        if(selectIdx == selectCount) {
            sb.append(combineNum(selectIdx - 1)).append("\n");

            return;
        }

        for(int elementIdx = 0; elementIdx < digitList.length; elementIdx++) {
            selectDigitList[selectIdx] = digitList[elementIdx];

            int nowNum = combineNum(selectIdx);

            if(isPrimeNum(nowNum)) {
                primePermutation(selectIdx + 1);
            }
        }
    }

    public static int combineNum(int selectIdx) {
        int combineResult = 0;
        for(int idx = 0; idx <= selectIdx; idx++) {
            combineResult += selectDigitList[idx] * Math.pow(10, selectIdx - idx);
        }

        return combineResult;
    }
    public static boolean isPrimeNum(int num) {
        // 1은 소수가 아니므로 맨 앞자리가 될 수 없다.
        if(num == 1) {
            return false;
        }

        // 자리만 바뀐 약수 (2, 5) = (5, 2) 는 구하지 않도록 sqrt
        int rangeNum = (int) Math.sqrt(num);

        for(int primeNum = 2; primeNum <= rangeNum; primeNum++) {
            if(num % primeNum == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        selectCount = Integer.parseInt(br.readLine().trim());

        // {1, 2, 3, 5, 7, 9} 를 저장하는 digitList
        digitList = new int[] {1, 2, 3, 5, 7, 9};
        selectDigitList = new int[selectCount];

        primePermutation(0);

        System.out.println(sb);
    }
}