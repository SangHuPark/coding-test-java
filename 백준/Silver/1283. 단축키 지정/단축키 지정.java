import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        checked = new boolean[26];
        for (int T = 0; T < testCase; T++) {
            st = new StringTokenizer(br.readLine().trim());
            String[] words = new String[st.countTokens()];
            for (int idx = 0; idx < words.length; idx++) {
                words[idx] = st.nextToken();
            }

            boolean flag = false;

            // 1. 각 단어 첫 글자 검사
            for (int idx = 0; idx < words.length; idx++) {
                char target = words[idx].charAt(0);
                int word =  target;
                if (target < 'a')
                    word -= 'A';
                else
                    word -= 'a';

                // 이미 사용된 단축키면 패스
                if (checked[word])
                    continue;

                // 체크
                checked[word] = true;

                // 단축키 표시
                words[idx] = "[" + words[idx].charAt(0) + "]" + words[idx].substring(1);

                // 현재 조건에서 종료됨 표시 후 종료
                flag = true;
                break;
            }
            if (flag) {
                // 결과로 단어 삽입
                for (int idx = 0; idx < words.length; idx++) {
                    sb.append(words[idx]).append(" ");
                }
                sb.append("\n");
                continue;
            }

            // 2. 각 단어의 첫 글자를 모두 사용한 경우
            for (int wordIdx = 0; wordIdx < words.length; wordIdx++) {
                String word = words[wordIdx];

                // 각 단어의 두 번째 글자부터 탐색
                for (int idx = 1; idx < word.length(); idx++) {
                    char tmp = word.charAt(idx);
                    int target = tmp;
                    if (tmp < 'a')
                        target -= 'A';
                    else
                        target -= 'a';

                    // 이미 사용된 단축키면 패스
                    if (checked[target])
                        continue;

                    // 체크
                    checked[target] = true;

                    // 단축키 표시
                    String tail = words[wordIdx].substring(idx + 1);
                    words[wordIdx] = words[wordIdx].substring(0, idx) + "[" + tmp + "]";
                    if (idx < words[wordIdx].length())
                        words[wordIdx] += tail;

                    flag = true;
                    break;
                }
                if (flag)
                    break;
            }

            // 결과로 단어 삽입
            for (int idx = 0; idx < words.length; idx++) {
                sb.append(words[idx]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

}