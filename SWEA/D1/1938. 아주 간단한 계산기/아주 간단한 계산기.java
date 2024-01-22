import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static BufferedReader br;

    public static void main(String args[]) throws Exception
    {
        br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().trim().split(" ");

        int firstInt = Integer.parseInt(input[0]);
        int secondInt = Integer.parseInt(input[1]);

        System.out.println(firstInt + secondInt);
        System.out.println(firstInt - secondInt);
        System.out.println(firstInt * secondInt);
        System.out.println(firstInt / secondInt);
    }
}