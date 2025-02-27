import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Num implements Comparable<Num> {
        int idx;
        int num;

        Num (int idx, int num) {
            this.idx = idx;
            this.num = num;
        }

        public int compareTo(Num o1) {
            if (this.num == o1.num)
                return Integer.compare(this.idx, o1.idx);

            return (-1) * Integer.compare(this.num, o1.num);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int aSize, bSize;
    static Num[] aList, bList;

    public static void main(String[] args) throws IOException {
        init();

        int aPoint = 0, bPoint = 0;
        int beforeAIdx = -1;
        int beforeBIdx = -1;
        List<Integer> answer = new ArrayList<>();
        while (aPoint < aSize && bPoint < bSize) {
            if (aList[aPoint].num == bList[bPoint].num) {
                if (aList[aPoint].idx < beforeAIdx) {
                    aPoint++;
                    continue;
                }  else if (bList[bPoint].idx < beforeBIdx) {
                    bPoint++;
                    continue;
                }

                beforeAIdx = aList[aPoint].idx;
                beforeBIdx = bList[bPoint].idx;
                answer.add(aList[aPoint].num);
                aPoint++;
                bPoint++;
            } else if (aList[aPoint].num > bList[bPoint].num)
                aPoint++;
            else
                bPoint++;

        }

        System.out.println(answer.size());
        for (int num : answer) {
            System.out.print(num + " ");
        }

    }

    public static void init() throws IOException {
        aSize = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        aList = new Num[aSize];
        for (int idx = 0; idx < aSize; idx++) {
            aList[idx] = new Num(idx, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(aList);

        bSize = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        bList = new Num[bSize];
        for (int idx = 0; idx < bSize; idx++) {
            bList[idx] = new Num(idx, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(bList);
    }
}