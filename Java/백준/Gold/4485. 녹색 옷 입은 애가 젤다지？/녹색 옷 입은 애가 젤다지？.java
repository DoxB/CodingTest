import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    private static int[] dy = {1, 0, -1, 0};
    private static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        while (true) {
            cnt++;
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            
            int[][] adj = new int[n][n];
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    adj[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = dks(adj, n);
            
            System.out.println("Problem " + cnt + ": " + answer);
        }
    }

    private static boolean isOk(int y, int x, int n) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

    private static int dks(int[][] adj, int n) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, adj[0][0]));
        dist[0][0] = adj[0][0];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curY = cur.y;
            int curX = cur.x;
            int curV = cur.value;

            for (int i = 0; i < 4; i++) {
                int nxtY = curY + dy[i];
                int nxtX = curX + dx[i];
                if (isOk(nxtY, nxtX, n)) {
                    if (dist[nxtY][nxtX] > curV + adj[nxtY][nxtX]) {
                        dist[nxtY][nxtX] = curV + adj[nxtY][nxtX];
                        pq.add(new Node(nxtY, nxtX, curV + adj[nxtY][nxtX]));
                    }
                }
            }
        }
        return dist[n - 1][n - 1];
    }

}

class Node implements Comparable<Node> {
    int y;
    int x;
    int value;

    public Node(int y, int x, int value) {
        this.y = y;
        this.x = x;
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
