import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [조건]
 * - X 가 3 으로 나누어 떨어지면, 3 으로 나눈다.
 * - X 가 2로 나누어 떨어지면, 2 로 나눈다.
 * - 1 을 뺀다.
 * - 10^6 보다 작거나 같은 정수 N
 * 
 * 1. 입력으로 들어오는 N 을 저장하는 N
 * 2. 메모이제이션을 위한 memo
 * 3. DP 를 이용하여 memo 항의 최솟값 갱신
 * 	3-1. 2로 나누어 떨어진다면 2로 나눈 값
 * 	3-2. 6의 배수일 경우
 */
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static int N;
	public static int[] memo;
	
	public static int makeOne(int n) {
		if(n < 2) {
			return 0;
		}
		
		// 아직 메모이제이션 되지 않은 항이라면
		if(memo[n] == 0) {
			// 6으로 나누어 지는 경우 2, 3, -1의 경우와 모두 비교 해주어야 한다.
			if(n % 6 == 0) {
				memo[n] = Math.min(makeOne(n-1), Math.min(makeOne(n / 3), makeOne(n / 2))) + 1;
			} // 3으로 나누어 지는 경우 3, -1 의 경우와 비교
			else if(n % 3 == 0) {
				memo[n] = Math.min(makeOne(n/3), makeOne(n-1)) + 1;
			} 
			else if(n % 2 == 0) {
				memo[n] = Math.min(makeOne(n / 2), makeOne(n-1)) + 1;
			}
			else {
				memo[n] = makeOne(n-1) + 1;
			}
		}
		
		return memo[n];
	}
	
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine().trim());

		memo = new int[N + 1];
		
		makeOne(N);

		System.out.println(makeOne(N));
	}

}