import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static boolean[] visited;
    private static int[] depth;
    private static int maxDept;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        maxDept = 0;
        int idxNum = n;
        while (idxNum > 1) {
            idxNum /= 2;
            maxDept++;
        }
        tree = new ArrayList[n + 1];
        parent = new int[maxDept + 1][n + 1];
        visited = new boolean[n + 1];
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
        for (int k = 1; k <= maxDept; k++) {
            for (int i = 1; i <= n; i++) {
                parent[k][i] = parent[k - 1][parent[k - 1][i]];
            }
        }

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
        for (int k = maxDept; k >= 0; k--) {
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[parent[k][b]]) {
                    b = parent[k][b];
                }
            }
        }
        for (int k = maxDept; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        int lca = a;
        if (a != b) {
            lca = parent[0][lca];
        }
        return lca;
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
                    visited[nxt] = true;
                    depth[nxt] = depth[cur] + 1;
                    parent[0][nxt] = cur;
                }
            }
        }
    }
}