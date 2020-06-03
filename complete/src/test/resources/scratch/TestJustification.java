import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    void appendSpaces(StringBuilder builder, int n) {
        for(int i=0;i<n;i++) {
            builder.append(' ');
        }
    }

    String createSentence(String[] words, int l, int r, int maxWidth, boolean lastLine) {
        StringBuilder builder = new StringBuilder();
        int sum = 0;
        for(int i=l;i<=r;i++) sum+=words[i].length();
        int spaces = maxWidth - sum;
        int count = r-l+1;
        int[] spaceArr = new int[count];
        if(count==1) spaceArr[0]=spaces;
        else {
            int d=spaces/(count-1);
            int m=spaces%(count-1);
            for(int i=0;i<count-1;i++) {
                if(!lastLine) {
                    spaceArr[i] = d + (m>0 ? 1:0 );
                    m-=1;
                } else {
                    spaceArr[i] = 1;
                }
            }
            if(lastLine) spaceArr[count - 1] = spaces - (count - 1);
        }
        for(int i=l;i<=r;i++) {
            builder.append(words[i]);
            appendSpaces(builder, spaceArr[i-l]);
        }
        return builder.toString();
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        int size=0, l=0;
        List<String> result = new ArrayList<>();
        for(int i=0;i<words.length;i++) {
            if(size + words[i].length() > maxWidth) {
                result.add(createSentence(words, l, i-1, maxWidth, false));
                size=words[i].length()+1; l=i;
            } else if(size + words[i].length() == maxWidth) {
                result.add(createSentence(words, l, i, maxWidth, ( i == (words.length-1)) ));
                size=0;l=i+1;
            } else {
                size += words[i].length()+1;
            }
        }
        if(l<words.length)
        result.add(createSentence(words, l, words.length-1, maxWidth, true));
        return result;
    }
}