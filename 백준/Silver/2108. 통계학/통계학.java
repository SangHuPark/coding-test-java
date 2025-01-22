import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class NUM implements Comparable<NUM> {
        int num;
        int cnt;

        NUM (int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        public int compareTo(NUM o) {
            if (this.cnt > o.cnt) {
                return -1;
            } else if (this.cnt < o.cnt){
                return 1;
            } else {
                if (this.num < o.num) {
                    return -1;
                } else if (this.num > o.num) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] nums;
    static int sansul, middle, maxPrint, range;
    static Map<Integer, Integer> counter;

    public static void main(String[] args) throws IOException {
        init();

        sansul = (int) Math.round((double) sansul / N);
        middle = nums[N >> 1];

        // 빈도 계산
        for (int idx = 0; idx < N; idx++) {
            counter.put(nums[idx], counter.getOrDefault(nums[idx], 0) + 1);
        }

        // 최대 빈도 계산
        int maxFreq = Integer.MIN_VALUE;
        List<Integer> mostFrequent = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (value > maxFreq) {
                maxFreq = value;
                mostFrequent.clear();
                mostFrequent.add(key);
            } else if (value == maxFreq) {
                mostFrequent.add(key);
            }
        }
        Collections.sort(mostFrequent);

        if (mostFrequent.size() > 1)
            maxPrint = mostFrequent.get(1);
        else
            maxPrint = mostFrequent.get(0);

        range = nums[N-1] - nums[0];

        sb.append(sansul).append("\n").append(middle).append("\n").append(maxPrint).append("\n").append(range);
        System.out.println(sb);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        nums = new int[N];
        for (int idx = 0; idx < N; idx++) {
            nums[idx] = Integer.parseInt(br.readLine().trim());
            sansul += nums[idx];
        }
        Arrays.sort(nums);

        counter = new HashMap<>();
    }

}