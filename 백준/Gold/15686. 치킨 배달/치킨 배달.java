import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 크기 N, 폐업시키지 않을 치킨 집 개수 M 을 저장하는 citySize, limitCount
 * 2. 도시의 정보를 저장하는 city
 * 3. 집의 위치를 저장하는 2차원 배열 house
 * 4. 치킨집의 위치를 저장하는 2차원 배열 chicken
 * 5. 재귀를 통해 집을 옮기며 치킨 거리의 누적합을 전달
 * 	5-1. 모든 집 탐색 시 최소값을 갱신
 * 	5-2. 중간에 최소값을 넘으면 백트래킹
 */
public class Main {
    static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int citySize, SELECT_COUNT;

    public static List<Position> homeList;
    public static List<Position> chickenList;

    public static int[] selectList;

    public static int resultDistance = Integer.MAX_VALUE;

    public static void chickenComb(int selectIdx, int elementIdx) {
        if(selectIdx == SELECT_COUNT) {
            int totalDistance = 0;

            for(Position home : homeList) {
                int minDistance = Integer.MAX_VALUE;
                for(int idx = 0; idx < SELECT_COUNT; idx++) {
                    Position selectChicken = chickenList.get(selectList[idx]);
                    int nowDistance = Math.abs(home.row - selectChicken.row) + Math.abs(home.col - selectChicken.col);
                    if(minDistance > nowDistance) {
                        minDistance = nowDistance;
                    }
                }

                totalDistance += minDistance;
            }

            if(resultDistance > totalDistance) {
                resultDistance = totalDistance;
            }

            return;
        }

        if(elementIdx == chickenList.size()) {
            return;
        }

        selectList[selectIdx] = elementIdx;
        chickenComb(selectIdx + 1, elementIdx + 1);

        selectList[selectIdx] = 0;
        chickenComb(selectIdx, elementIdx + 1);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        citySize = Integer.parseInt(st.nextToken());
        SELECT_COUNT = Integer.parseInt(st.nextToken());

        homeList = new ArrayList<>();
        chickenList = new ArrayList<>();
        for(int row = 1; row < citySize+1; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 1; col < citySize+1; col++) {
                int now = Integer.parseInt(st.nextToken());
                if(now == 1) {
                    homeList.add(new Position(row, col));
                } else if(now == 2) {
                    chickenList.add(new Position(row, col));
                }
            }
        }

        selectList = new int[SELECT_COUNT];

        chickenComb(0, 0);

        System.out.println(resultDistance);
    }
}