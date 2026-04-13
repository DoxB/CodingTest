import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
   public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // !! 설치된 다리 중에 양방향으로 바꿔야하는 것
        // 최소를 구해야되므로 최단 거리를 구해야함
        // 단방향으로 설정된 다리에서 역방향의 이동이 필요한 경우를 카운팅하면 됨
        // 다리로 이어진 경로를 0, 단방향의 역방향 경로를 1 가중치를 주고 카운팅
        // 최대 노드 수가 250개라 플루이드로 카운팅
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int INF = 100000001;
        int[][] adj = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(adj[i], INF);
            adj[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int flag = Integer.parseInt(st.nextToken());
            if (flag == 1) {
                adj[start][end] = 0;
                adj[end][start] = 0;
            } else {
                adj[start][end] = 0;
                adj[end][start] = 1;
            }
        }

        // 플루이드
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        int cnt = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(Integer.toString(adj[s][e]) + "\n");
        }

        System.out.println(sb.toString());
    }
}