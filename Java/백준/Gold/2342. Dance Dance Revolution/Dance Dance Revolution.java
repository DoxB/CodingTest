import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n개 수열 진행, 왼발, 오른발 현재 위치 일때, 누적 힘
        int[][][] dp = new int[100001][5][5];
        // i -> j 로 갈 때, 드는 힘
        int[][] move = {
            {0, 2, 2, 2, 2},
            {2, 1, 3, 4, 3},
            {2, 3, 1, 3, 4},
            {2, 4, 3, 1, 3},
            {2, 3, 4, 3, 1}
        };

        for (int i = 0; i < 100001; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = 100001 * 4;
                }
            }
        }

        dp[0][0][0] = 0;
        int idx = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            // 오른발
            for (int i = 0; i < 5; i++) {
                if (i == n) continue;
                for (int j = 0; j < 5; j++) {
                    dp[idx][i][n] = Math.min(dp[idx - 1][i][j] + move[j][n], dp[idx][i][n]);
                }
            }

            // 왼발
            for (int j = 0; j < 5; j++) {
                if (j == n) continue;
                for (int i = 0; i < 5; i++) {
                    dp[idx][n][j] = Math.min(dp[idx - 1][i][j] + move[i][n], dp[idx][n][j]);
                }
            }
            idx++;
        }
        idx--;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, dp[idx][i][j]);
            }
        }

        System.out.println(ans);
    }
}