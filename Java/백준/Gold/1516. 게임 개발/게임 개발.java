import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        int[] buildTime = new int[n + 1];
        int[] indegree = new int[n + 1];
        int[] answer = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken());
            int preBuild = Integer.parseInt(st.nextToken());
            while (preBuild != -1) {
                adj[preBuild].add(i);
                indegree[i]++;
                preBuild = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int nxt : adj[cur]) {
                indegree[nxt]--;
                answer[nxt] = Math.max(answer[nxt], answer[cur] + buildTime[cur]);
                if (indegree[nxt] == 0) {
                    q.add(nxt);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(answer[i] + buildTime[i]);
        }
    }
}