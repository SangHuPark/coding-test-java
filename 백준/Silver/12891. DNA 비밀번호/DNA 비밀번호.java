import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br;
    public static StringTokenizer st;

    public static int dnaCount;
    public static int selectDnaCount;

    public static char[] dnaList;

    public static int[] minDnaCheckList = new int[4];
    public static int[] selectDnaCheckList = new int[4];

    public static int dnaResultCount = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        dnaCount = Integer.parseInt(st.nextToken());
        selectDnaCount = Integer.parseInt(st.nextToken());

        dnaList = br.readLine().trim().toCharArray();

        // A, C, G, T 의 최소 개수 저장
        st = new StringTokenizer(br.readLine().trim());
        for(int idx = 0; idx < 4; idx++) {
            minDnaCheckList[idx] = Integer.parseInt(st.nextToken());
        }

        // 0 ~ selectDnaCount 길이의 첫 번째 부분문자열 세팅
        for(int idx = 0; idx < selectDnaCount; idx++) {
            addEndDna(idx);
        }

        if(isCorrect()) {
            dnaResultCount++;
        }

        int startIdx;

        // 전체 문자열 길이 중 생성하고자 하는 비밀번호 길이를 뺀 만큼 반복
        for(int endIdx = selectDnaCount; endIdx < dnaCount; endIdx++) {
            startIdx = endIdx - selectDnaCount; // 이전 부분문자열의 시작 인덱스 저장

            // 이전 부분문자열의 시작 위치 제거
            removeStartDna(startIdx);

            // 제거 후 다음 인덱스를 끝에 추가
            addEndDna(endIdx);

            if(isCorrect()) {
                dnaResultCount++;
            }
        }

        System.out.println(dnaResultCount);
    }

    public static boolean isCorrect() {
        for(int idx = 0; idx < 4; idx++) {
            if(minDnaCheckList[idx] > selectDnaCheckList[idx]) {
                return false;
            }
        }

        return true;
    }

    public  static void removeStartDna(int startIdx) {
        switch(dnaList[startIdx]) {
            case 'A': selectDnaCheckList[0]--; break;
            case 'C': selectDnaCheckList[1]--; break;
            case 'G': selectDnaCheckList[2]--; break;
            case 'T': selectDnaCheckList[3]--; break;
        }
    }

    public static void addEndDna(int endIdx) {
        switch(dnaList[endIdx]) {
            case 'A': selectDnaCheckList[0]++; break;
            case 'C': selectDnaCheckList[1]++; break;
            case 'G': selectDnaCheckList[2]++; break;
            case 'T': selectDnaCheckList[3]++; break;
        }
    }
}