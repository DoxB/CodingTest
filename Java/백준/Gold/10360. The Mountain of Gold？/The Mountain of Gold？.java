import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    private static int n, m;
    private static ArrayList<Edge> edges;
    private static ArrayList<Node>[] adj;
    private static HashSet<Integer> cycles;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        int idx = 1;
        while (idx <= TC) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            cycles = new HashSet<>();
            adj = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                edges.add(new Edge(start, end, value));
                adj[start].add(new Node(end, value));
            }
            
            bf(0);
            System.out.print("Case #" + idx + ": ");
            if (!cycles.isEmpty()) {
                boolean flag = false;
                for (int num : cycles) {
                    if (bfs(num, 0)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("possible");
                } else {
                    System.out.println("not possible");
                }
            } else {
                System.out.println("not possible");
            }
            
            idx++;
        }

    }

    private static boolean bfs(int start, int target) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[start] = true;
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node cur = q.remove();
            if (cur.end == target) return true;
            for (Node nxt : adj[cur.end]) {
                if (!visited[nxt.end]) {
                    visited[nxt.end] = true;
                    q.add(new Node(nxt.end, cur.value + nxt.value));
                }
            }
        }

        return false;
    }

    private static void bf(int s) {
        int[] dist = new int[n];
        int INF = 100000000;
        Arrays.fill(dist, INF);
        dist[s] = 0;
        for (int cnt = 1; cnt <= n; cnt++) {
            boolean updated = false;
            for (Edge edge : edges) {
                int start = edge.start;
                int end = edge.end;
                int value = edge.value;

                if (dist[start] != INF && dist[end] > dist[start] + value) {
                    dist[end] = dist[start] + value;
                    updated = true;
                    if (n == cnt) {
                        cycles.add(start);
                        cycles.add(end);
                    }
                }
            }
            if (!updated) break;
        }
    }
}

class Edge {
    int start;
    int end;
    int value;

    public Edge(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
}

class Node {
    int end;
    int value;

    public Node(int end, int value) {
        this.end = end;
        this.value = value;
    }
}