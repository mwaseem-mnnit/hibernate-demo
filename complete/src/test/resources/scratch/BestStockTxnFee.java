import java.util.Stack;

class Solution {
    public int maxProfit(int[] prices, int fee) {
        if(prices.length<2) return 0;
        int min=Integer.MAX_VALUE, result=0, i=0, sub=0;
        while (i<prices.length) {
            while (i+1<prices.length && prices[i+1]<=prices[i]) i+=1;
            if(min>prices[i])
            min=prices[i];
            while (i+1<prices.length && prices[i+1]>=prices[i]) i+=1;
            if(prices[i]-min-fee-sub>0)
                result+=prices[i]-min-fee-sub;
            i+=1;
            int pp=prices[i-1];
            while (i+1<prices.length && prices[i+1]<=prices[i]) i+=1;
            if(i<prices.length && pp-prices[i]>=fee) {
                min=prices[i];
                sub=0;
            } else {
                sub=Math.max(pp - min - fee, 0);
            }
        }
        return result;
    }
    public static class TreeNode {
        int val;TreeNode left;TreeNode right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;this.left = left;this.right = right;
        }
    }
    public class A extends int[] {

    }
    public static void main(String[] args) {
        int [] c=new int[]{1};
        TreeNode[] t=new TreeNode[1];
        System.out.println(c.getClass()+"  "+t.getClass());
        Solution obj=new Solution();
        System.out.println(obj.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(obj.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 3));
        System.out.println(obj.maxProfit(new int[]{3,2}, 2));
        System.out.println(obj.maxProfit(new int[]{1,2,3,4,5,6,7,8,9,10}, 2));
        System.out.println(obj.maxProfit(new int[]{1,4,6,2,8,3,10,14}, 3));
        System.out.println(obj.maxProfit(new int[]{1,3,7,5,10,3}, 3));
        System.out.println(obj.maxProfit(new int[]{4,5,2,4,3,3,1,2,5,4}, 1));
        System.out.println(obj.maxProfit(new int[]{4,5,5,2,4,3,5,5,2,3}, 3));
        System.out.println(obj.maxProfit(new int[]{9,8,3,4,5,8,7,6,5,6,4,10,9,9,9,14,13,17}, 2));
    }
}