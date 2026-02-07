import java.util.ArrayList;
import java.util.Collections;

class Solution {
    private static boolean[] visited;
    private static int cnt;
    
    public int solution(int[] cards) {
        ArrayList<Integer> arr = new ArrayList<>();
        int n = cards.length;
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, cards);
                arr.add(cnt);
                cnt = 0;
            }
        }
        
        int answer = 0;
        if (arr.size() > 1) {
            Collections.sort(arr, Collections.reverseOrder());
            answer = arr.get(0) * arr.get(1);
        }
        
        return answer;
    }
    
    private static void dfs(int start, int[] cards) {
        cnt++;
        visited[start] = true;
        int nxt = cards[start] - 1;
        if (!visited[nxt]) {
            dfs(nxt, cards);
        }
    }
}