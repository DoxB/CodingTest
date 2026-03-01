import java.util.HashMap;

class Solution {
    private static int[] parent;    
    private static int[] sell;
    private static HashMap<String, Integer> user;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // 명단 번호로 관리
        int n = enroll.length;
        user = new HashMap<>();
        user.put("center", 0);
        for (int i = 0; i < n; i++) {
            user.put(enroll[i], i + 1);
        }
        
        // 바로 앞 부모 관리
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parent[user.get(enroll[i])] = 0;
            } else {
                parent[user.get(enroll[i])] = user.get(referral[i]);
            }
        }
        
        // 이익금 관리
        sell = new int[n + 1];
        for (int i = 0; i < seller.length; i++) {
            int idx = user.get(seller[i]);
            int value = amount[i] * 100;
            update(idx, value);
        }
        
        // 정리
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = sell[i + 1];
        }
        
        return answer;
    }
    
    private static void update(int idx, int value) {
        if (parent[idx] == idx) {
            sell[idx] = value;
            return;
        }
        int nxtValue = value / 10;
        sell[idx] += value - nxtValue;
        update(parent[idx], nxtValue);
    }
}