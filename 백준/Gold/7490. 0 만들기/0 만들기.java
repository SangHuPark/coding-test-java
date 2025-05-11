import java.io.*;
import java.util.Stack;

/**
 * 내가 선택한 카테고리: 구현 + DFS
 * 1. 선택지는 고정적으로 3개. +, -, ' '
 * 2. 기저조건: N-1 개를 선택하면 끝
 * 3. 수식이 만들어지면 해당 값을 계산. 0이면 수식 저장
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static char[] operators;

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        operators = new char[3];
        operators[0] = ' ';
        operators[1] = '+';
        operators[2] = '-';
        for (int T = 0; T < testCase; T++) {
            init();

            chooseOperator(0, new char[N-1]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void chooseOperator(int idx, char[] chooses) {
        // 2. 기저조건: N-1 개를 선택하면 끝
        if (idx == N - 1) {
            if (isZero(chooses)) {
                int chooseIdx = 0;
                int num = 1;
                for (int output = 0; output < N + N - 1; output++) {
                    if (output % 2 == 0)
                        sb.append(num++);
                    else
                        sb.append(chooses[chooseIdx++]);
                }
                sb.append("\n");
            }

            return;
        }

        for (int operatorIdx = 0; operatorIdx < 3; operatorIdx++) {
            chooses[idx] = operators[operatorIdx];
            chooseOperator(idx + 1, chooses);
        }
    }

    public static boolean isZero(char[] chooses) {
//        Stack<Integer> stack = new Stack<>();
//        stack.add(1);
//        int num = 2;
//        for (char operator : chooses) {
//            switch (operator) {
//                case '+':
//                    stack.add(num);
//                    break;
//                case '-':
//                    stack.add((-1) * num);
//                    break;
//                case ' ':
//                    int tmp = stack.pop() * 10;
//                    if (tmp < 0)
//                        tmp -= num;
//                    else
//                        tmp += num;
//
//                    stack.add(tmp);
//                    break;
//            }
//            num++;
//        }
//
//        int answer = 0;
//        for (int element : stack) {
//            answer += element;
//        }
//
//        return answer == 0;

        int sum = 0;
        int num = 1;
        int current = num++; // 시작 값

        for (char operator : chooses) {
            switch (operator) {
                case '+':
                    sum += current;
                    current = num++;
                    break;
                case '-':
                    sum += current;
                    current = -num++;
                    break;
                case ' ':
                    if (current < 0) {
                        current = current * 10 - num++;
                    } else {
                        current = current * 10 + num++;
                    }
                    break;
            }
        }
        sum += current;
        return sum == 0;
    }

}