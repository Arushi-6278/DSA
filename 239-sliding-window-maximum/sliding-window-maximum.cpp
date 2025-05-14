class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
         vector<int> arr;
      deque<int> q;
      int n = nums.size();
      

      for(int i=0;i<k;i++){
        while(!q.empty() && nums[q.back()] <= nums[i]){
            q.pop_back();
        }
        q.push_back(i);
      }
      
      for(int i=k;i<n;i++){
        arr.push_back(nums[q.front()]);
        
        while(!q.empty() && q.front() <= i-k){
            q.pop_front();
        }

        while(!q.empty() && nums[q.back()]<= nums[i]){
            q.pop_back();
        }
          q.push_back(i);
         
      }

        arr.push_back(nums[q.front()]);

        return arr;
    }
};