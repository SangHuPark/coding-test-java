import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 학생 수 N, 키를 비교한 횟수 M 을 저장하는 studentNum, measureCount
 * 2. 진입 차수를 저장하는 entryList
 * 3. 위상정렬을 위한 그래프 lineGraph
 * 4. 위상정렬을 진행하는 sortQueue
 * 5. 진입 차수가 0 인 인덱스 큐에 삽입
 * 6. 큐가 빌 때까지 반복
 *  6-1. 진입 차수가 0 인 노드
 *  6-2. 인접한 노드를 찾는다.
 *  6-3. 인접한 노드들의 진입 차수를 -1 하여 갱신
 *  6-4. 만약 진입 차수가 0 이 되었다면 다음 방문 대상으로 큐에 삽입
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int studentNum, measureCount;

    public static int[] entryList;
    public static List<List<Integer>> lineGraph;
    public static Deque<Integer> sortQueue;

    public static void main(String[] args) throws IOException {
        // 1. 학생 수 N, 키를 비교한 횟수 M 을 저장하는 studentNum, measureCount
        st = new StringTokenizer(br.readLine().trim());
        studentNum = Integer.parseInt(st.nextToken());
        measureCount = Integer.parseInt(st.nextToken());
        // 2. 진입 차수를 저장하는 entryList
        entryList = new int[studentNum+1];
        // 3. 위상정렬을 위한 그래프 lineGraph
        lineGraph = new ArrayList<>();

        // 각 학생 노드 생성
        for(int idx = 0; idx <= studentNum; idx++) {
            lineGraph.add(new ArrayList<Integer>());
        }

        // 그래프 생성
        for(int idx = 0; idx < measureCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int student = Integer.parseInt(st.nextToken());
            int entryStudent = Integer.parseInt(st.nextToken());

            lineGraph.get(student).add(entryStudent);

            // 진입 차수 저장
            entryList[entryStudent]++;
        }

        // 4. 위상정렬을 진행하는 sortQueue
        sortQueue = new ArrayDeque<>();

        for(int idx = 1; idx <= studentNum; idx++) {
            // 5. 진입 차수가 0 인 인덱스 큐에 삽입
            if(entryList[idx] == 0) {
                sortQueue.offer(idx);
            }
        }

        // 6. 큐가 빌 때까지 반복
        while(!sortQueue.isEmpty()) {
            // 6-1. 진입 차수가 0 인 노드
            int student = sortQueue.poll();

            // 인접 노드를 검사할 진입 차수가 0 인 노드 저장
            sb.append(student).append(" ");

            // 6-2. 인접한 노드를 찾는다.
            for(int idx : lineGraph.get(student)) {
                // 6-3. 인접한 노드들의 진입 차수를 -1 하여 갱신
                entryList[idx]--;

                // 6-4. 만약 진입 차수가 0 이 되었다면 다음 방문 대상으로 큐에 삽입
                if(entryList[idx] == 0) {
                    sortQueue.offer(idx);
                }
            }
        }

        System.out.println(sb);
    }
}