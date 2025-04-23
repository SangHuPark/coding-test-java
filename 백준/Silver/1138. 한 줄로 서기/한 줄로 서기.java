import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int height;
        int idx;

        Node(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] tallerList, people;

    public static void main(String[] args) throws IOException {
        init();

        for (int idx = 0; idx < N; idx++) {
            int cnt = tallerList[idx];

            for (int other = 0; other < N; other++) {
                if (cnt == 0 && people[other] == 0) {
                    people[other] = idx + 1;
                    break;
                } else if (people[other] == 0) {
                    cnt--;
                }
            }
        }

        for (int idx = 0; idx < N; idx++) {
            System.out.print(people[idx] + " ");
        }
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        tallerList = new int[N];
        for (int idx = 0; idx < N; idx++) {
            tallerList[idx] = Integer.parseInt(st.nextToken());
        }

        people = new int[N];
    }

}