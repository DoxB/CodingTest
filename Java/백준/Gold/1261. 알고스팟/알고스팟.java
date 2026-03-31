import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    private static int n;
    private static int m;
    private static int[][] map;
    private static int[][] dist;
    private static int[] dy = {1, 0, -1, 0};
    private static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            String p = br.readLine();
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(p.substring(j, j + 1));
                map[i][j] = num;
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dks(0, 0);
        System.out.println(dist[n - 1][m - 1]);
    }
    
    private static boolean isOk(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    private static void dks(int y, int x) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(y, x, map[y][x]));
        dist[y][x] = map[y][x];
        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            for (int i = 0; i < 4; i++) {
                int nxtY = cur.y + dy[i];
                int nxtX = cur.x + dx[i];
                if (isOk(nxtY, nxtX)) {
                    if (dist[nxtY][nxtX] > cur.value + map[nxtY][nxtX]) {
                        pq.add(new Node(nxtY, nxtX, cur.value + map[nxtY][nxtX]));
                        dist[nxtY][nxtX] = cur.value + map[nxtY][nxtX];
                    }
                }
            }
        }
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