import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static Map<Integer, int[]> map;

    static String getMinDigit(int sticks) {
        switch (sticks) {
            case 2: return "1";
            case 3: return "7";
            case 4: return "4";
            case 5: return "2";
            case 6: return "0";
            case 7: return "8";
            default: return "";
        }
    }

    static boolean compare(String a, String b) {
        // 앞자리 0이면 무조건 패스
        if (a.charAt(0) == '0') return false;
        if (b.charAt(0) == '0') return true;

        if (a.length() != b.length()) return a.length() < b.length();
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) throws IOException {
        map = new HashMap<>();
        map.put(2, new int[]{1});
        map.put(3, new int[]{7});
        map.put(4, new int[]{4});
        map.put(5, new int[]{5, 3, 2});
        map.put(6, new int[]{9, 6, 0});
        map.put(7, new int[]{8});

        // 최소값 초기화
        String[] minNum = new String[101];
        Arrays.fill(minNum, "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        minNum[2] = "1";
        minNum[3] = "7";
        minNum[4] = "4";
        minNum[5] = "2";
        minNum[6] = "6";
        minNum[7] = "8";

        for (int num = 8; num <= 100; num++) {
            for (int digit = 2; digit <= 7; digit++) {
                if (num - digit < 2) continue;

                String temp = minNum[num - digit] + getMinDigit(digit);

                if (compare(temp, minNum[num])) {
                    minNum[num] = temp;
                }
            }
        }

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int T = 0; T < testCase; T++) {
            int num = Integer.parseInt(br.readLine().trim());

            // 전처리
            List<Integer> elements = new ArrayList<>();
            int cnt = num >> 1;
            elements.add(2 + (num % 2)); // 맨 앞자리 삽입
            for (int two = 1; two < cnt; two++) {
                elements.add(2);
            }

            // 1. 최소값 구하기
            /*// 한자리로 표현 가능하면 그 숫자가 최소
            if (num >= 2 && num <= 7) {
                int[] nums = map.get(num);
                // 숫자는 0으로 시작할 수 없으므로
                if (nums[nums.length - 1] == 0)
                    sb.append(nums[nums.length - 2]);
                else
                    sb.append(nums[nums.length - 1]);
            } // 그게 아니라면
            else {
                int temp = num;
                int minus = 7;
                List<Integer> minList = new LinkedList<>();
                while (temp > 1 && minus > 1) {
                    if (temp - minus < 2 && temp != minus) {
                        minus--;
                    } else {
                        temp -= minus;
                        minList.add(minus);
                    }
                }
                minList.sort((o1, o2) -> {
                    return Integer.compare(o1, o2);
                });

                if (temp != 0) {
                    minList.add(minList.remove(0) + temp);
                }

                StringBuilder min = new StringBuilder();
                int idx = 0;
                for (int element : minList) {
                    int[] nums = map.get(element);
                    int leng = nums.length - 1;

                    // 첫 번째 자리면 0은 불가
                    if (idx == 0 && element == 6) {
                        min.append(nums[leng - 1]);
                    } else {
                        min.append(nums[leng]);
                    }
                    idx++;
                }
                sb.append(min);
            }
            sb.append(" ");*/

            sb.append(minNum[num]).append(" ");

            // 2. 최대값 구하기
            StringBuilder max = new StringBuilder();
            for (int element : elements) {
                max.append(map.get(element)[0]);
            }

            sb.append(max).append("\n");

        }

        System.out.println(sb);

    }

}