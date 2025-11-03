import java.io.*;
import java.util.PriorityQueue;

class MyNode implements Comparable<MyNode>{
    private int num;

    public MyNode(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }

    @Override
    public int compareTo(MyNode myNode) {
        if (Math.abs(this.num) > Math.abs(myNode.getNum())) {
            return 1;
        } else if (Math.abs(this.num) < Math.abs(myNode.getNum())) {
            return -1;
        } else {
            if (this.num > myNode.getNum()) {
                return 1;
            } else if (this.num < myNode.getNum()) {
                return -1;
            }
        }
        return 0;
    }
}


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<MyNode> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int curNum = Integer.parseInt(br.readLine());
            if (curNum == 0) {
                if (pq.isEmpty()) {
                    bw.write(Integer.toString(0));
                    bw.newLine();
                } else {
                    bw.write(Integer.toString(pq.remove().getNum()));
                    bw.newLine();
                }
            } else {
                MyNode myNode = new MyNode(curNum);
                pq.add(myNode);
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}