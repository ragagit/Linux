/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readlinesfromfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.io.FileWriter;
 
public class ReadLinesFromFile {
 
    public static void main(String a[]){
        BufferedReader br = null;
        String strLine = "";
        BufferedWriter bw = null;
        Reader reader = null;
        Writer writer = null;
        File file = null;
        
        try {
            
            reader = new InputStreamReader(System.in);            
            br = new BufferedReader(reader);
            file = File.createTempFile("MyTempFile", "tmp");
            System.out.println("Temp file:" + file.getAbsolutePath());
           
            bw = new BufferedWriter(new FileWriter(file));
            
            
            while( !strLine.equalsIgnoreCase("exit") ){
                strLine = br.readLine();
                bw.write(strLine);
            }
            bw.close();
            br.close();
            
            br = new BufferedReader(new FileReader(file));
                        
            //br = new BufferedReader( new FileReader("C:/tmp/file.txt"));
            System.out.println("Content of file:");
            while( (strLine = br.readLine()) != null){
                System.out.println(strLine);
            }
            System.out.println("End of content");
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find the file: fileName");
        } catch (IOException e) {
            System.err.println("Unable to read the file: fileName");
        }
    }
}
