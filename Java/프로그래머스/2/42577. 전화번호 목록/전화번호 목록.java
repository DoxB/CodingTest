import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) { 
            if (phone_book[i].length() > phone_book[i + 1].length()) {
                continue;
            } else {
                if (phone_book[i].equals(phone_book[i + 1].substring(0, phone_book[i].length()))) {
                    answer = false;
                    break;
                }
            }
        }
        
        return answer;
    }
}