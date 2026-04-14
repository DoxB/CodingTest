import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] descMap = new ArrayList[k + 1];
        ArrayList<Integer>[] ascMap = new ArrayList[k + 1];
        for (int i = 0; i <= k; i++) {
            descMap[i] = new ArrayList<>();
            ascMap[i] = new ArrayList<>();
        }
        int[] descDegree = new int[k + 1];
        int[] ascDegree = new int[k + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            String symbol = st.nextToken();
            if (symbol.equals("<")) {
                descMap[i + 1].add(i);
                ascMap[i].add(i + 1);
                descDegree[i]++;
                ascDegree[i + 1]++;
            } else {
                descMap[i].add(i + 1);
                ascMap[i + 1].add(i);
                descDegree[i + 1]++;
                ascDegree[i]++;
            }
        }

        int maxNum = 9;
        int minNum = 0;

        PriorityQueue<Integer> descQ = new PriorityQueue<>();
        PriorityQueue<Integer> ascQ = new PriorityQueue<>();

        for (int i = 0; i <= k; i++) {
            if (descDegree[i] == 0) {
                descQ.add(i);
            }
            if (ascDegree[i] == 0) {
                ascQ.add(i);
            }
        }

        int[] maxAns = new int[k + 1];
        int[] minAns = new int[k + 1];

        while (!descQ.isEmpty()) {
            int cur = descQ.remove();
            maxAns[cur] = maxNum--;
            for (int nxt : descMap[cur]) {
                descDegree[nxt]--;
                if (descDegree[nxt] == 0) {
                    descQ.add(nxt);
                }
            }
        }

        while (!ascQ.isEmpty()) {
            int cur = ascQ.remove();
            minAns[cur] = minNum++;
            for (int nxt : ascMap[cur]) {
                ascDegree[nxt]--;
                if (ascDegree[nxt] == 0) {
                    ascQ.add(nxt);
                }
            }
        }

        StringBuilder maxSb = new StringBuilder();
        StringBuilder minSb = new StringBuilder();
        for (int i = 0; i <= k; i++) {
            maxSb.append(Integer.toString(maxAns[i]));
            minSb.append(Integer.toString(minAns[i]));
        }

        System.out.println(maxSb.toString());
        System.out.println(minSb.toString());
    }
}