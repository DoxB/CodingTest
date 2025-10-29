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
            arrSum[i] = arrSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        long[] arrMod = new long[m];
        for (int i = 1; i <= n; i++) {
            int tmp = (int) (arrSum[i] % m);
            arrMod[tmp]++;
        }

        long answer = 0;
        answer += arrMod[0];
        for (int i = 0; i < m; i++) {
            if (arrMod[i] > 1) {
                answer += arrMod[i] * (arrMod[i] - 1) / 2;
            }
        }

        bw.write(Long.toString(answer));
        bw.flush();
    }
}
