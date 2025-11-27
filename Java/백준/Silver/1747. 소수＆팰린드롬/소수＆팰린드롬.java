import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean[] isPrime = new boolean[1004001];

        int n = Integer.parseInt(br.readLine());
        for (int i = 2; i < 1004001; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i <= Math.sqrt(1004001); i++) {
            if (isPrime[i]) {
                for (int j = i + i; j < 1004001; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int ans = -1;
        for (int i = n; i < 1004000; i++) {
            if (isPrime[i]) {
                if (isPal(i)) {
                    ans = i;
                    break;
                }
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean isPal(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int startIdx = 0;
        int endIdx = arr.length - 1;
        while (startIdx < endIdx) {
            if (arr[startIdx] != arr[endIdx]) {
                return false;
            }
            startIdx++;
            endIdx--;
        }
        return true;
    }
}