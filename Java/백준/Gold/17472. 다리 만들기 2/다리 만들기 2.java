import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
    private static int[][] adj;
    private static int[] move_y = {1, 0, -1, 0};
    private static int[] move_x = {0, 1, 0, -1};
    private static boolean[][] visited;
    private static int n;
    private static int m;
    private static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[n][m];

        int flag = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(adj[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j, flag);
                    flag++;
                }
            }
        }

        parent = new int[flag];
        for (int i = 1; i < flag; i++) {
            parent[i] = i;
        }

        // 다리 찾기
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(adj[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int nxt_y = i + move_y[k];
                        int nxt_x = j + move_x[k];
                        if (checkLine(nxt_y, nxt_x) && adj[nxt_y][nxt_x] == 0) {
                            int brigeCount = 1;
                            int startCity = adj[i][j];
                            int endCity = -1;
                            while (adj[nxt_y][nxt_x] == 0) {
                                nxt_y += move_y[k];
                                nxt_x += move_x[k];
                                if (!checkLine(nxt_y, nxt_x)) break;
                                if (adj[nxt_y][nxt_x] != 0) {
                                    endCity = adj[nxt_y][nxt_x];
                                    break;
                                }
                                brigeCount++;
                            }
                            if ((startCity != endCity) && brigeCount >= 2 && endCity != -1) {
                                pq.add(new Edge(startCity, endCity, brigeCount));
                            }
                        }
                    }
                }
            }
        }

        int useBrige = 0;
        int answer = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.remove();
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                answer += edge.value;
                useBrige++;
            }
        }

        if (useBrige == flag - 2) {
            System.out.println(answer);
        } else {
            System.out.println("-1");
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static void bfs(int y, int x, int flag) {
        Queue<MapLocation> q = new LinkedList<>();
        MapLocation start = new MapLocation(y, x);
        q.add(start);
        visited[y][x] = true;
        adj[y][x] = flag;
        while (!q.isEmpty()) {
            MapLocation cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nxt_y = cur.y + move_y[i];
                int nxt_x = cur.x + move_x[i];
                if (checkLine(nxt_y, nxt_x)) {
                    if (!visited[nxt_y][nxt_x] && adj[nxt_y][nxt_x] != 0) {
                        MapLocation next = new MapLocation(nxt_y, nxt_x);
                        q.add(next);
                        visited[nxt_y][nxt_x] = true;
                        adj[nxt_y][nxt_x] = flag;
                    }
                }
            }
        }
    }

    private static boolean checkLine(int y, int x) {
        if (0 <= y && y < n && 0 <= x && x < m) {
            return true;
        }
        return false;
    }
}

class MapLocation {
    int y;
    int x;

    public MapLocation(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Edge implements Comparable<Edge> {
    int start;
    int end;
    int value;

    public Edge (int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
    @Override
    public int compareTo(Edge edge) {
        if (this.value > edge.value) {
            return 1;
        } else if (this.value == edge.value) {
            return 0;
        }
        return -1;
    }
}