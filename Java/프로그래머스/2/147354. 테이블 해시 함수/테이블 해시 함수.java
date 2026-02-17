import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        ArrayList<MyData> arr = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            int[] isData = data[i];
            int idxCol = data[i][col - 1];
            arr.add(new MyData(isData, idxCol));
        }
        
        Collections.sort(arr);
        
        ArrayList<Integer> totalArr = new ArrayList<>();
        int start = row_begin - 1;
        int end = row_end - 1;
        for (int i = start; i <= end; i++) {
            MyData tmp = arr.get(i);
            int total = 0;
            for (int j = 0; j < tmp.isData.length; j++) {
                total += tmp.isData[j] % (i + 1);
            }
            totalArr.add(total);
        }
        
        int answer = totalArr.get(0);
        for (int i = 1; i < totalArr.size(); i++) {
            int vs = totalArr.get(i);
            answer = answer ^ vs;
        }
        
        return answer;
    }
}

class MyData implements Comparable<MyData> {
    int[] isData;
    int idxCol;
    
    public MyData(int[] isData, int idxCol) {
        this.isData = isData;
        this.idxCol = idxCol;
    }
    
    @Override
    public int compareTo(MyData d) {
        if (this.idxCol - d.idxCol > 0) {
            return 1;
        } else if (this.idxCol - d.idxCol == 0) {
            if (this.isData[0] - d.isData[0] > 0) {
                return -1;
            } else if (this.isData[0] - d.isData[0] == 0) {
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }
}