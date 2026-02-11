import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<Integer> dic = new ArrayList<>();
        
        for (int i = 0; i < 26; i++) {
            char tmp = (char)('A' + i);
            map.put(String.valueOf(tmp), i + 1);
        }
        
        int n = msg.length();
        int start = 0;
        int curIdx = 27;
        
        while (start < n) {
            int cnt = 1;
            while (start + cnt <= n && map.containsKey(msg.substring(start, start + cnt))) {
                cnt++;
            }
            
            String w = msg.substring(start, start + cnt - 1);
            dic.add(map.get(w));
            
            if (start + cnt < n) {
                String newWord = msg.substring(start, start + cnt);
                map.put(newWord, curIdx++);
            }
            
            start = start + cnt - 1;
        }
        
        
        int[] answer = new int[dic.size()];
        for (int i = 0; i < dic.size(); i++) {
            answer[i] = dic.get(i);
        }
        
        return answer;
    }
}