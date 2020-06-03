import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class TwoCitySchedule {
    public static void main(String[] args) {
        TwoCitySchedule object = new TwoCitySchedule();
        object.twoCitySchedCost(new int[][]{{10,20}, {20,10}});
        object.floodFill(new int[][]{{0,0}, {0,0}}, 0,0,1);
    }

    public void paint(int[][] image, int sr, int sc, int newColor, int originalColor) {
        if(sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != originalColor || image[sr][sc] == newColor) {
            return;
        }
        image[sr][sc] = newColor;
        paint(image, sr-1,sc, newColor, originalColor);
        paint(image, sr+1,sc, newColor, originalColor);
        paint(image, sr,sc-1, newColor, originalColor);
        paint(image, sr,sc+1, newColor, originalColor);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        paint(image, sr,sc,newColor, image[sr][sc]);
        return image;
    }

    public int twoCitySchedCost(int[][] costs) {
        List<List<Integer>> diff = new ArrayList<>(costs.length);
        for(int i=0; i<costs.length;i++) {
            List<Integer> temp = new ArrayList<>(2);
            temp.add(costs[i][0] - costs[i][1]);
            temp.add(i);
            diff.add(temp);
        }
        diff.sort(Comparator.comparingInt(o -> o.get(0)));
        int ans=0;
        for(int i=0;i<costs.length/2;i++) {
            ans+=costs[diff.get(i).get(1)][0];
        }
        for(int i=costs.length/2;i<costs.length;i++) {
            ans+=costs[diff.get(i).get(1)][1];
        }
        return ans;
    }
}