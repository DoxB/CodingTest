import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long b = Long.parseLong(st.nextToken());
        long a = Long.parseLong(st.nextToken());

        long num = gcd(a, b);
        StringBuilder sb = new StringBuilder();
        for (long i = 0; i < num; i++) {
            sb.append("1");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static Long gcd(Long a, Long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}