class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n];
        
       
        int[] frequency = new int[n + 1];
        int commonCount = 0;
        
        for (int i = 0; i < n; i++) {
       
            frequency[A[i]]++;
            if (frequency[A[i]] == 2) {
                commonCount++;
            }
            
           
            frequency[B[i]]++;
            if (frequency[B[i]] == 2) {
                commonCount++;
            }
            
        
            C[i] = commonCount;
        }
        
        return C;
    }
}