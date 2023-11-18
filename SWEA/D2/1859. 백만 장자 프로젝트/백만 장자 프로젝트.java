/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());

        for (int i = 0; i < length; i++) {
            /* int days = Integer.parseInt(br.readLine());
            br.readLine();
            String[] input = br.readLine().split(" ");
            long answer = 0;

            for (int j = 0; j < input.length; j++) {
                int tmp = Integer.parseInt(input[j]);
                int max = 0;
                for (int k = j; k < input.length; k++) {
                    int sel = Integer.parseInt(input[k]);
                    max = Math.max(max, sel);
                }
                if (tmp < max) {
                    answer += max - tmp;
                }
            }

            System.out.printf("#%d %d\n", i+1, answer); */
            
            int n = Integer.parseInt(br.readLine());

            List<Integer> numList = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            int maxPrice = 0;
            double result=0;

            for (int j = n-1; j >= 0; j--) {
                if (maxPrice < numList.get(j).intValue())
                    maxPrice = numList.get(j).intValue();
                int temp = maxPrice - numList.get(j).intValue();
                result += temp;
            }

            System.out.println("#" + (i+1) + " " + Math.round(result));
        }
    }
}