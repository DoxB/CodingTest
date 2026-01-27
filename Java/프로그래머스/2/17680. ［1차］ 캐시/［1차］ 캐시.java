import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Queue<String> cache = new LinkedList<>();
        
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        for (int i = 0; i < cities.length; i++) {
            String curLower = tolowerAlpha(cities[i]);
            if (cache.contains(curLower)) {
                answer += 1;
                int cur = cache.size();
                for (int j = 0; j < cur; j++) {
                    String lastString = cache.remove();
                    if (lastString.equals(curLower)) {
                        continue;
                    }
                    cache.add(lastString);
                }
                cache.add(curLower);
            } else if (cache.size() < cacheSize) {
                cache.add(curLower);
                answer += 5;
            } else {
                cache.remove();
                cache.add(curLower);
                answer += 5;
            }
        }
        
        return answer;
    }
    
    private static String tolowerAlpha(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if ('A' <= tmp && tmp <= 'Z') {
                tmp = (char) (tmp + 32);
            }
            sb.append(tmp);
        }
        return sb.toString();
    }
}