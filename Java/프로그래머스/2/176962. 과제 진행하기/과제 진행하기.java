import java.util.Stack;
import java.util.PriorityQueue;

class Solution {
    public String[] solution(String[][] plans) {
        Stack<MyQuest> sk = new Stack<>();
        PriorityQueue<MyQuest> pq = new PriorityQueue<>();
        int n = plans.length;
        String[] answer = new String[n];
        int curIdx = 0;
        for (int i = 0; i < n; i++) {
            pq.add(new MyQuest(plans[i][0], plans[i][1], plans[i][2]));
        }
        
        int t = 0;
        while (!pq.isEmpty()) {
            MyQuest curQ = pq.remove();
            if (!sk.isEmpty()) {
                MyQuest prevQ = sk.peek();
                t = curQ.time - prevQ.time;
            }
            while (!sk.isEmpty()) {
                MyQuest popQ = sk.pop();
                if (t >= popQ.spend) {
                    answer[curIdx] = popQ.subject;
                    curIdx++;
                    t -= popQ.spend;
                } else {
                    popQ.spend -= t;
                    sk.push(popQ);
                    break;
                }
            }
            sk.push(curQ);
        }
        
        while (!sk.isEmpty()) {
            MyQuest lastQ = sk.pop();
            answer[curIdx] = lastQ.subject;
            curIdx++;
        }
                
        return answer;
    }
}

class MyQuest implements Comparable<MyQuest>{
    String subject;
    int time;
    int spend;
    
    public MyQuest(String subject, String time, String spend) {
        this.subject = subject;
        String[] t = time.split(":");
        this.time = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        this.spend = Integer.parseInt(spend);
    }
    
    @Override
    public int compareTo(MyQuest m) {
        if (this.time > m.time) {
            return 1;
        } else if (this.time == m.time) {
            return 0;
        }
        return -1;
    }
}