import java.util.ArrayList;
    
class Solution {
    public double[] solution(int k, int[][] ranges) {
        
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(k);
        while (k != 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
            arr.add(k);
        }
        
        
        int n = ranges.length;
        double[] answer = new double[n];
        for (int i = 0; i < n; i++) {
            int start = ranges[i][0];
            int end = arr.size() + ranges[i][1] - 1;
            if (start > end) {
                answer[i] = -1.0;
            } else if (start == end) {
                answer[i] = 0;
            } else {
                for (int j = start; j < end; j++) {
                    int minH = Math.min(arr.get(j), arr.get(j + 1));
                    int maxH = Math.max(arr.get(j), arr.get(j + 1));
                    int width = 1;

                    double s1 = (double)width * (double)minH;
                    double s2 = ((double)maxH - (double)minH) * (double)width * 0.5;
                    answer[i] += s1 + s2;   
                }
            }
        }
        return answer;
    }
}