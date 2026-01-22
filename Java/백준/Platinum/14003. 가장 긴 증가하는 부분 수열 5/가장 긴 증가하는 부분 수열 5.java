import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {
    private static int[] count;
    private static int[] tmpArr;
    private static int curLength;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        count = new int[n + 1];
        tmpArr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        curLength = 1;
        tmpArr[1] = arr[1];
        count[1] = 1;
        // 탐색하면서 순서 카운팅, 최대 순서 카운팅하기 위해 순서마다 최소값 저장할 배열
        // 최솟값 업데이트할 때 이분탐색으로 시간복잡도 줄이기 n -> logN
        for (int i = 2; i <= n; i++) {
            if (arr[i] > tmpArr[curLength]) {
                tmpArr[++curLength] = arr[i];
                count[i] = curLength;
            } else {
                int idx = binSearch(arr[i], 1, curLength);
                tmpArr[idx] = arr[i];
                count[i] = idx;
            }
        }

        System.out.println(curLength);

        Stack<Integer> ansStack = new Stack<>();
        for (int i = n; i >= 1; i--) {
            if (count[i] == curLength) {
                if (ansStack.isEmpty() || ansStack.peek() > arr[i]) {
                    ansStack.add(arr[i]);
                    curLength--;
                }
            }
        }
        while (!ansStack.isEmpty()) {
            int ans = ansStack.pop();
            System.out.print(ans + " ");
        }
    }

    private static int binSearch(int idxNum, int left, int right) {
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (tmpArr[mid] < idxNum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}