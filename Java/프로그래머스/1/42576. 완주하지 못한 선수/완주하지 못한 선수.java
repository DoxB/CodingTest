import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : participant) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.replace(s, map.get(s) + 1);
            }
        }
        
        for (String s : completion) {
            map.replace(s, map.get(s) - 1);
        }
        
        for (String s : map.keySet()) {
            if (map.get(s) == 1) {
                answer = s;
                break;
            }
        }
        
        return answer;
    }
}