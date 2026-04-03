import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Main {
    private static int n, m, f, s, t;
    private static ArrayList<Node>[] adj;
    private static long[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n];
        dist = new long[n][2];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            dist[i][0] = Long.MAX_VALUE;
            dist[i][1] = Long.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long value = Long.parseLong(st.nextToken());
            adj[start].add(new Node(end, value, 0));
            adj[end].add(new Node(start, value, 0));
        }

        for (int i = 0; i < f; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adj[start].add(new Node(end, 0, 0));
        }

        long answer = dks(s, t);
        System.out.println(answer);
    }

    private static long dks(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start][0] = 0;
        pq.add(new Node(start, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            if (cur.value > dist[cur.idx][cur.used]) continue;
            for (Node nxt : adj[cur.idx]) {
                if (nxt.value == 0) {
                    if (cur.used == 1) continue;
                    else {
                        if (dist[nxt.idx][1] > cur.value) {
                            dist[nxt.idx][1] = cur.value;
                            pq.add(new Node(nxt.idx, cur.value, 1));
                        }
                    }
                } else {
                    if (dist[nxt.idx][cur.used] > cur.value + nxt.value) {
                        dist[nxt.idx][cur.used] = cur.value + nxt.value;
                        pq.add(new Node(nxt.idx, cur.value + nxt.value, cur.used));
                    }
                }
            }
        }

        return Math.min(dist[end][0], dist[end][1]);
    }
}

class Node implements Comparable<Node> {
    int idx;
    long value;
    int used;

    public Node(int idx, long value, int used) {
        this.idx = idx;
        this.value = value;
        this.used = used;
    }

    @Override
    public int compareTo(Node n) {
        if (this.value - n.value > 0) {
            return 1;
        }
        return -1;
    }
}