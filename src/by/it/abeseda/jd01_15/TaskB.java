package by.it.abeseda.jd01_15;



/** @author comment one line */


/*
*меня достали эти комментарии
 */

import java.io.*;

public class TaskB {

    private static final String TXT="TaskB.txt";
    private static final String JAVA="TaskB.java";
    private static final String dir = System.getProperty("user.dir") + File.separator +
            "src" + File.separator +
            TaskB.class.getPackageName().replace(".", File.separator)+File.separator;

    private static final String fileToRead=dir+JAVA;
    private static final String fileToWrite=dir+TXT;

    public static void main(String[] args) {//медведи не летают
        StringBuilder text=readWholeText(fileToRead);
/*рыбп
*/
        while (isTextContainsJavaDocLineComment(text)){
            int begin=text.indexOf("/**");
            int end=text.indexOf("*/", begin);
            if(end==-1 || begin==-1){
                break;
            }else{
            text.replace(begin, end+2, "");}
        }
        while (isTextContainsSingleLineComment(text)){
            int begin=text.indexOf("/"+"/");
            int end=text.indexOf("\r", begin);
            if(end==-1||begin==-1){
                break;
            }else{
                text.replace(begin, end, "");}
        }
        while (isTextContainsMultiLineComment(text)){
            int begin=text.indexOf("/*");
            int end=text.indexOf("*/", begin);
            if(end==-1||begin==-1){
                break;
            }else{
            text.replace(begin, end+2, "");}
        }

/*
hjhvfgfjfjfjfjffvfj
jhjhvjhvjhvjhvjv
 */


        writeWholeText(text,fileToWrite);
        System.out.println(text);//медведи не летают
    }

    private static boolean isTextContainsSingleLineComment(StringBuilder text) {
        return isCommentExists(text.indexOf("//"));
    }

    private static boolean isTextContainsMultiLineComment(StringBuilder text) {
        return isCommentExists(text.indexOf("/*"));
    }

    private static boolean isTextContainsJavaDocLineComment(StringBuilder text) {
        return isCommentExists(text.indexOf("/**"));
    }
/*я устала*/
    private static boolean isCommentExists(int index){
        return index!=-1;
    }

    private static void writeWholeText(StringBuilder text, String fileToWrite) {
        try (PrintWriter out=new PrintWriter(new FileWriter(fileToWrite))){
                out.print(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder readWholeText(String fileToRead) {
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileToRead)) {
            int c;
            while ((c = fileReader.read()) != -1) {
                sb.append(Character.toChars(c));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
}