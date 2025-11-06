import java.io.*;
import java.util.StringTokenizer;

// 각 종류 색종이 5개씩

public class Main {
    private static int[][] adj = new int[10][10];
    private static int[] paper = {0, 5, 5, 5, 5, 5};
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        if (answer == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(Integer.toString(answer));
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int yx, int count) {
        if (count >= answer) return;
        if (yx == 100) {
            if (answer > count) {
                answer = count;
            }
            return;
        }
        int y = yx / 10;
        int x = yx % 10;

        if (adj[y][x] == 1) {
            for (int i = 1; i <= 5; i++) {
                if (paper[i] > 0 && check(y, x, i)) {
                    fill(y, x, i, 0);
                    paper[i]--;
                    dfs(yx + 1, count + 1);
                    fill(y, x, i, 1);
                    paper[i]++;
                }
            }
        } else {
            dfs(yx + 1, count);
        }
    }

    private static boolean check(int y, int x, int size) {
        if ((y + size) > 10 || (x + size) > 10) return false;
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (adj[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static void fill(int y, int x, int size, int flag) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                adj[i][j] = flag;
            }
        }
    }

}