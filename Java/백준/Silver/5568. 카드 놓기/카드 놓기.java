import java.io.*;
import java.util.HashSet;

public class Main {
    private static String[] arr;
    private static HashSet<String> hs;
    private static boolean[] visited;
    private static int n;
    private static int k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }
        hs = new HashSet<>();
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            visited[i] = true;
            dfs(i, 1, arr[i]);
            visited[i] = false;
        }

        System.out.println(hs.size());
    }

    private static void dfs(int idx, int depth, String cur) {
        if (depth == k) {
            hs.add(cur);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, depth + 1, cur + arr[i]);
                visited[i] = false;
            }
        }
    }
}