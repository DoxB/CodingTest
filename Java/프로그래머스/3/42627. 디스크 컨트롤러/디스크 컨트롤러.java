import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int curTime = 0;
        PriorityQueue<MyTask> mt = new PriorityQueue<>();
        PriorityQueue<MyInner> mi = new PriorityQueue<>();
        
        for (int i = 0; i < jobs.length; i++) {
            mi.add(new MyInner(jobs[i][0], jobs[i][1]));
        }
        
        while (!mi.isEmpty()) {
            while (!mi.isEmpty() && mi.peek().inTime <= curTime) {
                MyInner myInner = mi.remove();
                mt.add(new MyTask(myInner.inTime, myInner.duration));
            }
            if (mt.size() == 0) {
                curTime = mi.peek().inTime;
                continue;
            } else {
                MyTask myTask = mt.remove();
                curTime += myTask.duration;
                answer += curTime - myTask.inTime;
            }
        }
        
        while (!mt.isEmpty()) {
            MyTask myTask = mt.remove();
            curTime += myTask.duration;
            answer += curTime - myTask.inTime;
        }
        
        return answer / jobs.length;
    }
}

class MyInner implements Comparable<MyInner> {
    int inTime;
    int duration;
    
    public MyInner(int inTime, int duration) {
        this.inTime = inTime;
        this.duration = duration;
    }
    
    @Override
    public int compareTo(MyInner m) {
        if (this.inTime - m.inTime > 0) {
            return 1;
        }
        return -1;
    }
}

class MyTask implements Comparable<MyTask> {
    int inTime;
    int duration;
    
    public MyTask(int inTime, int duration) {
        this.inTime = inTime;
        this.duration = duration;
    }
    
    @Override
    public int compareTo(MyTask m) {
        if (this.duration - m.duration > 0) {
            return 1;
        }
        return -1;
    }
}