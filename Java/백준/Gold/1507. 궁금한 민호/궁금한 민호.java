import java.io.*;
import java.util.StringTokenizer;

public class Main {
   public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 최소 이동 시간을 구해놓았다고 한다
        // 노드 갯수 20개니깐 플루이드 활용
        // 최소 이동 시간이 또 업데이트가 될 수 없다 -> 불가능 (문제가 조금 이상하다.)
        // 거쳐서 갈 때, 직접적을 갈 때 비교해서 동일하면 직접적으로 가는 도로는 제거
        int n = Integer.parseInt(br.readLine());
        int[][] adj = new int[n][n];
        boolean[][] del = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j || i == k || j == k) continue;
                    if (adj[i][j] > adj[i][k] + adj[k][j]) {
                        System.out.println(-1);
                        return;
                    } else if (adj[i][j] == adj[i][k] + adj[k][j]) {
                        del[i][j] = true;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!del[i][j]) {
                    answer += adj[i][j];
                }
            }
        }

        System.out.println(answer / 2);
    }
}