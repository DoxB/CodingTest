import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> h = new HashMap<>();
        for (int i = 0; i < tangerine.length; i++) {
            if (!h.containsKey(tangerine[i])) {
                h.put(tangerine[i], 1);
            } else {
                h.replace(tangerine[i], h.get(tangerine[i]) + 1);
            }
        }
        
        ArrayList<Integer> arr = new ArrayList<>(h.values());
        arr.sort(Collections.reverseOrder());
        
        int cnt = 0;
        while (k > 0) {
            k -= arr.get(cnt);
            cnt++;
        } 
        
        answer = cnt;
        return answer;
    }
}