/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeDuplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Integer;
/**
 *
 * @author saikat
 */
public class genprog {
    private static int debug=0;
   
     
    public static int gp(Integer arr[])
    {
        if(arr.length<=1) return arr[0];
        String str="";
        int lc=0;
        int max=arr[0];
        for(int i=0;i<arr.length/5;i++)
        {
            max=arr[lc];
        for(int j=0;j<5;j++)
        {
            if(max<arr[lc]) {
                max=arr[lc];
            }
        lc++;
        }
        str+="#"+max;
        }
        lc--;
        if(lc<0) lc=0;
        max=arr[lc];
        for(int j=lc;j<arr.length;j++)
        {
        if(max<arr[j])
            max=arr[j];
        }
        str+="#"+max;
        
        String[] sa=str.split("#");
        Integer na[]=new Integer[sa.length-1];
        System.out.println(" str ="+str);
        for(int i=1;i<sa.length;i++)
        {
            na[i-1]=Integer.parseInt(sa[i]);
            System.out.println("sa "+i+" "+na[i-1]);
        }
        return gp(na);
    
    }
    
    
    public static void main(String[] args) throws IOException {
        debug=0;
        Integer rp=0;
        int s=108;
        Integer sc[]=new Integer[s];
        Random rn=new Random();
        for(int i=0;i<sc.length;i++)
        {
            long dum=0;
        sc[i]=rn.nextInt(200);
            System.out.println(" "+sc[i]);
        }
        Integer res=gp(sc);
        System.out.println("res = "+res);
    }

}
