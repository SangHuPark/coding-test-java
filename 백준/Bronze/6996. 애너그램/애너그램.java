import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int testCnt;
    static String firInput, secInput;
    static int firInputLeng, secInputLeng;
    static Map<Character, Integer> firMap, secMap;

    public static void main(String[] args) throws IOException {
        init();

        for (int T = 0; T < testCnt; T++) {
            st = new StringTokenizer(br.readLine().trim());
            firInput = st.nextToken();
            secInput = st.nextToken();
            firInputLeng = firInput.length();
            secInputLeng = secInput.length();

            firMap = new HashMap<>();
            for (int idx = 0; idx < firInputLeng; idx++) {
                char firChar = firInput.charAt(idx);
                firMap.put(firChar, firMap.getOrDefault(firChar, 0) + 1);
            }

            secMap = new HashMap<>();
            for (int idx = 0; idx < secInputLeng; idx++) {
                char secChar = secInput.charAt(idx);
                secMap.put(secChar, secMap.getOrDefault(secChar, 0) + 1);
            }

            sb.append(firInput).append(" & ").append(secInput);
            boolean exactStatus = true;
            for (char key : firMap.keySet()) {
                if (!secMap.containsKey(key)) {
                    sb.append(" are NOT anagrams.\n");
                    exactStatus = false;
                    break;
                }

                int firVal = firMap.get(key);
                int secVal = secMap.get(key);
                if (firVal != secVal) {
                    sb.append(" are NOT anagrams.\n");
                    exactStatus = false;
                    break;
                }
            }

            if (exactStatus)
                sb.append(" are anagrams.\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        testCnt = Integer.parseInt(br.readLine().trim());

    }

}