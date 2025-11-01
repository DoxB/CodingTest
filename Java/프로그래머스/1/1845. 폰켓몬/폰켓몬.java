import java.util.ArrayList;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int len = nums.length;
        int pickNum = len / 2;
        
        ArrayList<Integer> al = new ArrayList<>();
        for (int n : nums) {
            if (!al.contains(n)) {
                al.add(n);
            }
        }
        
        if (al.size() >= pickNum) {
            answer = pickNum;
        } else {
            answer = al.size();
        }
        
        return answer;
    }
}