import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Letter implements Comparable<Letter> {
        char letter;
        int cnt;

        Letter (char letter, int cnt) {
            this.letter = letter;
            this.cnt = cnt;
        }

        public int compareTo(Letter o1) {
            return Integer.compare(this.letter, o1.letter);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int wordCnt;
    static int[] targetList;
    static String target, opponent;
    static int targetLeng, opponentLeng;
    static int correctCnt;

    static int answer;

    public static void main(String[] args) throws IOException {
        wordCnt = Integer.parseInt(br.readLine().trim());

        // 기준 입력
        target = br.readLine().trim();
        targetLeng = target.length();

        answer = 0;
        // 상대들 입력
        for (int word = 1; word < wordCnt; word++) {
            targetList = new int[26];
            for (int idx = 0; idx < targetLeng; idx++) {
                targetList[target.charAt(idx) - 'A']++;
            }

            correctCnt = 0;
            opponent = br.readLine().trim();
            opponentLeng = opponent.length();
            for (int idx = 0; idx < opponentLeng; idx++) {
                int charIdx = opponent.charAt(idx) - 'A';
                if (targetList[charIdx] > 0) {
                    correctCnt++;
                    targetList[charIdx]--;
                }
            }

            // 두 단어의 길이가 같고, 모든 글자가 일치하거나 한 글자만 다른 경우
            if ((targetLeng == opponentLeng) && (targetLeng == correctCnt || targetLeng-1 == correctCnt))
                answer++;
            // 상대 문자열과 글자가 하나 다른데, 상대 문자열의 글자 하나가 많은 경우  => 한 글자만 제거
            else if (opponentLeng - 1 == correctCnt && targetLeng == opponentLeng - 1)
                answer++;
            // 상대 문자열과 모든 글자가 일치하나, 상대 문자열의 글자 하나가 적은 경우  => 한 글자만 추가
            else if (opponentLeng == correctCnt && targetLeng == opponentLeng + 1)
                answer++;
        }

        System.out.println(answer);
    }
}