import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int total = n + m;
        int[][] dp = new int[total + 1][total + 1];
        for (int i = 0; i <= total; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
            dp[i][1] = i;
        }
        for (int i = 2; i <= total; i++) {
            for (int j = 1; j < i; j++) {
                if (dp[i - 1][j - 1] + dp[i - 1][j] > 1000000000) {
                    dp[i][j] = 1000000001;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }

        // z기준
        if (dp[total][m] < k) {
            System.out.println("-1");
        } else {
            while (!(n == 0 && m == 0)) {
                if (dp[n + m - 1][m] >= k) {
                    System.out.print("a");
                    n--;
                } else {
                    System.out.print("z");
                    k = k - dp[n + m - 1][m];
                    m--;
                }
            }
        }
    }
}