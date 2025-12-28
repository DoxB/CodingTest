import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    private static boolean[] visited;
    private static ArrayList<Integer>[] adj;
    private static int[] answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adj[end].add(start);
        }

        int maxNum = 0;
        for (int i = 1; i <= n; i++) {
            Arrays.fill(visited, false);
            int count = bfs(i);
            answer[i] = count;
            if (maxNum < count) {
                maxNum = count;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (answer[i] == maxNum) {
                sb.append(Integer.toString(i) + " ");
            }
        }

        System.out.println(sb.toString());
    }

    private static int bfs(int start) {
        int count = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int i : adj[cur]) {
                if (!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
