import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long start = 1;
        long end = k;
        long ans = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            long count = 0;

            for (int i = 1; i <= n; i++) {
                count += Math.min(mid / i, n);
            }

            if (count < k) {
                start = mid + 1;
            } else {
                ans = mid;
                end = mid - 1;
            }
        }

        bw.write(Long.toString(ans));
        bw.flush();
        br.close();
        bw.close();
    }
}