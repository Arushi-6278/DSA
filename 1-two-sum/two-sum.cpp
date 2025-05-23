class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
      unordered_map<int, int> m;
      
      int n = nums.size();
      for(int i=0;i<n;i++){
        int val = target - nums[i];
        if(m.find(val) != m.end()){
            return {m[val], i};
        }
        m[nums[i]] = i;
      }


     return {};

    }
};