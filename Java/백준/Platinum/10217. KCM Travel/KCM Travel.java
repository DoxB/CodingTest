import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int q = 0; q < T; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            ArrayList<Node>[] adj = new ArrayList[n + 1];
            int[][] dist = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                adj[s].add(new Node(e, d, c));
            }
            for (int i = 1; i <= n; i++) {
                Collections.sort(adj[i]);
            }

            int[][] ans = dks(1, adj, dist, n, m);
            int answer = Integer.MAX_VALUE;
            for (int i = 0; i <= m; i++) {
                answer = Math.min(answer, ans[n][i]);
            }

            if (answer == Integer.MAX_VALUE) {
                System.out.println("Poor KCM");
            } else {
                System.out.println(answer);
            }
        }
    }

    private static int[][] dks(int start, ArrayList<Node>[] adj, int[][] dist, int n, int m) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start][0] = 0;
        pq.add(new Node(start, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            
            if (cur.value > dist[cur.idx][cur.price]) continue;

            if (cur.idx == n) break;

            for (Node nxt : adj[cur.idx]) {
                int nxtValue = cur.value + nxt.value;
                int nxtPrice = cur.price + nxt.price;

                if (nxtPrice <= m && dist[nxt.idx][nxtPrice] > nxtValue) {
                    for (int i = nxtPrice; i <= m; i++) {
                        if (dist[nxt.idx][i] <= nxtValue) break;
                        dist[nxt.idx][i] = nxtValue;
                    }
                    pq.add(new Node(nxt.idx, nxtValue, nxtPrice));
                }
            }
        }

        return dist;
    }
}

class Node implements Comparable<Node> {
    int idx;
    int value;
    int price;

    public Node(int idx, int value, int price) {
        this.idx = idx;
        this.value = value;
        this.price = price;
    }

    @Override
    public int compareTo(Node n) {
        if (this.value - n.value > 0) {
            return 1;
        } else if (this.value == n.value) {
            if (this.price - n.price > 0) {
                return 1;
            }
        }
        return -1;
    }
}