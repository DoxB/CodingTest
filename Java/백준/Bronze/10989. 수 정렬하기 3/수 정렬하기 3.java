import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[10001];

        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(br.readLine());
            arr[tmp]++;
        }

        for (int i = 1; i < 10001; i++) {
            if (arr[i] == 0) continue;
            for (int j = 0; j < arr[i]; j++) {
                bw.write(Integer.toString(i));
                bw.newLine();
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}