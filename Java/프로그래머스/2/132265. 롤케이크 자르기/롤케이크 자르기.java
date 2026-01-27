import java.util.HashMap;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        
        for (int i = 0; i < topping.length; i++) {
            right.put(topping[i], right.getOrDefault(topping[i], 0) + 1);
        }
        
        int curRight = right.size();
        for (int i = 0; i < topping.length; i++) {
            right.put(topping[i], right.get(topping[i]) -1);
            if (right.get(topping[i]) == 0) {
                curRight--;
            }
            left.put(topping[i], right.getOrDefault(topping[i], 0) + 1);
            
            if (left.size() == curRight) {
                answer++;
            }
        }
        
        return answer;
    }
}