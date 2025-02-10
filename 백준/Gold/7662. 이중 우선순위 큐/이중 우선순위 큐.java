import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            int commandCnt = Integer.parseInt(br.readLine().trim());
            TreeMap<Long, Integer> treeMap = new TreeMap<>();

            for (int i = 0; i < commandCnt; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                String command = st.nextToken();

                if (command.equals("I")) {
                    long num = Long.parseLong(st.nextToken());
                    treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
                } else { // "D"
                    if (treeMap.isEmpty()) continue;

                    String deleteType = st.nextToken();
                    long key = deleteType.equals("-1") ? treeMap.firstKey() : treeMap.lastKey();

                    if (treeMap.get(key) == 1) {
                        treeMap.remove(key);
                    } else {
                        treeMap.put(key, treeMap.get(key) - 1);
                    }
                }
            }

            if (treeMap.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
            }
        }

        System.out.print(sb);
    }
}
