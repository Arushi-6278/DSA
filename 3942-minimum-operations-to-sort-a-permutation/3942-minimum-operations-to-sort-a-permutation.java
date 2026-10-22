class Solution {
public int minOperations(int[] nums) {
int n = nums.length;
int zeroIdx = -1;
for (int i = 0; i < n; i++) {
if (nums[i] == 0) {
zeroIdx = i;
break;
}
}
boolean incRight = true;
for (int i = 0; i < n; i++) {
if (nums[(zeroIdx + i) % n] != i) {
incRight = false;
break;
}
}
boolean incLeft = true;
for (int i = 0; i < n; i++) {
if (nums[(zeroIdx - i + n) % n] != i) {
incLeft = false;
break;
}
}
int ans = Integer.MAX_VALUE;
if (incRight) {
ans = Math.min(ans, zeroIdx);
ans = Math.min(ans, n - zeroIdx + 2);
}
if (incLeft) {
ans = Math.min(ans, zeroIdx + 2);
ans = Math.min(ans, n - zeroIdx);
}
return ans == Integer.MAX_VALUE ? -1 : ans;
}
}