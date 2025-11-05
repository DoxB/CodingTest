import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    private static boolean[] visited;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
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

        for (int i = 1; i <= n; i++) {
            if (visited[i] == false) {
                dfs(i);
                answer++;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int node) {
        visited[node] = true;
        for (int nxtNode : adj.get(node)) {
            if (visited[nxtNode] == false) {
                dfs(nxtNode);
            }
        }
    }
}