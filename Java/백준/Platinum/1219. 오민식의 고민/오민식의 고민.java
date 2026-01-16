import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[m];
        long[] income = new long[n];
        for (int i = 0; i < n; i++) {
            income[i] = Long.MIN_VALUE;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(s, e, v);
        }

        long[] cityIncome = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cityIncome[i] = Long.parseLong(st.nextToken());
        }

        income[startCity] = cityIncome[startCity];
        for (int i = 0; i < n + 100; i++) {
            for (int j = 0; j < m; j++) {
                Edge edge = edges[j];
                if (income[edge.start] == Long.MIN_VALUE) continue;
                else if (income[edge.start] == Long.MAX_VALUE) {
                    income[edge.end] = Long.MAX_VALUE;
                } else if (income[edge.end] < income[edge.start] + cityIncome[edge.end] - edge.value) {
                    income[edge.end] = income[edge.start] + cityIncome[edge.end] - edge.value;
                    if (i >= n - 1) {
                        income[edge.end] = Long.MAX_VALUE;
                    }
                }
            }
        }

        if (income[endCity] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else if (income[endCity] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else {
            System.out.println(income[endCity]);
        }
    }
}

class Edge {
    int start;
    int end;
    int value;

    public Edge(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
}