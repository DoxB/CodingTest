import java.io.*;

public class Main {
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        br.close();

        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);
    }

    private static void dfs(int num, int count) {
        if (count == n) {
            if (isPrime(num)) {
                System.out.println(num);
                return;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue;
            }
            int tmp = (num * 10) + i;
            if (isPrime(tmp)) {
                dfs(tmp, count + 1);
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}