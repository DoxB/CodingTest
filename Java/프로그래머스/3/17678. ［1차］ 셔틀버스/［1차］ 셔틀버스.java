import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Stack;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        // 셔틀은 9시부터 출발
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < timetable.length; i++) {
            pq.add(calMin(timetable[i]));
        }
        
        int[] busTable = new int[n];
        HashMap<String, Stack<Integer>> bus = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int avalTime = (9 * 60) + (i * t);
            busTable[i] = avalTime;
            bus.put(Integer.toString(avalTime), new Stack<>());
        }
        
        while (!pq.isEmpty()) {
            int curTime = pq.remove();
            for (int i = 0; i < n; i++) {
                if (busTable[i] >= curTime && bus.get(Integer.toString(busTable[i])).size() < m) {
                    bus.get(Integer.toString(busTable[i])).push(curTime);
                    break;
                }
            }
        }
        
        // 정답 찾기
        int idxTime = busTable[n - 1];
        
        if (bus.get(Integer.toString(idxTime)).size() < m) {
            answer = calTime(idxTime);
        } else {
            int lastTime = bus.get(Integer.toString(idxTime)).peek();
            answer = calTime(lastTime - 1);
        }
        
        return answer;
    }
    
    private static String calTime(int time) {
        String h = String.format("%02d", time / 60);
        String m = String.format("%02d", time % 60);
        
        return h + ":" + m;
    }
    
    private static int calMin(String time) {
        String[] ts = time.split(":");
        return Integer.parseInt(ts[0]) * 60 + Integer.parseInt(ts[1]);
    }
}