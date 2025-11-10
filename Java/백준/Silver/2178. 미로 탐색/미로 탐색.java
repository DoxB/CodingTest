import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    private static int[][] adj;
    private static int n;
    private static int m;
    private static boolean[][] visited;
    private static int[] dy = {1, 0, -1, 0};
    private static int[] dx = {0, 1, 0, -1};
    private static int answer = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][m];
        adj = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                adj[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        MyNode startNode = new MyNode(0, 0, 1);
        bfs(startNode, n - 1, m - 1);

        bw.write(Integer.toString(answer));
        bw.flush();

        br.close();
        bw.close();
    }

    private static boolean check(int y, int x) {
        if (0 <= y && y < n && 0 <= x && x < m) {
            return true;
        }
        return false;
    }

    private static void bfs(MyNode myNode, int targetY, int targetX) {
        visited[myNode.getY()][myNode.getX()] = true;
        Queue<MyNode> q = new LinkedList<>();
        q.add(myNode);

        while (q.size() > 0) {
            MyNode curNode = q.remove();
            int curY = curNode.getY();
            int curX = curNode.getX();
            int curCount = curNode.getCount();
            if (targetY == curY && targetX == curX) {
                answer = curCount;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nxtY = curY + dy[i];
                int nxtX = curX + dx[i];
                if (check(nxtY, nxtX) && !visited[nxtY][nxtX] &&adj[nxtY][nxtX] == 1) {
                    MyNode nxtNode = new MyNode(nxtY, nxtX, curCount + 1);
                    visited[nxtY][nxtX] = true;
                    q.add(nxtNode);
                }
            }
        }
    }
}

class MyNode {
    private int y;
    private int x;
    private int count;

    public MyNode(int y, int x, int count) {
        this.y = y;
        this.x = x;
        this.count = count;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getCount() {
        return count;
    }
}