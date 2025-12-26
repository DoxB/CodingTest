import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

public class Main {
    private static ArrayList<Integer>[] adj;
    private static int[] visited;
    private static ArrayList<Integer> answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        visited = new int[n + 1];
        answer = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 1; i <= n; i++) {
            visited[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adj[start].add(end);
        }

        bfs(x);

        for (int i = 1; i <= n; i++) {
            if (visited[i] == k) {
                answer.add(i);
            }
        }

        if (answer.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(answer);
            for (int i : answer) {
                System.out.println(i);
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(start);
        visited[start] = 0;
        while (!q.isEmpty()) {
            int curNum = q.remove();
            for (int i : adj[curNum]) {
                if (visited[i] == -1) {
                    visited[i] = visited[curNum] + 1;
                    q.add(i);
                }
            }
        }
    }
}