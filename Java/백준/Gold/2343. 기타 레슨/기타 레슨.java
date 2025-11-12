import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int end = 0;
        int start = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            end += arr[i];
            start = Math.max(start, arr[i]);
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            int tmpSum = 0;
            int count = 1;
            for (int i = 0; i < n; i++) {
                if (tmpSum + arr[i] > mid) {
                    tmpSum = 0;
                    count++;
                }
            tmpSum += arr[i];
            }

            if (count > m) {
                start = mid + 1;
            } else if (count <= m) {
                end = mid - 1;
            }
        }

        bw.write(Integer.toString(end + 1));
        bw.flush();
        br.close();
        bw.close();
    }
}