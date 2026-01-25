import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        
        int[] arr = new int[elements.length * 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = elements[i % elements.length];
        }
        
        int[] s = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            s[i] = s[i - 1] + arr[i - 1];
        }
        
        HashSet<Integer> hs = new HashSet<>();
        int idx = 1;
        while (idx <= elements.length) {
            for (int i = idx; i < elements.length + idx; i++) {
                int sum = s[i] - s[i - idx];
                hs.add(sum);
            }
            idx++;
        }
        
        answer = hs.size();
        
        return answer;
    }
}