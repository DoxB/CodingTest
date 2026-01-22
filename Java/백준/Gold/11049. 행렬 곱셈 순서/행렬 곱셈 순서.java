import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[][] dp;
    private static Matrix[] m;
    public static void main(String[] args) throws IOException{
        // 1 ~ N까지 최소
        // 1 ~ N = (1 ~ N - 1) + (N ~ N) + ?구간 더할 때 생기는 계산횟수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        m = new Matrix[n + 1];
        dp = new int[n + 1][n + 1];
        StringTokenizer st = null;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            m[i] = new Matrix(y, x);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(execute(1, n));
    }

    private static int execute(int start, int end) {
        int result = Integer.MAX_VALUE;
        if (dp[start][end] != -1) {
            return dp[start][end];
        }
        if (start == end) {
            return 0;
        }
        if (start + 1 == end) {
            return m[start].y * m[start].x * m[end].x;
        }
        for (int i = start; i < end; i++) {
            result = Math.min(result, execute(start, i) + execute(i + 1, end) + m[start].y * m[i].x * m[end].x);
        }
        return dp[start][end] = result;
    }



}

class Matrix {
    int y;
    int x;
    public Matrix(int y, int x) {
        this.y = y;
        this.x = x;
    }
}