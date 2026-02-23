import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.LinkedList;

class Solution {
    private static HashMap<String, PriorityQueue<String>> map;
    private static LinkedList<String> ans;
    
    public String[] solution(String[][] tickets) {
        map = new HashMap<>();
        ans = new LinkedList<>();
        
        for (int i = 0; i < tickets.length; i++) {
            String start = tickets[i][0];
            String end = tickets[i][1];
            
            if (!map.containsKey(start)) {
                map.put(start, new PriorityQueue<>());
            }
            map.get(start).add(end);
        }
        
        dfs("ICN");
        
        String[] answer = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
    
    private static void dfs(String cur) {
        PriorityQueue<String> pq = null;
        if (map.containsKey(cur)) {
            pq = map.get(cur);
        }
        while (pq != null && !pq.isEmpty()) {
            dfs(pq.remove());
        }
        ans.addFirst(cur);
    }
}