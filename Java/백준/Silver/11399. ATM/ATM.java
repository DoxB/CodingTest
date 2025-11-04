import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int[] ansArr = new int[n];
        ansArr[0] = arr[0];
        for (int i = 1; i < n; i++) {
            ansArr[i] = ansArr[i - 1] + arr[i];
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer += ansArr[i];
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}