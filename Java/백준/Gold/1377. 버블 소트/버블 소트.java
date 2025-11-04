import java.io.*;
import java.util.Arrays;

class MyNode implements Comparable<MyNode> {
    private int index;
    private int value;

    public MyNode(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return this.index;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int compareTo(MyNode myNode) {
        if (this.value > myNode.getValue()) {
            return 1;
        } else if (this.value < myNode.getValue()) {
            return -1;
        }
        return 0;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        MyNode[] arr = new MyNode[n];
        int[] preIdx = new int[n];
        int[] nxtIdx = new int[n];

        for (int i = 0; i < n; i++) {
            int curNum = Integer.parseInt(br.readLine());
            arr[i] = new MyNode(i, curNum);
            preIdx[i] = i;
        }

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            nxtIdx[i] = arr[i].getIndex();
        }

        // 답 구하기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int tmp = nxtIdx[i] - preIdx[i];
            if (tmp > answer) {
                answer = tmp;
            }
        }
        // 정렬 이후 마지막 스왑이 안되는 경우
        answer++;

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}