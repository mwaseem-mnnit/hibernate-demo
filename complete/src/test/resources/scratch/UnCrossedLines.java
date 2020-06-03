class Solution {
    public int getIndexValue(int[][] store, int i, int j) {
        if(i < 0 || j < 0) return 0;
        return store[i][j];
    }
    public int maxUncrossedLines(int[] A, int[] B) {
        int[][] store = new int[A.length][B.length];
        for(int i=0;i<A.length;i++) {
            for(int j=0;j<B.length;j++) {
                store[i][j] = Math.max(Math.max(getIndexValue(store, i-1, j-1) + ( A[i]==B[j] ? 1 : 0),
                        getIndexValue(store,i,j-1)), getIndexValue(store, i-1, j));
            }
        }
        return store[A.length-1][B.length-1];
    }
}