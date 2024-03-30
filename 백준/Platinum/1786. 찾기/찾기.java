import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 전체 문자열 T, 찾고자 하는 패턴 문자열 P
 * 2. T 의 0 번째 인덱스를 제외하고 길이가 2 이상인 부분 문자열부터 접미사, 접두사 검사
 * 	2-1. 패턴의 부분 문자열 테이블 pi
 * 3. 패턴 문자열로 pi 테이블 갱신
 * 	3-1. 부분 문자열 P 의 i = 1 에 해당하는 부분 문자열의 접미사가 j = 0 과 같은지 검사
 * 	3-2. i 를 늘려가며 현재 j 포인터와 일치하다면 continue
 * 	3-3. 일치하지 않다면 i 만 증가
 * 	3-4. 이 과정을 반복해 pi 테이블 갱신
 * 4. pi 테이블을 통해 전체 문자열과 검사
 * 	4-1. 불일치 할 경우 pi[jPointer-1] 의 값을 jPointer 에 저장
 * 	4-2. jPointer 가 (패턴 문자열 길이) 와 같다면 패턴을 찾은 것이므로 0 으로 초기화 후 찾은 횟수 증가, 그때의 전체 문자열 인덱스 저장
 *
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static char[] T;
    public static char[] P;

    public static int jPointer;

    public static int[] pi;

    public static int correctCnt;
    public static List<Integer> correctIdxList;

    public static void main(String[] args) throws IOException {
        T = br.readLine().toCharArray();
        P = br.readLine().toCharArray();

        pi = new int[P.length];
        jPointer = 0;
        for(int idx = 1; idx < P.length; idx++) {
            // 3-1. 부분 문자열 P 의 idx 에 해당하는  접미사가 jPinter 같은지 검사
            while(0 < jPointer && P[idx] != P[jPointer]) {
                // 같지 않다면 pi 테이블의 인덱스 값으로 이동
                jPointer = pi[jPointer - 1];
            }

            // 3-2. 일치하다면 pi[idx] 업데이트
            if(P[idx] == P[jPointer]) {
                pi[idx] = ++jPointer;
            }
        }

        correctIdxList = new ArrayList<>();
        correctCnt = 0;
        jPointer = 0;
        for (int tIdx = 0; tIdx < T.length; tIdx++) {
            while(0 < jPointer && T[tIdx] != P[jPointer]) {
                jPointer = pi[jPointer - 1];
            }

            if(T[tIdx] == P[jPointer]) {

                if(jPointer == P.length-1) {
                    correctCnt++;
                    correctIdxList.add(tIdx - (P.length-2));

                    jPointer = pi[jPointer];
                }
                else {
                    jPointer++;
                }
            }

        }

        sb.append(correctCnt).append("\n");
        for(int correctIdx : correctIdxList) {
            sb.append(correctIdx).append(" ");
        }

        System.out.println(sb);
    }
}