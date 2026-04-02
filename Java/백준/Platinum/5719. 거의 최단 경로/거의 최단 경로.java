import java.io.*;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Node>[] adj;
    private static ArrayList<Integer>[] parent;
    private static int n, m;
    private static boolean[][] edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            adj = new ArrayList[n];
            parent = new ArrayList[n];
            edge = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                adj[i] = new  ArrayList<>();
                parent[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                adj[start].add(new Node(end, value));
            }

            dks(s, e);
            removeEdge(e);
            int answer = dks(s, e);
            if (answer == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(answer);
            }
        }
    }

    private static int dks(int start, int end) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            if (cur.value > dist[cur.idx]) continue;

            for (Node nxt : adj[cur.idx]) {
                if (!edge[cur.idx][nxt.idx]) {
                    if (dist[nxt.idx] > cur.value + nxt.value) {
                        dist[nxt.idx] = cur.value + nxt.value;
                        parent[nxt.idx].clear();
                        parent[nxt.idx].add(cur.idx);
                        pq.add(new Node(nxt.idx, cur.value + nxt.value));
                    } else if (dist[nxt.idx] == cur.value + nxt.value) {
                        parent[nxt.idx].add(cur.idx);
                    }
                }
            }
        }
        return dist[end];
    }

    private static void removeEdge(int end) {
        for (int idx : parent[end]) {
            if (!edge[idx][end]) {
                edge[idx][end] = true;
                removeEdge(idx);
            }
        }
    }
}

class Node implements Comparable<Node> {
    int idx;
    int value;

    public Node(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }

    @Override
    public int compareTo(Node n) {
        if (this.value - n.value > 0) {
            return 1;
        }
        return -1;
    }
}