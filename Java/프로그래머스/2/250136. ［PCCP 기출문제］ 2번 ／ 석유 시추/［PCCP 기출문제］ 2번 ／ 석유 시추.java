import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    private static int[] mY = {1, 0, -1, 0};
    private static int[] mX = {0, 1, 0, -1};
    private static int n;
    private static int m;
    private static boolean[][] visited;
    private static ArrayList<Integer> size;
    private static int[][] idsMap;
    
    public int solution(int[][] land) {
        // 땅을 팔때마다 BFS 돌리면 무조건 시간 초과
        // 영역별로 구분하고, 영역별 크기 구해서 더하기
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        size = new ArrayList<>();
        idsMap = new int[n][m];
        
        // 세팅
        int idx = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    int value = bfs(i, j, land, idx++);
                    size.add(value);
                }
            }
        }
        
        int answer = 0;
        
        for (int x = 0; x < m; x++) {
            HashSet<Integer> hs = new HashSet<>();
            for (int y = 0; y < n; y++) {
                if (idsMap[y][x] != 0 && !hs.contains(idsMap[y][x])) {
                    hs.add(idsMap[y][x]);
                }
            }
            
            int total = 0;
            for (int s : hs) {
                total += size.get(s - 1);
            }
            
            answer = Math.max(answer, total);
        }
        
        return answer;
    }
    
    private static boolean isOk(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
    
    private static int bfs(int y, int x, int[][] land, int idx) {
        Queue<Node> q = new LinkedList<>();
        visited[y][x] = true;
        idsMap[y][x] = idx;
        q.add(new Node(y, x));
        int value = 1;
        
        while (!q.isEmpty()) {
            Node cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nY = cur.y + mY[i];
                int nX = cur.x + mX[i];
                if (isOk(nY, nX) && land[nY][nX] == 1 && !visited[nY][nX]) {
                    value++;
                    idsMap[nY][nX] = idx;
                    visited[nY][nX] = true;
                    q.add(new Node(nY, nX));
                }
            }
        }
        
        return value;
    }
}

class Node {
    int y;
    int x;
    
    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}