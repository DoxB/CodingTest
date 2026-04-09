import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] adj = new int[n + 1][n + 1];
        int INF = 1000001;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(adj[i], INF);
            adj[i][i] = 0;
        }
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adj[start][end] = 1;
            adj[end][start] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        int[] dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int maxDist = 0;
            for (int j = 1; j <= n; j++) {
                if (i != j && adj[i][j] != INF) {
                    maxDist = Math.max(maxDist, adj[i][j]);
                }
            }

            dist[i] = maxDist;
        }


        int cnt = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            else {
                cnt++;
                int idx = i;
                int minValue = dist[i];
                for (int j = 1; j <= n; j++) {
                    if (adj[i][j] != INF) {
                        visited[j] = true;
                        if (dist[j] < minValue) {
                            minValue = dist[j];
                            idx = j;
                        }
                    }
                }
                ans.add(idx);
            }
        }

        Collections.sort(ans);
        
        System.out.println(cnt);
        for (int i : ans) {
            System.out.println(i);
        }
    }
}