import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final char PLUS = '+', MINUS = '-', MULTIPLY = '*', DIVISION = '/';

    static String operation;
    static Stack<Character> cals;

    // 연산자 판단 함수
    public static boolean isOperation(char cal) {
        return cal == PLUS || cal == MINUS || cal == MULTIPLY || cal == DIVISION;
    }

    // 연산자 우선순위 반환 함수
    public static int getPrecedence(char cal) {
        if (cal == MULTIPLY || cal == DIVISION)
            return 2;

        if (cal == PLUS || cal == MINUS)
            return 1;

        return 0; // 기타 문자
    }

    public static void main(String[] args) throws IOException {
        init();

        for (char cal : operation.toCharArray()) {

            if (cal == ')') {
                while (!cals.isEmpty() && cals.peek() != '(') {
                    sb.append(cals.pop());
                }
                cals.pop();
            } else if (cal == '(') {
                cals.push(cal);
            } else if (isOperation(cal)) {
                while (!cals.isEmpty() && getPrecedence(cals.peek()) >= getPrecedence(cal)) {
                    sb.append(cals.pop());
                }

                cals.push(cal);
            } else {
                sb.append(cal);
            }
        }

        // 스택에 남은 연산자 모두 추가
        while (!cals.isEmpty()) {
            sb.append(cals.pop());
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        operation = br.readLine().trim();
        cals = new Stack<>();
    }
}