import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // n번째 포함, n기준 왼쪽 오른쪽 계산
        // 쭉쭉 이어나가는 도중에 그동안의 합보다 n번째 자리수가 더 크면 n번째부터 계산 다시 시작
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int ans = arr[0];
        leftMax[0] = arr[0];
        rightMax[n - 1] = arr[n - 1];

        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(arr[i], leftMax[i - 1] + arr[i]);
            if (ans < leftMax[i]) {
                ans = leftMax[i];
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(arr[i], rightMax[i + 1] + arr[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            int remove = leftMax[i - 1] + rightMax[i + 1];
            if (ans < remove) {
                ans = remove;
            }
        }

        System.out.println(ans);
    }
}