import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr;
    public static long answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sort(arr, 0, n - 1);

        bw.write(Long.toString(answer));
        bw.flush();
        br.close();
        bw.close();
    }


    private static void sort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = (left + right) / 2;

        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int leftLength = mid - left + 1;
        int rightLength = right - mid;

        int[] leftTmp = new int[leftLength];
        int[] rightTmp = new int[rightLength];

        for (int i = 0; i < leftLength; i++) {
            leftTmp[i] = arr[left + i];
        }
        for (int i = 0; i < rightLength; i++) {
            rightTmp[i] = arr[mid + 1 + i];
        }

        int leftIdx = 0;
        int rightIdx = 0;
        int oriIdx = left;

        while (leftIdx < leftLength && rightIdx < rightLength) {
            if (leftTmp[leftIdx] <= rightTmp[rightIdx]) {
                arr[oriIdx] = leftTmp[leftIdx];
                if ((left + leftIdx) > oriIdx) {
                    answer += (left + leftIdx) - oriIdx;
                }
                leftIdx++;
            } else {
                arr[oriIdx] = rightTmp[rightIdx];
                if ((rightIdx + mid + 1) > oriIdx) {
                    answer += (rightIdx + mid + 1) - oriIdx;
                }
                rightIdx++;
            }
            oriIdx++;
        }

        while (leftIdx < leftLength) {
            arr[oriIdx] = leftTmp[leftIdx];
            leftIdx++;
            oriIdx++;
        }

        while (rightIdx < rightLength) {
            arr[oriIdx] = rightTmp[rightIdx];
            rightIdx++;
            oriIdx++;
        }
    }
}