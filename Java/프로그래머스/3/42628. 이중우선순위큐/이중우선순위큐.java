import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        int n = operations.length;
        for (int i = 0; i < n; i++) {
            String[] tmp = operations[i].split(" ");
            if (tmp[0].equals("I")) {
                minQ.add(Integer.parseInt(tmp[1]));
                maxQ.add(Integer.parseInt(tmp[1]));
            } else {
                if (!maxQ.isEmpty() && tmp[1].equals("1")) {
                    int v = maxQ.remove();
                    minQ.remove(v);
                } else if (!minQ.isEmpty() && tmp[1].equals("-1")){
                    int v = minQ.remove();
                    maxQ.remove(v);
                }
            }
        }
        
        int[] answer = new int[2];
        
        if (!minQ.isEmpty()) {
            answer[0] = maxQ.remove();
            answer[1] = minQ.remove();
        }
        
        return answer;
    }
}