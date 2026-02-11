import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public String[] solution(String[] files) {
        ArrayList<MyFile> arr = new ArrayList<>();
        int n = files.length;
        for (int i = 0; i < n; i++) {
            char[] curFile = files[i].toCharArray();
            boolean findNum = false;
            int start = -1;
            int end = -1;
            for (int j = 0; j < curFile.length; j++) {
                if (!findNum && curFile[j] >= '0' && curFile[j] <= '9') {
                    findNum = true;
                    start = j;
                } else if (findNum && (curFile[j] < '0' || curFile[j] > '9')) {
                    end = j;
                    break;
                }
            }
            if (end == -1) {
                end = curFile.length;
            }
            
            String head = files[i].substring(0, start);
            String number = files[i].substring(start, end);
            String tail = "";
            if (end != files[i].length()) {
                tail = files[i].substring(end, files[i].length());
            }
            
            arr.add(new MyFile(head, number, tail));
        }
        
        Collections.sort(arr);
        
        String[] answer = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            MyFile m = arr.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(m.head);
            sb.append(m.number);
            sb.append(m.tail);
            answer[i] = sb.toString();
        }
        return answer;
    }
}

class MyFile implements Comparable<MyFile>{
    String head;
    String number;
    int intNum;
    String tail;
    
    MyFile(String head, String number, String tail) {
        this.head = head;
        this.number = number;
        this.tail = tail;
        this.intNum = Integer.parseInt(number);
    }
    
    @Override
    public int compareTo(MyFile m) {
        if (head.compareToIgnoreCase(m.head) > 0) {
            return 1;
        } else if (head.compareToIgnoreCase(m.head) == 0) {
            if (intNum - m.intNum > 0) {
                return 1;
            } else if (intNum == m.intNum) {
               return 0;
            }
        }
        return -1;
    }
}