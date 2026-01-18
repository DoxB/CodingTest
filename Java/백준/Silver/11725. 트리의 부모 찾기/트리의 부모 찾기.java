import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer>[] adj;
    private static boolean[] visited;
    private static int[] answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adj[n1].add(n2);
            adj[n2].add(n1);
        }

        dfs(1);
        for (int i = 2; i <= n; i++) {
            System.out.println(answer[i]);
        }
    }

    private static void dfs(int curNum) {
        visited[curNum] = true;
        for (int i : adj[curNum]) {
            if (!visited[i]) {
                answer[i] = curNum;
                dfs(i);
            }
        }
    }
}