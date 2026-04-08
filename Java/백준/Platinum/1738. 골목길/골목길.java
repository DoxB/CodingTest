import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

public class Main {
    private static ArrayList<Edge> edges;
    private static ArrayList<Integer>[] adj;
    private static int[] parent;
    private static int n, m;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 최적의 경로가 안나오는 경우
        // 1. n으로 도착이 안될때
        // 2. N으로 갈 수 있는 양수 사이클이 있을때 <- 이거 판단하려면 가중치를 양수 음수 반전
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        edges = new ArrayList<>();
        parent = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = (-1) * Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, value));
            adj[start].add(end);
        }

        if (!bellFord(1)) {
            System.out.println(-1);
        } else if (dist[n] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            printPath(n);
        }
    }

    private static void printPath(int end) {
        ArrayList<Integer> path = new ArrayList<>();
        int cur = end;
        while (cur != 0) {
            path.add(cur);
            cur = parent[cur];
        }
        Collections.reverse(path);
        for (int idx : path) {
            System.out.print(idx + " ");
        }
        System.out.println();
    }


    private static boolean checkN(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.remove();
            if (cur == n) return true;
            for (int nxt : adj[cur]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    q.add(nxt);
                }
            }
        }

        return false;
    }

    private static boolean bellFord(int start) {
        dist[start] = 0;
        
        HashSet<Integer> hs = new HashSet<>();
        boolean update = false;
        for (int i = 1; i <= n; i++) {
            for (Edge e : edges) {
                if (dist[e.start] != Integer.MAX_VALUE && dist[e.end] > dist[e.start] + e.value) {
                    update = true;
                    parent[e.end] = e.start;
                    dist[e.end] = dist[e.start] + e.value;
                    if (i == n) {
                        hs.add(e.end);
                    }
                }
            }
            if (!update) break;
        }

        for (int idx : hs) {
            if (checkN(idx)) return false;
        }

        return true;
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