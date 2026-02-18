import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        PriorityQueue<Car> pq = new PriorityQueue<>();
        for (int i = 0; i < routes.length; i++) {
            int inT = routes[i][0];
            int outT = routes[i][1];
            pq.add(new Car(inT, outT));
        }
        
        int curIdx = Integer.MIN_VALUE;
        while (!pq.isEmpty()) {
            Car c = pq.remove();
            if (c.inT <= curIdx && curIdx <= c.outT) {
                continue;
            } else {
                answer++;
                curIdx = c.outT;
            }
        }
        
        return answer;
    }
}

class Car implements Comparable<Car>{
    int inT;
    int outT;
    
    public Car(int inT, int outT) {
        this.inT = inT;
        this.outT = outT;
    }
    
    @Override
    public int compareTo(Car c) {
        if (this.outT - c.outT > 0) {
            return 1;
        }
        return -1;
    }
}