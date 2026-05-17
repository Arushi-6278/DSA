class Solution {
    public boolean canReach(int[] arr, int start) {
       
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        
       
        if (arr[start] == 0) {
            return true;
        }
        
       
        int jump = arr[start];
        arr[start] = -arr[start];
        
       
        boolean canReachRight = canReach(arr, start + jump);
        boolean canReachLeft = canReach(arr, start - jump);
        
       
        return canReachRight || canReachLeft;
    }
}