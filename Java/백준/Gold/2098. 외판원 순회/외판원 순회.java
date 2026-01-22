import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 1000000 * 16 + 1;
    private static int n;
    private static int[][] v;
    private static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        v = new int[n][n];
        StringTokenizer st = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                v[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[현재위치][방문했던 도시들] = v[현재위치][방문할 도시] + dp[방문할 도시][방문했던 도시들] < 최솟값 찾기
        dp = new int[n][1 << n];
        System.out.println(tsp(0, 1));
    }

    private static int tsp(int cur, int visited) {
        if (visited == (1 << n) - 1) {
            return v[cur][0] == 0 ? INF : v[cur][0];
        }
        if (dp[cur][visited] != 0) {
            return dp[cur][visited];
        }
        int min_v = INF;
        for (int i = 0; i < n; i++) {
            if ((visited & (1 << i)) == 0 && v[cur][i] != 0) {
                min_v = Math.min(min_v, v[cur][i] + tsp(i, (visited | (1 << i))));
            }
        }
        dp[cur][visited] = min_v;
        return dp[cur][visited];
    }
}