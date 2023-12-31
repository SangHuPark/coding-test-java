import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> input = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int A = input.get(0);
        int B = input.get(1);
        
        String answer = "";
        if (!(A > 1 && B > 1)) {
            A = A % 3;
            B = B % 3;
        }
        answer = A > B ? "A" : "B";
        System.out.println(answer);
    }
}