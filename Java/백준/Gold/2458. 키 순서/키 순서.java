import java.io.*;
import java.util.StringTokenizer;

public class Main {
   public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] know = new boolean[n + 1][n + 1];
        int[] cnt = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            know[start][end] = true;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (know[i][k] && know[k][j])
                        know[i][j] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (know[i][j] || know[j][i]) {
                    cnt[i]++;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (cnt[i] == n - 1) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}