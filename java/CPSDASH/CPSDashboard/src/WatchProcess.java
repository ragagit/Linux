/**
 * WatchProcess.java
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;



class WatchProcess implements Runnable
{
    private String processName;
    private boolean isProcessAlive;
    private Runtime runtime;
    private Process proc;
    private InputStream inputstream;
    private InputStreamReader inputstreamreader;
    private BufferedReader bufferedreader;
    private String line;
    private boolean found;

    public Logger logger = CPSDashLogger.getCPSDashLogger();

    WatchProcess(){
        isProcessAlive = false;
    }
    WatchProcess( String processName ){
        this.processName = processName;
        isProcessAlive = false;
        found = false;
    }
    public synchronized void setProcAlive( boolean alive ){
        isProcessAlive = alive;
    }

    public synchronized boolean isProcAlive(){
        return isProcessAlive;       
    }
    public void run(){

        logger.debug("Process to watch:" + processName );
        
        while( Thread.currentThread().isAlive() ){
            try{
                
                runtime = Runtime.getRuntime();
                proc = runtime.exec("cmd /c tasklist");                
                inputstream = proc.getInputStream();
                inputstreamreader = new InputStreamReader(inputstream);
                bufferedreader = new BufferedReader(inputstreamreader);
                
                found = false;
                while( (line = bufferedreader.readLine()) != null ){
                    if( line.indexOf(processName) != -1 ){
                        found = true;                 
                    }
                }
                if( found )
                    setProcAlive(true);
                else
                    setProcAlive(false);
                inputstreamreader = null;
                bufferedreader = null;
                Thread.sleep(5000);
               
            }catch( IOException ioe){
                logger.error(ioe.getMessage().toString());
            }catch( InterruptedException ie){
                logger.error(ie.getMessage().toString());
            }

        }

    }

}