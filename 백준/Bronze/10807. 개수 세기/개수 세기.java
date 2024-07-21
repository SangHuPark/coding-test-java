import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N;
    static int[] numList;
    static int target;

    public static void main(String[] args) throws IOException {
        init();
        
        int result = 0;
        for (int num : numList) {
            if (num == target)
                result++;
        }

        System.out.println(result);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        
        st = new StringTokenizer(br.readLine().trim());
        numList = new int[N];
        for (int idx = 0; idx < N; idx++) {
            numList[idx] = Integer.parseInt(st.nextToken());
        }
        
        target = Integer.parseInt(br.readLine().trim());
    }
}