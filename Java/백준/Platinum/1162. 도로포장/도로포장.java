import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Main {
    private static int n, m, k;
    private static ArrayList<Node>[] adj;
    private static long[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        dist = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            for (int j = 0; j <= k; j++) {
                dist[i][j] = Long.MAX_VALUE;
            }
        }

        // 길 생성할 때 모든 길 각각 포장되는 길도 생성
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long v = Long.parseLong(st.nextToken());
            adj[s].add(new Node(e, v, 0));
            adj[e].add(new Node(s, v, 0));
            adj[s].add(new Node(e, 0, 0));
            adj[e].add(new Node(s, 0, 0));
        }

        long answer = dks(1, n);
        System.out.println(answer);
    }

    private static long dks(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start][0] = 0;
        pq.add(new Node(start, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            if (cur.value > dist[cur.idx][cur.pave]) continue;

            for (Node nxt : adj[cur.idx]) {
                if (nxt.value == 0) {
                    if (cur.pave < k) {
                        if (dist[nxt.idx][cur.pave + 1] > cur.value) {
                            dist[nxt.idx][cur.pave + 1] = cur.value;
                            pq.add(new Node(nxt.idx, cur.value, cur.pave + 1));
                        }
                    }
                } else {
                    if (dist[nxt.idx][cur.pave] > cur.value + nxt.value) {
                        dist[nxt.idx][cur.pave] = cur.value + nxt.value;
                        pq.add(new Node(nxt.idx, cur.value + nxt.value, cur.pave));
                    }
                }
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            answer = Math.min(answer, dist[end][i]);
        }

        return answer;
    }
}

class Node implements Comparable<Node> {
    int idx;
    long value;
    int pave;

    public Node(int idx, long value, int pave) {
        this.idx = idx;
        this.value = value;
        this.pave = pave;
    }

    @Override
    public int compareTo(Node n) {
        if (this.value - n.value > 0) {
            return 1;
        }
        return -1;
    }
}