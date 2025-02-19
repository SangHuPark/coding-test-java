import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Lesson implements Comparable<Lesson> {
        int start;
        int end;

        Lesson(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Lesson o1) {
            return Integer.compare(this.start, o1.start);
        }

        public String toString() {
            return "[ " + start + " " + end + " ]";
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int classCnt;
    static List<Lesson> lessons;

    static PriorityQueue<Integer> rooms;

    public static void main(String[] args) throws IOException {
        init();

        for (Lesson obj : lessons) {
            if (!rooms.isEmpty() && rooms.peek() <= obj.start) {
//                System.out.print(obj + " ");
//                System.out.println(rooms.poll());
                rooms.poll(); // 가장 빨리 끝나는 강의실 제거
            }
//            System.out.println("=========================================");
            rooms.add(obj.end);
        }

        System.out.println(rooms.size());
    }

    public static void init() throws IOException {
        classCnt = Integer.parseInt(br.readLine().trim());

        lessons = new ArrayList<>();
        for (int idx = 0; idx < classCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lessons.add(new Lesson(start, end));
        }
        Collections.sort(lessons);

        rooms = new PriorityQueue<>();
    }
}