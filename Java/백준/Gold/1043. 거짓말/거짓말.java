import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    private static int[] parent;
    private static int[] knownPeople;
    private static ArrayList<Integer>[] party;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        if (k == 0) {
            System.out.println(m);
            return;
        } else {
            knownPeople = new int[k];
            for (int i = 0; i < k; i++) {
                knownPeople[i] = Integer.parseInt(st.nextToken());
            }
        }
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int idxKnown = knownPeople[0];
        for (int i = 1; i < k; i++) {
            union(idxKnown, knownPeople[i]);
        }

        party = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            party[i] = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine());
            int partyPeople = Integer.parseInt(st.nextToken());
            if (partyPeople == 1) {
                party[i].add(Integer.parseInt(st.nextToken()));
            } else {
                int firstPeople = Integer.parseInt(st.nextToken());
                party[i].add(firstPeople);
                for (int j = 1; j < partyPeople; j++) {
                    int another = Integer.parseInt(st.nextToken());
                    party[i].add(another);
                    union(firstPeople, another);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            boolean flag = true;
            for (int peopleNum : party[i]) {
                if (find(idxKnown) == find(peopleNum)) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) answer++;
        }

        System.out.println(answer);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}