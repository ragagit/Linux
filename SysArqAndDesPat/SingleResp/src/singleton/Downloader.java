/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

/**
 *
 * @author raga
 */

//This is not thread safe. Instead we use enum, by default they are synchronized.
public class Downloader {
    
    //eager version
    //private static Downloader downloader = new Downloader();
    private static Downloader downloader;
    
    //we can have a private cinstructor as we don't want the class to be instantiated

    private Downloader() {
       
    }
    
    public void startDownloading(){
        System.out.println("Start downloading from the web ...");
    }
    
    public static Downloader getInstance(){
        if( downloader == null )
            return new Downloader();
        
        return downloader;
    }
    
}
