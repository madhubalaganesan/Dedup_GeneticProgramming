/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DeDuplication;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.io.InputStream;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author saikat
 */
public class cv {
    /**
     * converts the given file into a txt document and returns the path as string
     * .does NOT return until fie is converted. does so by running a loop and checking last modified date on /project/temp/cur.txt
     */
    public static String convert(File inp, String outputpath)
    {
        RandomAccessFile fl=null;
        try
        {
            fl=new RandomAccessFile(outputpath,"rw");
            String fname=inp.getName();
            String arr[]=fname.split("\\.");
            String end=arr[arr.length-1];
          //  System.out.println(" extension = "+end);
            if(end.equals("docx"))
            {
            
            XWPFDocument docx=new XWPFDocument(new FileInputStream(inp));
            XWPFWordExtractor ext=new XWPFWordExtractor(docx);
            String str=ext.getText();
            fl.write(str.getBytes());
           fl.close();
            }else if(end.equals("doc"))
            {
           
                System.out.println(" for doc");
           InputStream is = new FileInputStream(inp.getAbsolutePath());
           WordExtractor ext=new WordExtractor(is);
            String str=ext.getText();
            fl.write(str.getBytes());
            }else if(end.equals("pptx"))
            {
           
                System.out.println(" for pptx");
           //  XWPFDocument pptx=new XWPFDocument(new FileInputStream(inp));
            XMLSlideShow pptx = new XMLSlideShow(new FileInputStream(inp));
            XSLFPowerPointExtractor xw = new XSLFPowerPointExtractor(pptx);
            System.out.println(xw.getText());
            String str=xw.getText();
            fl.write(str.getBytes());
            }
            else if(end.equals("ppt"))
            {
           
                System.out.println(" for ppt");
            InputStream is = new FileInputStream(inp.getAbsolutePath());
            PowerPointExtractor ext=new PowerPointExtractor(is);
            String str=ext.getText();
            fl.write(str.getBytes());
            }else if(end.equals("xls"))
            {
           
            System.out.println(" for xls");
            HSSFWorkbook xls = new HSSFWorkbook(new FileInputStream(inp));
            ExcelExtractor ext = new ExcelExtractor(xls);          
            String str=ext.getText();
            fl.write(str.getBytes());
            }
            else if(end.equals("xlsx"))
            {
            System.out.println(" for xlsx");
            XSSFWorkbook xlsx = new XSSFWorkbook(new FileInputStream(inp));
            XSSFExcelExtractor xw = new XSSFExcelExtractor(xlsx);
                    
            System.out.println(xw.getText());
            String str=xw.getText();
            fl.write(str.getBytes());
            }
           
            else
            {
           // throw new IllegalArgumentException(" format "+end+" not supported");
            
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(cv.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        
        }
        finally{
        
        }
        return outputpath;
    
    }
    public static String conv(File inp, String outputpath) 
    {
        
       try
       {
           Thread.sleep(1000);
           //System.out.println(" entered convert. file = "+inp.getAbsolutePath());
        String nm=inp.getName();
        String[] arr = nm.split("\\.");
        String ext=arr[arr.length-1];
       // System.out.println(" ext = "+ext);
       if(ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx")||ext.equalsIgnoreCase("html")||ext.equalsIgnoreCase(""))
       {
 String cd="C:\\Program Files\\TotalDocConverter\\dc.exe \""+inp.getAbsolutePath()+" \"  \""+outputpath+"\" -c TXT";
           System.out.println("cd = "+cd);
          new File(outputpath).delete();
           
           Long ts1=new java.util.Date().getTime();
           Runtime.getRuntime().exec(cd);
           Long ts2;
        //System.out.println("(cmd called) processing file "+inp.getAbsolutePath());
        while(new File(outputpath).exists()==false){    
        
            //System.out.println(" ts1 = "+ts1+" ts2 = "+ts2);
        }
         //  System.out.println("processed ");
           return outputpath;
       }
    }
       catch (Exception ex) {
                Logger.getLogger(cv.class.getName()).log(Level.SEVERE, null, ex);
                return ex.toString();
       }
       //for all others including txt files return this
         return inp.getAbsolutePath();
    
    }
    public static void main(String[] args) {
String res = convert(new File("E:\\project\\input\\u1.doc"),"E:\\project\\input\\u1.txt");
        System.out.println(" res ="+res);
    }
}
