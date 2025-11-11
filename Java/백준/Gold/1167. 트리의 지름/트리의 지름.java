import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    private static ArrayList<ArrayList<Edge>> tree;
    private static boolean[] visited;
    private static int answer = 0;
    private static int farNode;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idxNode = Integer.parseInt(st.nextToken());
            while (true) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) {
                    break;
                }
                int dist = Integer.parseInt(st.nextToken());
                Edge edge1 = new Edge(end, dist);
                Edge edge2 = new Edge(idxNode, dist);
                tree.get(idxNode).add(edge1);
                tree.get(end).add(edge2);
            }
        }
        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];
        dfs(farNode, 0);

        System.out.println(answer);
    }

    private static void dfs(int start, int curDist) {
        visited[start] = true;
        if (curDist > answer) {
            answer = curDist;
            farNode = start;
        }
        for (Edge e : tree.get(start)) {
            if (!visited[e.getEnd()]) {
                dfs(e.getEnd(), curDist + e.getDist());
            }
        }
    }
}

class Edge {
    private int end;
    private int dist;

    public Edge(int end, int dist) {
        this.end = end;
        this.dist = dist;
    }

    public int getEnd() {
        return end;
    }

    public int getDist() {
        return dist;
    }
}