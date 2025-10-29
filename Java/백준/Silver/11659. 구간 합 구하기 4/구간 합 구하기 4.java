import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long[] arrSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arrSum[i] = arrSum[i - 1] + Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.write(Long.toString(arrSum[end] - arrSum[start - 1]));
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }
}