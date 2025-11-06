import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int m;
    private static boolean[] visited;
    private static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new String[m];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++){
            dfs(i, 0);
        }
        br.close();
    }

    private static void dfs(int num, int count) {
        arr[count] = Integer.toString(num);
        if (count == m - 1) {
            System.out.println(String.join(" ", arr));
            return;
        }
        visited[num] = true;
        for (int i = 1; i <= n; i++) {
            if (visited[i] == false) {
                dfs(i, count + 1);
            }
        }
        visited[num] = false;
    }
}