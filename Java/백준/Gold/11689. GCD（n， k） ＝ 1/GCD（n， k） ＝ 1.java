import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(br.readLine());
        long result = n;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                result = result - (result / i);
                while (n % i == 0) {
                    n = n / i;
                }
            }
        }

        if (n > 1) {
            result = result - result / n;
        }

        bw.write(Long.toString(result));
        bw.flush();
        br.close();
        bw.close();
    }
}