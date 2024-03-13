import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 모음 aeiou 를 저장하는 gather
 * 2. 입력으로 들어온 문자열 한글자씩 gather 에 포함되어 있는 지 검사
 *  2-1. 안들어 있다면 저장
 */
public class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static String gather = "aeiou";
    public static char[] input;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");
            input = br.readLine().trim().toCharArray();
            for (int idx = 0; idx < input.length; idx++) {
                String toString = String.valueOf(input[idx]);
                if(!gather.contains(toString)) {
                    sb.append(toString);
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}