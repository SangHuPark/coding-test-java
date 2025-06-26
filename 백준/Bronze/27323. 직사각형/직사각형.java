import java.io.*;
import java.util.*;

/**
 * BOJ_문제번호_문제명
 * @author tkdgn407
 * 
 * 1. 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int A, B;
    
    public static void main(String[] args) throws IOException {
        A = Integer.parseInt(br.readLine().trim());    
        B = Integer.parseInt(br.readLine().trim());
        System.out.println(A * B);
    }
}