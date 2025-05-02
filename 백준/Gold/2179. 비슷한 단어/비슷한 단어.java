import java.io.*;
import java.util.*;

/**
 * 1. 단어들을 입력받아 배열에 저장하고, 원래 입력 순서도 함께 기록한다.
 * 2. 단어들을 사전순으로 정렬하면, 공통 접두사가 긴 단어쌍은 인접하게 위치하게 된다.
 * 3. 정렬된 배열에서 인접한 단어 쌍들 간 공통 접두사 길이를 구하며 최댓값을 갱신한다.
 * 4. 공통 접두사 길이가 최대인 쌍이 여러 개라면, 원래 입력 순서를 기준으로 가장 앞선 쌍을 선택한다.
 * 5. 최종적으로 조건을 만족하는 두 단어를 입력 순서대로 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        TreeMap<String, Integer> map = new TreeMap<>();
        String[] list = new String[N];
        List<String> result = new ArrayList<>();

        for (int idx = 0; idx < N; idx++) {
            String word = br.readLine();
            map.put(word, 0);
            list[idx] = word;
        }

        for (String word : map.keySet()) {
            // 인접한 쌍 추출
            String nextWord = map.higherKey(word);

            // 마지막 인덱스면 종료
            if (nextWord == null) break;

            // 다음 문자열이 현재 문자열과 접두사가 있다면 현재 문자열의 겹치는 접두사 길이를 저장
            for (int idx = 1; idx <= word.length(); idx++) {
                if (nextWord.startsWith(word.substring(0, idx))) {
                    map.put(word, map.get(word) + 1);
                } else break;
            }
        }

        // 최대 접두사 길이 계산
        int max = Integer.MIN_VALUE;
        for (String word : map.keySet()) {
            max = Math.max(max, map.get(word));
        }

        // 최대 접두사 길이를 가진 문자열 저장
        for (String word : map.keySet()) {
            if (map.get(word) == max) result.add(word);
        }

        // 각 문자열 중 최대 접두사 길이를 가지는 단어가 있
        for (String word : map.keySet()) {
            for (String r : result) {
                if (word.startsWith(r.substring(0, max))) {
                    map.put(word, map.get(r));
                }
            }
        }

        String first = null;
        for (String word : list) {
            if (map.containsKey(word) && map.get(word) == max) {
                first = word;
                break;
            }
        }
        System.out.println(first);

        for (String word : list) {
            if (word.equals(first)) continue;
            if (word.length() < max) continue;
            if (first.startsWith(word.substring(0, max)) || word.startsWith(first.substring(0, max))) {
                System.out.println(word);
                break;
            }
        }

    }

}