/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeDuplication;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.logging.Level;

/**
 *
 * @author dell
 */
public class seq {
    
    
    /**
     * minimum lenght diff to advance
     */
    public static long mindiff=2500;
    public static float compare (String p1,String p2) throws java.io.IOException
    {
        p1=p1.trim();
        p2=p2.trim();
        File f1=new File(p1);
        File f2=new File(p2);
        //if( Math.abs(f1.length()-f2.length()) > mindiff)
            //return 999;
      //  System.out.println("compare called on "+p1+" and "+p2);
        //Getting the name of the files to be compared.
		BufferedReader br2 = new BufferedReader (new
InputStreamReader(System.in));
		
  		String str = p1;//;//br2.readLine();
  		System.out.println("Files to compare :\n1st File name:"+p1);
  		String str1 = p2;//"E:/project/input/t12.txt";//br2.readLine();
                System.out.println("2nd File name:"+p2);
		String s1="";
  		String s2="",s3="",s4="";
  		String y="",z="";

        //Reading the contents of the files
 		BufferedReader br = new BufferedReader (new FileReader (str));
  		BufferedReader br1 = new BufferedReader (new FileReader (str1));

 		while((z=br1.readLine())!=null)
 			s3+=z;

		while((y=br.readLine())!=null)
  			s1+=y;

		//System.out.println ();

        //String tokenizing
  		int numTokens = 0;
  		StringTokenizer st = new StringTokenizer (s1);
  		String[] a = new String[100000];
  		for(int l=0;l<100000;l++)
	   		{a[l]="";}
  		int i=0;
  		while (st.hasMoreTokens())
    	 {
      		s2 = st.nextToken();
     	    a[i]=s2;
			i++;
            numTokens++;
    	 }

     	int numTokens1 = 0;
	   	StringTokenizer st1 = new StringTokenizer (s3);
	   	String[] b = new String[100000];
	   	for(int k=0;k<100000;k++)
	   		{b[k]="";}
	   	int j=0;
	   	while (st1.hasMoreTokens())
	      {
	       s4 = st1.nextToken();
	       b[j]=s4;
	       j++;
	       numTokens1++;
	      }

//comparing the contents of the files and printing the differences, if any.
		int x=0;
                                        int tot=0;
     	for(int m=0;m<a.length;m++)
     	{
                if(a[m].equals(b[m])==false && a[m].trim().length()>1 &&b[m].trim().length()>1)
                {
      //              System.out.println(a[m]+"----"+b[m]);
                    x++;
                }
                else if(a[m].trim().length()>1 &&b[m].trim().length()>1)
                { 
    //                System.out.println(a[m]+"==="+b[m]);
                    tot++;
                
                }
//                                int expextedpos=0;
//                                int tot=0;
//                                for(int ct=expextedpos-5;ct<expextedpos+5;ct++)
//                                {
//                                    if(a[m+ct]==b[m])
//                                        ;
//                                }
                                                          
                    }
 //System.out.println("No. of differences : " + x);
 if(x>0){
     //System.out.println("Files are not equal");
 }
 else{
 //    System.out.println("Files are equal. No difference found");
 }
        //System.out.println(" res = "+x);
        
        long per=a.length-x;
        System.out.println(String.format(" x = %d a.length= %d per = %d", x,a.length,per));
        
        float pa1=per;
        float pa2=tot;//a.length;
       float pt=tot/(tot+x);
       //pt=pt*100;
        System.out.println("pt = "+pt+" tot="+tot+" x="+x);
        return  pt*100;
 }
    
    public static void copy(String source,String dest) throws Exception
    {
    new File(dest).delete();
    RandomAccessFile s=new RandomAccessFile(source, "r");
    RandomAccessFile d=new RandomAccessFile(dest, "rw");
    //FileReader sr=new FileReader(new File(source));
    try
    {
    while(true)
    {
    Byte b=s.readByte();
    if(b==null)
        break;
    d.writeByte(b);
    }
    }
    catch(EOFException ex)
    {
        s.close();
        d.close();
        return;
    }
    s.close();
        d.close();
        
    }

    public static void main(String[] args) throws Exception {
        //long compare = compare("E:/project/input/t1.txt","E:/project/input/t12.txt");
       //System.out.println("param="+compare);
        float res=  compare("E:\\project\\storage\\u1.txt","E:\\project\\storage\\u12.txt");
        System.out.println(" res ="+res);
        //System.out.println(" "+(99965/100000));
        }

}
