class Solution {
    public static int subarraySum(int[] nums, int k) {
        if(nums==null || nums.length==0) return 0;
        int l=0,r=0,sum=0;
        int ans=0;
        while(r<nums.length) {
            sum+=nums[r];
            if(sum == k) {
                ans++;
            } else if(sum>k) {
                while(sum>k && l<r) {
                    sum-=nums[l]; l++;
                }
                if(sum>k) {
                    sum=0; l++;r++;
                } else {
                    sum-=nums[r];
                }
            } else {
                r++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{4,1,2,4,3}, 3));
        System.out.println(subarraySum(new int[]{1,2,3}, 3));
    }
}