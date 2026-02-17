import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public String solution(String m, String[] musicinfos) {
        ArrayList<MyMusic> arr = new ArrayList<>();
        for (int i = 0; i < musicinfos.length; i++) {
            String[] musicinfo = musicinfos[i].split(",");
            String s = musicinfo[0];
            String e = musicinfo[1];
            String title = musicinfo[2];
            String lyics = musicinfo[3];
            
            String[] st = s.split(":");
            String[] et = e.split(":");
            int stime = Integer.parseInt(st[0]) * 60 + Integer.parseInt(st[1]);
            int etime = Integer.parseInt(et[0]) * 60 + Integer.parseInt(et[1]);
            int time = etime - stime;
            Queue<String> q = new LinkedList<>();
            for (int j = 0; j < lyics.length(); j++) {
                String h;
                if (j < lyics.length() - 1 && lyics.substring(j + 1, j + 2).equals("#")) {
                    h = lyics.substring(j, j + 2);
                    j++;
                } else {
                    h = lyics.substring(j, j + 1);
                }
                q.add(h);
            }
            
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            while (cnt < time) {
                String cur = q.remove();
                sb.append(cur);
                q.add(cur);
                cnt++;
            }
            
            String song = sb.toString();
            arr.add(new MyMusic(time, title, song));
        }
        
        Collections.sort(arr, Collections.reverseOrder());
        
        String answer = "(None)";
        for (MyMusic tmp : arr) {
            String vs = tmp.song.replace(m + "#", "");
            if (vs.contains(m)) {
                answer = tmp.title;
                break;
            }    
        }
        
        return answer;
    }
}

class MyMusic implements Comparable<MyMusic> {
    int time;
    String title;
    String song;
    
    public MyMusic (int time, String title, String song) {
        this.time = time;
        this.title = title;
        this.song = song;
    }
    
    @Override
    public int compareTo(MyMusic m) {
        if (this.time - m.time > 0) return 1;
        else if (this.time - m.time == 0) return 0;
        return -1;
    }
}