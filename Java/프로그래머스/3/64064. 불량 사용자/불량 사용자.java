import java.util.HashSet;

class Solution {
    private static HashSet<String>[] isHave;
    private static HashSet<String> select;
    private static HashSet<HashSet<String>> result; // 중복 조합 가르기
    private static int n;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        n = banned_id.length;
        isHave = new HashSet[n];
        select = new HashSet<>();
        result = new HashSet<>();
        for (int i = 0; i < n; i++) {
            isHave[i] = new HashSet<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < user_id.length; j++) {
                if (isOk(user_id[j], banned_id[i])) {
                    isHave[i].add(user_id[j]);
                }
            }
        }
        
        dfs(0);
        
        answer = result.size();
        
        return answer;
    }
    
    private static void dfs(int depth) {
        if (depth == n) {
            result.add(new HashSet<>(select));
            return;
        }
        for (String user : isHave[depth]) {
            if (!select.contains(user)) {
                select.add(user);
                dfs(depth + 1);
                select.remove(user);
            }
        }
    }
    
    private static boolean isOk(String user, String ban) {
        if (user.length() != ban.length()) {
            return false;
        } else {
            int n = user.length();
            char[] uC = user.toCharArray();
            char[] bC = ban.toCharArray();
            
            for (int i = 0; i < n; i++) {
                if (bC[i] == '*') {
                    continue;
                } else {
                    if (uC[i] != bC[i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}