import java.util.ArrayList;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();
        
        for (int i = 0; i < str1.length() - 1; i++) {
            String tmp = str1.substring(i, i + 2);
            if (tmp.matches("[a-z]{2}")){
                arr1.add(tmp); 
            }
        }
        
        for (int i = 0; i < str2.length() - 1; i++) {
            String tmp = str2.substring(i, i + 2);
            if (tmp.matches("[a-z]{2}")){
                arr2.add(tmp);
            }
        }
        
        int n1 = arr1.size();
        int n2 = arr2.size();
        for (String s : arr1) {
            arr2.remove(s);
        }
        double crossNum = n2 - arr2.size();
        double unionNum = n1 + n2 - crossNum;
        
        if (n1 == 0 && n2 == 0) {
            answer = 65536;
        } else {
            answer = (int)((crossNum / unionNum) * 65536);
        }
        
        return answer;
    }
}