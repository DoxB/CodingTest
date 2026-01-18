import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer>[] adj;
    private static boolean[] visited;
    private static int delNode;
    private static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        int rootNode = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parentNode = Integer.parseInt(st.nextToken());
            if (parentNode == -1) {
                rootNode = i;
            } else {
                adj[i].add(parentNode);
                adj[parentNode].add(i);
            }
        }

        delNode = Integer.parseInt(br.readLine());
        answer = 0;
        if (delNode == rootNode) {
            System.out.println("0");
        } else {
            dfs(rootNode);
            System.out.println(answer);
        }
    }

    private static void dfs(int curNode) {
        visited[curNode] = true;
        int childNode = 0;
        for (int i : adj[curNode]) {
            if (!visited[i] && (i != delNode)) {
                childNode++;
                dfs(i);
            }
        }
        if (childNode == 0) {
            answer++;
        }
    }
}