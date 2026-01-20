import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    private static ArrayList<Integer>[] tree;
    private static boolean[] visited;
    private static int[] parent;
    private static int[] depth;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        tree = new ArrayList[n + 1];
        parent = new int[n + 1];
        depth = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        bfs(1);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            System.out.println(executeLCA(n1, n2));
        }
    }

    private static int executeLCA(int a, int b) {
        if (depth[a] > depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        while (depth[b] != depth[a]) {
            b = parent[b];
        }

        while (b != a) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int nxt : tree[cur]) {
                if (!visited[nxt]) {
                    q.add(nxt);
                    parent[nxt] = cur;
                    depth[nxt] = depth[cur] + 1;
                    visited[nxt] = true;
                }
            }
        }
    }
}