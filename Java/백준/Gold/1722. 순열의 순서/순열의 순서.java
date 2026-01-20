import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] facto = new long[n + 1];
        facto[0] = 1;
        for (int i = 1; i <= n; i++) {
            facto[i] = facto[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sol = Integer.parseInt(st.nextToken());
        int[] search = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        if (sol == 1) {
            long k = Long.parseLong(st.nextToken());
            for (int i = 1; i <= n; i++) {
                int cnt = 1;
                for (int j = 1; j <= n; j++) {
                    if (visited[j]) continue;
                    if (k <= cnt * facto[n - i]) {
                        k -= (cnt - 1) * facto[n - i];
                        search[i] = j;
                        visited[j] = true;
                        break;
                    }
                    cnt++;
                }
            }

            for (int i = 1; i <= n; i++) {
                System.out.print(search[i] + " ");
            }

        } else if (sol == 2) {
            long ans = 1;
            for (int i = 1; i <= n; i++) {
                int num = Integer.parseInt(st.nextToken());
                long cnt = 0;
                for (int j = 1; j < num; j++) {
                    if (!visited[j]) {
                        cnt++;
                    }
                }
                ans += cnt * facto[n - i];
                visited[num] = true;
            }

            System.out.println(ans);
        }
    }
}