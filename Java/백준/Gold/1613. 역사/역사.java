import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    private static int n, k;
    private static int[][] adj;
    private static int INF = 20_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        adj = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(adj[i], INF);
            adj[i][i] = 0;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adj[start][end] = 1;
        }

        for (int idx = 1; idx <= n; idx++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][idx] + adj[idx][j]);
                }
            }
        }

        // 답 찾기
        int cnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (adj[start][end] < INF) {
                System.out.println(-1);
            } else if (adj[end][start] < INF) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }




    }
}