import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.BitSet;

import org.apache.log4j.Logger;



class WatchProcesses implements Runnable
{
    private String processNames[];
    private BitSet actProcs;
    private Runtime runtime;
    private Process proc;
    private InputStream inputstream;
    private InputStreamReader inputstreamreader;
    private BufferedReader bufferedreader;
    private String line;
    private boolean keep_monitoring;
    //private boolean found;
    public static BitSet actPubProcs;
    public static String procNames[];
    public static boolean updating = false;
    public static boolean suspended = false;
    public Logger logger = CPSDashLogger.getCPSDashLogger();

    WatchProcesses(){

    }
    WatchProcesses( String processNames[] ){
        this.processNames = processNames;
        actProcs = new BitSet( processNames.length );
        actProcs.clear();
        keep_monitoring = true;
    }
    public synchronized void setProcAlive( boolean alive ){
        //isProcessAlive = alive;
    }

    public static void setBitsActProcs( BitSet procs ){
        actPubProcs = procs;
    }
    public static void setProcsInfo( String names[] ){

        procNames = names;
    }
    public synchronized static boolean isProcAlive( String procName ){
        int j = 0;
       while( updating )++j;
            for(int i = 0; i < procNames.length; i++){
                if( procNames[i].equals(procName)){
                    return actPubProcs.get(i);
                }
            }
         return false;
    }
    public BitSet getBitsActProcs(){
        return actProcs;
    }
    public void startMonitor(){
        keep_monitoring = true;
    }
    public void stopMonitor(){
        keep_monitoring = false;
    }
    public void run(){

        logger.debug("Starting WatchProcesses - run()");
        int idx = 0;
        int beginIndex = 0;
        int endIndex = 0;

        while( Thread.currentThread().isAlive() ){

            try{
                while( WatchProcesses.suspended ){ Thread.sleep(1000);continue; }
                runtime = Runtime.getRuntime();
                proc = runtime.exec("cmd /c tasklist");
                inputstream = proc.getInputStream();
                inputstreamreader = new InputStreamReader(inputstream);
                bufferedreader = new BufferedReader(inputstreamreader);

                WatchProcesses.updating = true;
                actProcs.clear();
                while( (line = bufferedreader.readLine()) != null ){
                    for(int i=0; i < processNames.length; i++){
                    idx = line.indexOf(processNames[i]);
                    beginIndex = idx + processNames[i].length();
                    endIndex = beginIndex + 5;
                        if( ( line.indexOf(processNames[i]) ) != -1 &&  ( line.substring(beginIndex, endIndex).indexOf("exe") ) != -1 ){
                            actProcs.set(i, true);
                        }
                    }
                }
                WatchProcesses.updating = false;
                                
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