import java.io.*;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Node>[] adj;
    private static int[] parent;
    private static int[] dist;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n + 1];
        parent = new int[n + 1];
        dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[s].add(new Node(e, v));
            adj[e].add(new Node(s, v));
        }

        dks(1);
        System.out.println(n - 1);
        for (int i = 2; i <= n; i++) {
            if (parent[i] == 0) continue;
            System.out.println(i +" "+ parent[i]);
        }
    }

    private static void dks(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            if (dist[cur.idx] < cur.value) continue;

            for (Node nxt : adj[cur.idx]) {
                if (dist[nxt.idx] > cur.value + nxt.value) {
                    dist[nxt.idx] = cur.value + nxt.value;
                    parent[nxt.idx] = cur.idx;
                    pq.add(new Node(nxt.idx, cur.value + nxt.value));
                }
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