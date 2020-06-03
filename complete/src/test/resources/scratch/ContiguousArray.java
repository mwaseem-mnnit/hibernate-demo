import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int result=0;
        int sum=0;
        for(int i=0;i<nums.length;i++) {
            sum += (nums[i]==0) ? -1 : 1;
            if(map.containsKey(sum)) {
                result = Math.max(result, i-map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return result;
    }
    public int findMaxLength1(int[] nums) {
        if(nums.length==0) return 0;
        int min=0, max=0, sum=0;
        for(int i=0;i<nums.length;i++) {
            sum += (nums[i] == 0) ? -1 : 1;
            if(sum<min) min = sum;
            if(sum>max) max=sum;
        }
        int C = -min;
        int[] map = new int[ max - min + 1];
        Arrays.fill(map, -2);
        int result=0;
        sum=0;
        map[C]=-1;
        for(int i=0;i<nums.length;i++) {
            sum += (nums[i]==0) ? -1 : 1;
            if(map[C+sum] != -2 ) {
                result = Math.max(result, i - map[C+sum]);
            } else {
                map[C+sum]=i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.findMaxLength(new int[]{0,1,1,1,0}));
    }
}