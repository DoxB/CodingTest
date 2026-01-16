import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] rel = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    rel[i][j] = 0;
                } else {
                    rel[i][j] = 10000001;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            rel[s][e] = 1;
            rel[e][s] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (rel[i][j] > rel[i][k] + rel[k][j]) {
                        rel[i][j] = rel[i][k] + rel[k][j];
                    }
                }
            }
        }

        int minNum = Integer.MAX_VALUE;
        int idx = -1;
        for (int i = 1; i <= n; i++) {
            int tmp = 0;
            for (int j = 1; j <= n; j++) {
                tmp += rel[i][j];
            }
            if (minNum > tmp) {
                minNum = tmp;
                idx = i;
            }
        }

        System.out.println(idx);
    }
}