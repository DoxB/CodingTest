import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> member = new HashMap<>();
        int n = record.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            String[] r = record[i].split(" ");
            if (r[0].equals("Enter")) {
                member.put(r[1], r[2]);
            } else if (r[0].equals("Change")) {
                member.put(r[1], r[2]);
                continue;
            }
            cnt++;
        }
        
        String[] answer = new String[cnt];
        
        int curIdx = 0;
        for (int i = 0; i < n; i++) {
            String[] r = record[i].split(" ");
            if (r[0].equals("Enter")) {
                answer[curIdx++] = member.get(r[1]) + "님이 들어왔습니다.";
            } else if (r[0].equals("Leave")) {
                answer[curIdx++] = member.get(r[1]) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}