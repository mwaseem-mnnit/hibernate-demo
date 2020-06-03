class Solution {
    int getIndexValue(int i, int j, int[][] store, int idx) {
        if(i<0 || j<0 || i>=store.length || j >= store[0].length) {
            return 0;
        }
        return store[i][j];
    }

    public int countSquares(int[][] matrix) {
        if(matrix.length==0) return 0;
        int[][] store = new int[matrix.length][matrix[0].length];
        int result=0;
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                int v = Math.min(getIndexValue(i-1,j,store,0),
                        Math.min(getIndexValue(i-1,j-1,store,0), getIndexValue(i,j-1,store,0)));
                store[i][j] = matrix[i][j] == 1 ? v+1 : 0;
                result += store[i][j];
            }
        }
        return result;
    }
}