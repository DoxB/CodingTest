import java.util.PriorityQueue;

class Solution {
    public int solution(String[][] book_time) {
        int n = book_time.length;
        int[] timeTable = new int[n];
        PriorityQueue<BookTime> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String[] s = book_time[i][0].split(":");
            String[] e = book_time[i][1].split(":");
            
            int start = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
            int end = Integer.parseInt(e[0]) * 60 + Integer.parseInt(e[1]);
            
            pq.add(new BookTime(start, end));
        }
        
        int answer = 0;
        while (!pq.isEmpty()) {
            BookTime bt = pq.remove();
            for (int i = 0; i < n; i++) {
                if (timeTable[i] == 0) {
                    timeTable[i] = bt.end;
                    answer++;
                    break;
                }
                else if (timeTable[i] + 10 <= bt.start) {
                    timeTable[i] = bt.end;
                    break;
                }
            }
        }
        
        return answer;
    }
}

class BookTime implements Comparable<BookTime>{
    int start;
    int end;
    
    public BookTime(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int compareTo(BookTime b) {
        if (this.start - b.start > 0) {
            return 1;
        } else if (this.start - b.start == 0) {
            return 0;
        }
        return -1;
    }
}