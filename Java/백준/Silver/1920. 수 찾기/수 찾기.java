import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] example = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            example[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(example);

        int m = Integer.parseInt(br.readLine());
        int[] search = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            search[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            boolean flag = false;
            int idxNum = search[i];
            int start = 0;
            int end = example.length - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (example[mid] > idxNum) {
                    end = mid - 1;
                } else if (example[mid] < idxNum) {
                    start = mid + 1;
                } else {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                bw.write("1");
                bw.newLine();
            } else {
                bw.write("0");
                bw.newLine();
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}