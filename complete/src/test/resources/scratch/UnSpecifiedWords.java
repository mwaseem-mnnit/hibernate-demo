import java.util.List;

class UnSpecifiedWords {
    int binarySearch(List<String> bag, int l, int r, int pos, char c) {
        int mid=(l+r)/2;
        while (l<r) {
            if(bag.get(mid).charAt(pos) <= c ) {
                l=mid;
            } else if(bag.get(mid).charAt(pos) > c ) {
                r=mid;
            }
        }
        if(mid!=c) return -1;
        return mid;
    }
    int[] search(List<String> bag, int l, int r, int pos, Character c) {
        int f=binarySearch(bag,l,r,pos, (char) (c - 1));
        int e=binarySearch(bag,l,r,pos,c);
        return new int[]{f,e};
    }

    int recur(List<String> bag, String query, int pos, int l, int r) {
        if(query.charAt(pos) != '?') {
            int[] boundary=search(bag, l,r, pos, query.charAt(pos));
            recur(bag, query, pos+1, boundary[0], boundary[1]);
        } else {
            for(int i=0;i<26;i++) {
                int[] boundary=search(bag, l,r, pos, (char)('a'+i));
                recur(bag, query, pos+1, boundary[0], boundary[1]);
            }
        }
        return 0;
    }
    /*
    * if ? then iterate from a to z and call recursion for each alphabet
    * */
    public static void main(String[] args) {
        
    }
}