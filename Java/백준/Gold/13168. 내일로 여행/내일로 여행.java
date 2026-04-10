import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dis100 = {"Mugunghwa", "ITX-Saemaeul", "ITX-Cheongchun"};
        String[] dis50 = {"S-Train", "V-Train"};
        HashSet<String> discount100 = new HashSet<>(Arrays.asList(dis100));
        HashSet<String> discount50 = new HashSet<>(Arrays.asList(dis50));

        HashMap<String, Integer> city = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            city.put(st.nextToken(), i);
        }

        int planNum = Integer.parseInt(br.readLine());
        String[] plan = new String[planNum];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < planNum; i++) {
            plan[i] = st.nextToken();
        }

        double[][] cost = new double[n][n];
        double[][] discost = new double[n][n];
        double INF = 10000000;
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
            Arrays.fill(discost[i], INF);
            cost[i][i] = 0;
            discost[i][i] = 0;
        }
        int pathNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < pathNum; i++) {
            st = new StringTokenizer(br.readLine());
            String how = st.nextToken();
            String start = st.nextToken();
            String end = st.nextToken();
            double price = Double.parseDouble(st.nextToken());

            int sNum = city.get(start);
            int eNum = city.get(end);
            if (discount100.contains(how)) {
                discost[sNum][eNum] = Math.min(discost[sNum][eNum], 0);
                discost[eNum][sNum] = Math.min(discost[eNum][sNum], 0);
            } else if (discount50.contains(how)) {
                discost[sNum][eNum] = Math.min(discost[sNum][eNum], price / 2);
                discost[eNum][sNum] = Math.min(discost[eNum][sNum], price / 2);
            } else {
                discost[sNum][eNum] = Math.min(discost[sNum][eNum], price);
                discost[eNum][sNum] = Math.min(discost[eNum][sNum], price);
            }
            cost[sNum][eNum] = Math.min(cost[sNum][eNum], price);
            cost[eNum][sNum] = Math.min(cost[eNum][sNum], price);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                    discost[i][j] = Math.min(discost[i][j], discost[i][k] + discost[k][j]);
                }
            }
        }

        // 답찾기
        double railro = (double) r;
        double general = 0;
        for (int i = 0; i < planNum - 1; i++) {
            int sNum = city.get(plan[i]);
            int eNum = city.get(plan[i + 1]);
            general += cost[sNum][eNum];
            railro += discost[sNum][eNum];
        }

        if (general > railro) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}