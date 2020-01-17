


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        String str = "10.0\r";
        System.out.println(str);
        BigDecimal b = new BigDecimal(str.trim());
        System.out.println(b);
        try {
            File file = new File("/Users/mohd.waseem/Downloads/HPCL-card_numbers4.csv");
            FileInputStream is = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            is.read(bytes);
            String line1 = new String(bytes);
            String[] ll = line1.split("\n");
            for (int i=1; i<ll.length;i++) {
                System.out.println(ll[i].split(",")[0]);
                System.out.println(ll[i].split(",")[1]);
                System.out.println(ll[i].split(",")[0].trim());
                System.out.println(new BigDecimal(ll[i].split(",")[1].trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Integer> prime = new HashSet<>();
        Set<Integer> compass = new HashSet<>();
        try {
            BufferedReader b1 = new BufferedReader(new FileReader(new File("/Users/mohd.waseem/Downloads/prime_id")));
            BufferedReader b2 = new BufferedReader(new FileReader(new File("/Users/mohd.waseem/Downloads/compass_id")));
            String line1 = "";
            String line2 = "";
            while( (line1 = b1.readLine()) != null ) {
                prime.add(Integer.parseInt(line1.trim()));
            }
            b1.close();

            while( (line2 = b2.readLine()) != null ) {
                if(line2.charAt(0) == '\uFEFF')
                    line2 = line2.substring(1);
                compass.add(Integer.parseInt(line2.trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> primeNotInCompass = new ArrayList<>();
        for (Integer p : prime) {
            if( !compass.contains(p) )
                primeNotInCompass.add(p);
        }
        List<Integer> compassNotInPrime = new ArrayList<>();
        for (Integer c : compass) {
            if( !prime.contains(c) )
                compassNotInPrime.add(c);
        }
        System.out.println(primeNotInCompass);
        System.out.println(compassNotInPrime);
    }

    public static void main1(String[] args) {

        try {
            BufferedReader b1 = new BufferedReader(new FileReader(new File("/Users/mohd.waseem/Library/Preferences/IntelliJIdea2019.2/scratches/list1.csv")));

            String line = "";

            List<Integer> list1 = new ArrayList<>();
            while( (line = b1.readLine()) != null ) {
                String[] split = line.split(",");
                for (String s : split) {
                    list1.add(Integer.parseInt(s.trim()));
                }
            }

            List<Integer> list2 = new ArrayList<>();
            b1 = new BufferedReader(new FileReader(new File("/Users/mohd.waseem/Library/Preferences/IntelliJIdea2019.2/scratches/list2.csv")));
            while( (line = b1.readLine()) != null) {
                String[] split = line.split(",");
                for (String s : split) {
                    list2.add(Integer.parseInt(s.trim()));
                }
            }

            for (Integer i1 : list1) {
                boolean flag = false;
                for (Integer i2 : list2) {
                    if( i1.equals(i2) ) {
                        flag=true;
                        break;
                    }
                }
                if( !flag) {
                    System.out.print(i1+" ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}