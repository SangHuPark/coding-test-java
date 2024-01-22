import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String args[]) throws Exception
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String result = br.readLine().trim().toUpperCase();

        System.out.println(result);
    }
}