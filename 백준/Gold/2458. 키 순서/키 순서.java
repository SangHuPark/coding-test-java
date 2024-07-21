import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n,m;
    static boolean[][] arr = new boolean[501][501];
    static int[] isRanked = new int[501];
    static int ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        int a,b;
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            arr[a][b] = true;

        }

        for(int i=1;i<=n;i++)
            bfs(i);

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(arr[i][j]) isRanked[i]++;
                if(arr[j][i]) isRanked[i]++;
            }
        }

        for(int i=1;i<=n;i++){
            if(isRanked[i] == n-1){
                ans++;
            }
        }

        System.out.println(ans);




    }

    static void bfs(int start){
        boolean[] visited = new boolean[501];
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while(!q.isEmpty()){
            int first = q.poll();

            for(int i=1;i<=n;i++){
                if(!visited[i] && arr[first][i]){
                    visited[i] = true;
                    arr[start][i] = true;
                    q.add(i);
                }
            }
        }

    }
}