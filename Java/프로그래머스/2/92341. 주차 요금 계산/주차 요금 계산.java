import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, String> state = new HashMap<>();
        HashMap<String, Integer> result = new HashMap<>();
        
        for (int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");
            String[] t = record[0].split(":");
            String num = record[1];
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            
            if (record[2].equals("IN")) {
                int inputTime = h * 60 + m;
                map.put(num, inputTime);
                state.put(num, "IN");
            } else {
                int inputTime = map.get(num);
                int outTime = h * 60 + m;
                int duration = outTime - inputTime;
                result.put(num, result.getOrDefault(num, 0) + duration);
                state.put(num, "OUT");
            }
        }
        
        for (String k : state.keySet()) {
            if (state.get(k).equals("IN")) {
                int inputTime = map.get(k);
                int outTime = 23 * 60 + 59;
                int duration = outTime - inputTime;
                result.put(k, result.getOrDefault(k, 0) + duration);
                state.put(k, "OUT");
            }
        }
        
        
        ArrayList<String> cars = new ArrayList<>();
        for (String k : result.keySet()) {
            cars.add(k);
        }
        Collections.sort(cars);
        
        int[] answer = new int[result.size()];
        int idx = 0;
        for (String k : cars) {
            int duration = Math.max(result.get(k) - fees[0], 0);
            int over = 0;
            if (duration % fees[2] != 0) {
                over = 1;
            }
            int fee = fees[1] + ((duration / fees[2]) + over) * fees[3];
            answer[idx++] = fee;
        }
        
        return answer;
    }
}