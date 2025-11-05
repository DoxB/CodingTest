import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    private static boolean[] visited;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[n];

        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adj.get(start).add(end);
            adj.get(end).add(start);
        }

        for (int i = 0; i < n; i++) {
            if (answer != 1) {
                visited[i] = true;
                dfs(i, 0);
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int num, int count) {
        if (count == 4) {
            answer = 1;
            return;
        }
        visited[num] = true;
        for (int i : adj.get(num)) {
            if (!visited[i]) {
                dfs(i, count + 1);
            }
        }
        visited[num] = false;
    }

}