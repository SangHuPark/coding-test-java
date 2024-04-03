import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 학생들의 수를 입력받는다.
 * 2. 비교한 횟수를 입력받는다.
 * 	2-1. 비교한 횟수는 간선의 정보
 * 3. 각 학생의 방문처리를 저장하는 배열을 만든다.
 * 4. 모든 학생의 연결된 간선을 따라 BFS 를 진행한다.
 * 	4-1. 시작 학생 번호의 인덱스에 방문한 학생의 인덱스를 모두 체크한다.
 */
public class Solution {
    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int studentCount;
    public static int graphCount;
    public static List<List<Integer>> graph;

    public static Deque<Integer> studentQ;

    public static boolean[][] studentCheck;

    public static void checkBFS(int start) {
        studentQ = new ArrayDeque<Integer>();
        boolean[] visited = new boolean[studentCount];

        studentQ.add(start);

        visited[start] = true;

        while(!studentQ.isEmpty()) {
            int curStudent = studentQ.poll();

            for(int idx : graph.get(curStudent)) {
                if(visited[idx] || studentCheck[curStudent][idx]) {
                    continue;
                }

                studentQ.add(idx);
                visited[idx] = true;
                studentCheck[start][idx] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            input();

            studentCheck = new boolean[studentCount][studentCount];

            for(int student = 0; student < studentCount; student++) {
                checkBFS(student);
            }

            int result = 0;
            int[] isRanked = new int[studentCount];
            for(int studentRow = 0; studentRow < studentCount; studentRow++) {
                for(int studentCol = 0; studentCol < studentCount; studentCol++) {
                    if(studentCheck[studentRow][studentCol]) {
                        isRanked[studentRow]++;
                    }
                    if(studentCheck[studentCol][studentRow]) {
                        isRanked[studentRow]++;
                    }
                }

//                System.out.print(isRanked[studentRow] + " ");
                if(isRanked[studentRow] >= studentCount-1) {
                    result++;
                }
            }
//            System.out.println();

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    public static void input() throws IOException {
        studentCount = Integer.parseInt(br.readLine().trim());
        graphCount = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList<>();
        for (int idx = 0; idx < graphCount; idx++) {
            graph.add(new ArrayList<>());
        }

        for(int idx = 0; idx < graphCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken())-1;
            int next = Integer.parseInt(st.nextToken())-1;

            graph.get(start).add(next);
        }
    }

}