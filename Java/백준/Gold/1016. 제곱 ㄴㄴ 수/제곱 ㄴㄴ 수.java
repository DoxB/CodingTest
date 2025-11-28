import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long minNum = Long.parseLong(st.nextToken());
        long maxNum = Long.parseLong(st.nextToken());

        boolean[] isNoNo = new boolean[(int) (maxNum - minNum + 1)];
        for (int i = 2; i <= Math.sqrt(maxNum); i++) {
            long powNum = (long) i * (long) i;
            long startNum = 0;
            if (minNum % powNum == 0) {
                startNum = minNum;
            } else {
                startNum = ((minNum / powNum) + 1) * powNum;
            }
            for (long j = startNum; j <= maxNum; j += powNum) {
                isNoNo[(int) (j - minNum)] = true;
            }
        }

        int ans = 0;
        for (int i = 0; i < maxNum - minNum + 1; i++) {
            if (!isNoNo[i]) {
                ans++;
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        br.close();
        bw.close();
    }
}