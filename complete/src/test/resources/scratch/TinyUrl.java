import java.util.HashMap;
import java.util.Map;

public class TinyUrl {
    Map<Integer,String> storage = new HashMap<>();
    int seq = 1;
    char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    public String encode(String longUrl) {
        StringBuilder shortUrl=new StringBuilder();
        int n=seq;
        storage.put(n,longUrl);
        seq+=1;
        while (n>0)
        {
            shortUrl.append(map[n%62]);
            n = n/62;
        }
        return shortUrl.reverse().toString();
    }

    public String decode(String shortUrl) {
        long id = 0L; // initialize result

        // A simple base conversion logic
        for (int i=0; i < shortUrl.length(); i++)
        {
            if ('a' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= 'z')
                id = id*62 + shortUrl.charAt(i) - 'a';
            if ('A' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= 'Z')
                id = id*62 + shortUrl.charAt(i) - 'A' + 26;
            if ('0' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= '9')
                id = id*62 + shortUrl.charAt(i) - '0' + 52;
        }
        return storage.get(id);
    }

    public static void main(String[] args) {
        TinyUrl tinyUrl = new TinyUrl();
        String encode = tinyUrl.encode("https://leetcode.com/problems/design-tinyurl");
        String decode = tinyUrl.decode(encode);
        System.out.println(decode+" "+encode);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));