import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] degree = new int[n + 1];
        int[] time = new int[n + 1];
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            time[i] = t;
            result[i] = t;
            for (int j = 0; j < d; j++) {
                int prev = Integer.parseInt(st.nextToken());
                adj[prev].add(i);
                degree[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int nxt : adj[cur]) {
                degree[nxt]--;
                result[nxt] = Math.max(result[nxt], result[cur] + time[nxt]);
                if (degree[nxt] == 0) {
                    q.add(nxt);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, result[i]);
        }

        System.out.println(answer);
    }
}