import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    private static int[] check;
    private static boolean[] visited;
    private static ArrayList<Integer>[] adj;
    private static String answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            visited = new boolean[v + 1];
            adj = new ArrayList[v + 1];
            check = new int[v + 1];
            for (int j = 1; j <= v; j++) {
                adj[j] = new ArrayList<Integer>();
            }
            for (int a = 0; a < e; a++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                adj[start].add(end);
                adj[end].add(start);
            }
            answer = "YES";
            for (int b = 1; b <= v; b++) {
                if (!visited[b]) {
                    check[b] = 1;
                    dfs(b);
                }
            }
            bw.write(answer);
            bw.newLine();
        }
        bw.flush();
    }

    private static void dfs(int start) {
        visited[start] = true;
        for (int nxt : adj[start]) {
            if (check[nxt] == check[start]) {
                answer = "NO";
                return;
            }
        }
        for (int nxt : adj[start]) {
            if (!visited[nxt]) {
                check[nxt] = 3 - check[start];
                dfs(nxt);
            }
        }
    }

}
