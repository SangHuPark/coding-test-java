import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 스위치의 개수를 저장하는 switchNum 선언
 * 2. 스위치의 상태를 저장하는 int[] totalStatus 선언
 * 3. 학생 수를 저장하는 studentNum 선언
 * 4. studentNum 만큼 반복하며 성별과 받은 스위치 번호를 저장
 * 	4-1. 학생의 성별을 저장하는 studentType 선언
 * 	4-2. 학생이 받은 수를 저장하는 studentSwitch 선언
 * 5. studentType이 1이면 받은 수의 배수 인덱스의 totalStatus 상태 변경
 * 6. studentType이 2라면 (받은 수-1)의 양쪽 인덱스를 검사
 *  6-1. -1, +1 의 인덱스가 대칭이라면 다를 때 까지 범위를 늘려가며 totalStatus 상태 변경
 *  6-2. 받은 수-1의 인덱스도 상태 변경
 */
public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int switchNum;
	static int[] totalStatus;
	static int studentNum;
	static int studentType;
	static int studentSwitch;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 스위치의 개수를 저장하는 switchNum 선언
		switchNum = Integer.parseInt(br.readLine().trim());
		
		// 2. 스위치의 상태를 저장하는 int[] totalStatus 선언
		totalStatus = new int[switchNum];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < switchNum; idx++) {
			totalStatus[idx] = Integer.parseInt(st.nextToken());
		}
		
		// 3. 학생 수를 저장하는 studentNum 선언
		studentNum = Integer.parseInt(br.readLine().trim());
		// 4. studentNum 만큼 반복하며 성별과 받은 스위치 번호를 저장
		for(int student = 1; student <= studentNum; student++) {
			st = new StringTokenizer(br.readLine().trim());
			// 4-1. 학생의 성별을 저장하는 studentType 선언
			studentType = Integer.parseInt(st.nextToken());
			// 4-2. 학생이 받은 수를 저장하는 studentSwitch 선언
			studentSwitch = Integer.parseInt(st.nextToken());
			
			// 5. studentType이 1이면 받은 수의 배수 인덱스의 totalStatus 상태 변경
			if(studentType == 1) {
				for(int idx = 0; idx < switchNum; idx++) {
					if((idx+1) % studentSwitch == 0) {
						editStatus(idx);
					}
				}
			} else if(studentType == 2) { // 6. studentType이 2라면 (받은 수-1)의 양쪽 인덱스를 검사
				studentSwitch--;
				int moveIdx = 1;
				while(isArrange(studentSwitch-moveIdx, studentSwitch+moveIdx)) {
					// 6-1. -1, +1 의 인덱스가 대칭이라면 다를 때 까지 범위를 늘려가며 totalStatus 상태 변경
					if(totalStatus[studentSwitch-moveIdx] == totalStatus[studentSwitch+moveIdx]) {
						editStatus(studentSwitch-moveIdx);
						editStatus(studentSwitch+moveIdx);
					} else { // 현재 위치에서 양 옆이 다르다면 더 이상 검사 진행 x
                        break;
                    }
					moveIdx++;
				}
				// 6-2. 받은 수-1의 인덱스도 상태 변경
				editStatus(studentSwitch);
			}
		}
		
		for(int idx = 0; idx < switchNum; idx++) {
			sb.append(totalStatus[idx]).append(" ");
            // 20번째 스위치를 기준으로 개행 삽입
            if((idx+1) % 20 == 0) {
                sb.append("\n");
            }
		}
		
		System.out.println(sb);
	}
	
	public static void editStatus(int idx) {
		if(totalStatus[idx] == 0) {
			totalStatus[idx] = 1;
		} else {
			totalStatus[idx] = 0;
		}
	}
	
	public static boolean isArrange(int leftIdx, int rightIdx) {
		return leftIdx >= 0 && leftIdx < switchNum && rightIdx >= 0 && rightIdx < switchNum;
	}

}