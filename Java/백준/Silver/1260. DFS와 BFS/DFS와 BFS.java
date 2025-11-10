import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static ArrayList<ArrayList<Integer>> adj;
    private static boolean[] visited;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int startV = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adj.get(start).add(end);
            adj.get(end).add(start);
        }

        for (int i = 0; i <= n; i++) {
            Collections.sort(adj.get(i));
        }

        dfs(startV);
        System.out.println();

        visited = new boolean[n + 1];
        bfs(startV);

        br.close();
    }

    private static void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int node : adj.get(start)) {
            if (!visited[node]) {
                dfs(node);
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        while (q.size() > 0) {
            int cur = q.remove();
            System.out.print(cur + " ");
            for (int node : adj.get(cur)) {
                if (!visited[node]) {
                    visited[node] = true;
                    q.add(node);
                }
            }
        }
    }
}