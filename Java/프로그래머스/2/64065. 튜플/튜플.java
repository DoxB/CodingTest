import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public int[] solution(String s) {
        s = s.replace("{{", "");
        s = s.replace("}}", "");
        String[] arr = s.split("\\},\\{");
        
        int[] answer = new int[arr.length];
        HashSet<Integer> hs = new HashSet<>();
        
        // 문자열 길이 기준 오름차순 정렬
        Arrays.sort(arr, (String s1, String s2) -> s1.length() - s2.length());
        for (int i = 0; i < arr.length; i++) {
            int curLength = i;
            String[] tmp = arr[i].split(",");
            for (int j = 0; j < tmp.length; j++) {
                int num = Integer.parseInt(tmp[j]);
                if (curLength > 0 && hs.contains(num)) {
                    curLength--;
                    continue;
                }
                hs.add(num);
                answer[i] = num;
            }
        }
        
        return answer;
    }
}