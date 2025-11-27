import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long startNum = Long.parseLong(st.nextToken());
        long endNum = Long.parseLong(st.nextToken());
        boolean[] primeList = new boolean[10000001];

        int ans = 0;
        for (int i = 2; i <= (int) Math.sqrt(endNum); i++) {
            primeList[i] = true;
        }
        for (int i = 2; i <= (int) Math.sqrt(endNum); i++) {
            if (primeList[i]) {
                for (int j = i + i; j <= (int) Math.sqrt(endNum); j += i) {
                    primeList[j] = false;
                }
            }
        }

        for (int i = 2; i <= (int) Math.sqrt(endNum); i++) {
            if (primeList[i]) {
                double tmp = (double) i * i;
                while (tmp <= (double) endNum) {
                    if (tmp >= (double) startNum) {
                        ans++;
                    }
                    tmp *= i;
                }
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        br.close();
        bw.close();
    }
}