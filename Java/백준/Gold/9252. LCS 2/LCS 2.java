import java.io.*;
import java.util.ArrayList;


public class Main {
    private static long[][] dp;
    private static ArrayList<Character> path;
    private static char[] aArr;
    private static char[] bArr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        aArr = br.readLine().toCharArray();
        bArr = br.readLine().toCharArray();
        dp = new long[aArr.length + 1][bArr.length + 1];
        path = new ArrayList<Character>();
        for (int i = 1; i <= aArr.length; i++) {
            for (int j = 1; j <= bArr.length; j++) {
                if (aArr[i - 1] == bArr[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[aArr.length][bArr.length]);
        getText(aArr.length, bArr.length);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i));
        }
    }
    private static void getText(int y, int x) {
        if (y == 0 || x == 0) return;
        if (aArr[y - 1] == bArr[x - 1]) {
            path.add(aArr[y - 1]);
            getText(y - 1, x - 1);
        } else {
            if (dp[y - 1][x] > dp[y][x - 1]) {
                getText(y - 1, x);
            } else {
                getText(y, x - 1);
            }
        }
    }
}