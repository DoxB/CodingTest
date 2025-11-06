import java.io.*;

public class Main {
    private static int n;
    private static int[] board;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dfs(1, i);
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int y, int x) {
        if (y == n) {
            answer++;
            return;
        }
        board[y] = x;
        for (int i = 1; i <= n; i++) {
            if (checkQueen(y + 1, i)) {
                dfs(y + 1, i);
            }
        }
        board[y] = 0;
    }

    private static boolean checkQueen(int y, int x) {
        for (int i = 1; i < y; i++) { // y축 기준 쌓인 곳까지만
            if (x == board[i]) return false; // y축 체크
            if (Math.abs(y - i) == Math.abs(x - board[i])) return false; // 대각선 체크
        }
        return true;
    }
}