import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    private static int n, m, d, e;
    private static long[] height;
    private static ArrayList<Node>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        height = new long[n + 1];
        adj = new ArrayList[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long value = Long.parseLong(st.nextToken());
            adj[start].add(new Node(end, value));
            adj[end].add(new Node(start, value));
        }

        // 얻은 성취감은 고정, 체력소모량을 최소한으로 줄여야함
        // 다익스트라로 체력 소모량 최소 탐색
        long[] goGoal = dks(1);
        long[] downTarget = dks(n);

        long answer = Long.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i <= n; i++) {
            if (goGoal[i] == Long.MAX_VALUE || downTarget[i] == Long.MAX_VALUE) continue;
            long calValue = height[i] * e - (goGoal[i] + downTarget[i]) * d;
            answer = Math.max(calValue, answer);
            flag = true;
        }

        if (flag) {
            System.out.println(answer);
        } else {
            System.out.println("Impossible");
        }
        
    }

    private static long[] dks(int start) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.remove();
            if (cur.value > dist[cur.idx]) continue;

            for (Node nxt : adj[cur.idx]) {
                if (height[nxt.idx] <= height[cur.idx]) continue;
                if (dist[nxt.idx] > cur.value + nxt.value) {
                    dist[nxt.idx] = cur.value + nxt.value;
                    pq.add(new Node(nxt.idx, cur.value + nxt.value));
                }
            }
        }

        return dist;
    }
}

class Node implements Comparable<Node> {
    int idx;
    long value;

    public Node(int idx, long value) {
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