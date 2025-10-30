import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int answer = 0;
        int num = Integer.parseInt(br.readLine());

        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = i + 1;
        }

        int start = 0;
        int end = 0;
        int curSum = arr[0];
        while (end < num) {
            if (curSum == num) {
                answer++;
                end++;
                if (end < num) curSum += arr[end];
            } else if (curSum < num) {
                end++;
                curSum += arr[end];
            } else if (curSum > num) {
                curSum -= arr[start];
                start++;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
