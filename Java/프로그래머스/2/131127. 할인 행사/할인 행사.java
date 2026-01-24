import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        HashMap<String, Integer> h = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            h.put(discount[i], h.getOrDefault(discount[i], 0) + 1);
        }
        
        if (isOK(want, number, h)) {
            answer++;
        }
        
        int first = 0;
        int end = 10;
        while(end < discount.length) {
            h.put(discount[first], h.getOrDefault(discount[first], 0) - 1);
            h.put(discount[end], h.getOrDefault(discount[end], 0) + 1);
            
            if (isOK(want, number, h)) {
                answer++;
            }
            
            first++;
            end++;
        }
        
        return answer;
    }
    
    private static boolean isOK(String[] want, int[] number, HashMap<String, Integer> h) {
        for (int i = 0; i < number.length; i++) {
            if (h.getOrDefault(want[i], 0) < number[i]) {
                return false;
            }
        }
        return true;
    }
}