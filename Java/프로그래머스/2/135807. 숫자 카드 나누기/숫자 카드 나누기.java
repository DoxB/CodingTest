class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int idxA = arrayA[0];
        int idxB = arrayB[0];
        int idx = Math.max(idxA, idxB);
        
        while (idx > 1) {
            if (isOk(arrayA, idx)) {
                if (isNot(arrayB, idx)) {
                    return idx;
                }
            } else if (isOk(arrayB, idx)) {
                if (isNot(arrayA, idx)) {
                    return idx;
                }
            }
            idx--;
        }
        return answer;
    }
    
    private static boolean isOk(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % num != 0) {
                return false;
            }   
        }
        return true;    
    }
    
    private static boolean isNot(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % num == 0) {
                return false;
            }   
        }
        return true;
    }
}