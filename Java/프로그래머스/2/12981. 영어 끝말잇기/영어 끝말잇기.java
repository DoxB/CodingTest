import java.util.HashMap;

class Solution {
    public int[] solution(int n, String[] words) {
        boolean flag = true;
        HashMap<String, Integer> h = new HashMap<>();
        int curRound = 0;
        int idxNum = 0;
        
        h.put(words[0], 1);
        for (int i = 1; i < words.length; i++) {
            idxNum = (i % n) + 1;
            curRound = (i / n) + 1;
            char[] prev = words[i - 1].toCharArray();
            char[] cur = words[i].toCharArray();
            
            if (prev[prev.length - 1] == cur[0] && !h.containsKey(words[i])) {
                h.put(words[i], 1);
            } else {
                flag = false;
                break;
            }
        }
        
        if (flag) {
            int[] answer = {0, 0};
            return answer;
        } else {
            int[] answer = {idxNum, curRound};
            return answer;
        }
    }
}