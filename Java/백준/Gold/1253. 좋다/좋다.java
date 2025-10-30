import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int idxNum = arr[i];
            int start = 0;
            int end = n - 1;
            while (start < end) {
                int sum = arr[start] + arr[end];
                if (sum == idxNum) {
                    if (start != i && end != i) {
                        answer++;
                        break;
                    } else if (start == i) {
                        start++;
                    } else {
                        end--;
                    }
                } else if (sum < idxNum) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
