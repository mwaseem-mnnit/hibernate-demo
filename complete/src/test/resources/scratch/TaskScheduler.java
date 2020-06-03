public class TaskScheduler {

    public static void main(String[] args) {
        leastInterval(new char[]{'A','A','A','B','B','B'}, 2);
    }
    static int[] findMax(int[] nums) {
        int a=-1,b=-1,c=-1;
        for( int i=0;i<nums.length;i++) {
            if( nums[i]>a) {
                c=b;b=a;a=nums[i];
            } else if ( nums[i] > b) {
                c=b;b=nums[i];
            } else if( nums[i]>c) c=nums[i];
        }
        int[] ans = new int[3];
        ans[0]=a;ans[1]=b;ans[2]=c;
        return ans;
    }
    static int findTaskNumber(int[] tasks) {
        int ans = 0;
        for( int i=0; i<tasks.length;i++) if( tasks[i] > 0) ans+=1;
        return ans;
    }
    public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for( int i=0;i<tasks.length; i++) freq[tasks[i]-'A']+=1;
        int[] max = findMax(freq);
        int ans = 0;
        int numTasks = findTaskNumber(freq);
        if( numTasks > 2) ans = max[2]*(numTasks-2);
        if(max[1]-max[2]>0)
            ans+=3*(max[1]-max[2])-1;
        if(max[0]-max[1]>0)
            ans+=3*(max[0]-max[1])-1;
        return ans;
    }
}