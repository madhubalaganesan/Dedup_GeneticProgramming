package DeDuplication;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LevenshteinDistance {
public static double similarity(String s1, String s2) {
if (s1.length() < s2.length()) { // s1 should always be bigger
String swap = s1; s1 = s2; s2 = swap;
}
int bigLen = s1.length();
if (bigLen == 0) { return 1.0; /* both strings are zero length */ }
return (bigLen - computeEditDistance(s1, s2)) / (double) bigLen;
}
public static int computeEditDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
int[] costs = new int[s2.length() + 1];
for (int i = 0; i <= s1.length(); i++) {
int lastValue = i;
for (int j = 0; j <= s2.length(); j++) {
if (i == 0)
                    costs[j] = j;
else {
if (j > 0) {
int newValue = costs[j - 1];
if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
}
}
}
if (i > 0)
                costs[s2.length()] = lastValue;
}
return costs[s2.length()];
}
public static void printDistance(String s1, String s2) {
System.out.println(s1 + "-->" + s2 + ": " +
                    computeEditDistance(s1, s2) );//+ " ("+similarity(s1, s2)+")");
    //System.out.println(" "+com);
}
public static int sim(File f1,File f2)
{
        try {
            RandomAccessFile fl1=new RandomAccessFile(f1,"r");
            RandomAccessFile fl2=new RandomAccessFile(f2, "r");
            fl1.seek(0);
            fl2.seek(0);
            StringBuilder s1=new StringBuilder(100);
            while(true)
            {
                Byte ch = null;
                try
                {
                ch=fl1.readByte();
                //System.out.println("got "+ch);
                }catch(Exception e)
                {
                  //System.out.println(fl1.length()+" "+e.toString());
                    break;
                }
                if(ch == null)
                 break;
            s1.append(ch); 
            }
            StringBuilder s2=new StringBuilder(100);
            while(true)
            {
            
            Byte ch = null;
                try
                {
                ch=fl2.readByte();
                //    System.out.println(" got "+ch);
                }catch(Exception e)
                {
                    break;
                    
                }
                s2.append(ch); 
            }
            //System.out.println("s1 ="+s1.toString());
            //System.out.println(" s2 = "+s2.toString());
            System.out.println(" \nstarted"+new java.util.Date().getTime());
            int cd = computeEditDistance(s1.toString(), s2.toString());
        return cd;
        } catch (Exception ex) {
            Logger.getLogger(LevenshteinDistance.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
}
public static void main(String[] args) {
    File f1=new File("E:project/input/u12.txt");
    File f2=new File("E:project/input/u12.txt");
  //      System.out.println(""+f1.length());
        int sim=-99999;
        Long t1=new java.util.Date().getTime();
        sim = sim(f1, f2);
        
        System.out.println("sim = "+sim);
        Long t2=new java.util.Date().getTime();
        System.out.println(" diff = "+(t2-t1));
        // last diff = 188114 msecs
        //        printDistance("", "");
        //        printDistance("1234567890", "1");
        //        printDistance("1234567890", "12");
        //        printDistance("1234567890", "123");
        //        printDistance("1234567890", "1234");
        //        printDistance("1234567890", "12345");
        //        printDistance("1234567890", "123456");
        //        printDistance("1234567890", "1234567");
        //        printDistance("1234567890", "12345678");
        //        printDistance("1234567890", "123456789");
        //        printDistance("1234567890", "1234567890");
        //        printDistance("1234567890", "1234567980");
        //        printDistance("47/2010", "472010");
        //        printDistance("47/2010", "472011");
        //        printDistance("47/2010", "AB.CDEF");
        //        printDistance("47/2010", "4B.CDEFG");
        //        printDistance("47/2010", "AB.CDEFG");
        //        printDistance("The quick fox jumped", "The fox jumped");
        //        printDistance("The quick fox jumped", "The fox");
        //        printDistance("The quick fox jumped",
        //"The quick fox jumped off the balcany");
        //printDistance("kitten", "kitt       ene");
        //        printDistance("rosettacode", "raisethysword");
        //   printDistance(new StringBuilder("rosettacode").reverse().toString(),
        //new StringBuilder("raisethysword").reverse().toString());
}
}