import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        String input = br.readLine().trim();
        int length = input.length();
        int answer = 0;
        for (int idx = 0; idx < length; idx++) {
            char ch = input.charAt(idx);
            if ('A' <= ch && ch <= 'C') {
                answer += 2;
            } else if ('D' <= ch && ch <= 'F') {
                answer += 3;
            } else if ('G' <= ch && ch <= 'I') {
                answer += 4;
            } else if ('J' <= ch && ch <= 'L') {
                answer += 5;
            } else if ('M' <= ch && ch <= 'O') {
                answer += 6;
            } else if ('P' <= ch && ch <= 'S') {
                answer += 7;
            } else if ('T' <= ch && ch <= 'V') {
                answer += 8;
            } else if ('W' <= ch && ch <= 'Z') {
                answer += 9;
            } else {
                answer += 10;
            }
        }
        answer += length;

        System.out.println(answer);
    }

    public static void init() throws IOException {

    }
}