import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class StartStopProc{

    private static final String KILL = "taskkill";
    private static final String KILL_FLAGS = "/F /T /IM";
    private Runtime runtime;
    private Process proc;
    private InputStream inputstream;
    private InputStreamReader inputstreamreader;
    private BufferedReader bufferedreader;
    private String line;
    private String processName;
    private final static String UAC_FILE_NAME =  "PrinterOn Launcher.exe";

    StartStopProc(){
        processName = "";
    }



    public Logger logger = CPSDashLogger.getCPSDashLogger();

    public boolean startProc( String procName ){
        
        logger.debug("startProc() START");
        logger.debug("procName: " + procName );
        boolean res = true;
        int exitVal = 0;

        if( processName.equals("") )
            processName = procName;
        
        String UAC = CPSDashConfig.getCPSDashConfigInstance().getCPSDashPath() +  "\\" + UAC_FILE_NAME;
        
        try{

            runtime = Runtime.getRuntime();
            String cmdLaunch[] = { UAC, procName};
            proc = runtime.exec(cmdLaunch);
            
            //proc.waitFor();
            //Thread.sleep(1000);
            //exitVal = proc.exitValue();

        }catch( IOException ioe){
            logger.error(ioe.getMessage().toString());
            res = false;
        }/*catch( InterruptedException ie){
            logger.error(ie.getMessage().toString());
            res = false;
        }*/
        
        if( !checkOnProc( processName ) && exitVal != 0 ){
            JOptionPane.showMessageDialog(null, "Unable to start process: " + processName, "Process Error", JOptionPane.ERROR_MESSAGE);
        }
        logger.debug("startProc() END");
        return res;
    }
    
    public boolean startProc( String procName, String path, String file ){

        boolean res;

        processName = procName;
        String cmd = path + "\\" + procName +  " " + file;
        logger.debug("startProc() - cmd: " + cmd );
        res = startProc(cmd);

        return res;
    }

    public boolean startProc( String procName, String file ){

        boolean res = false;
        processName = procName;
        String cmd = procName + " " + file;
        res = OpenProc( cmd );

        return res;
    }

    public boolean stopProc( String procName ){

        boolean res = true;
        String cmd;
        int exitVal = 0;

        logger.debug("stopProc() START");
        logger.debug("procName: " + procName );

        cmd = KILL + " " + KILL_FLAGS + " " + procName;
        
        String UAC = CPSDashConfig.getCPSDashConfigInstance().getCPSDashPath() +  "\\" + UAC_FILE_NAME;
        try{

            runtime = Runtime.getRuntime();
            String cmdLaunch[] = { UAC, cmd};
            proc = runtime.exec(cmdLaunch);
            proc.waitFor();

        }catch( IOException ioe){
            logger.error(ioe.getMessage().toString());
            res = false;
        }catch( InterruptedException ie){
            logger.error(ie.getMessage().toString());
            res = false;
        }
        if( checkOnProc( processName ) && exitVal != 0 ){
            JOptionPane.showMessageDialog(null, "Unable to stop process:" + processName, "Process Error", JOptionPane.ERROR_MESSAGE);
        }


        return res;
    }

    public boolean startService( String cmd, String procName ){
        boolean res = true;
        int exitVal = 0;

        logger.debug("startService() START");
        logger.debug("cmd: " + cmd + "process: " + procName );

        String UAC = CPSDashConfig.getCPSDashConfigInstance().getCPSDashPath() +  "\\" + UAC_FILE_NAME;
        try{

            runtime = Runtime.getRuntime();
            String cmdLaunch[] = { UAC, cmd, "net start"};
            proc = runtime.exec(cmdLaunch);
            proc.waitFor();
            exitVal = proc.exitValue();

        }catch( IOException ioe){
            logger.error(ioe.getMessage().toString());
            res = false;
        }catch( InterruptedException ie){
            logger.error(ie.getMessage().toString());
            res = false;
        }

        if( !checkOnProc( procName ) && exitVal != 0 ){
            JOptionPane.showMessageDialog(null, "Unable to start service for: " + procName, "Process Error", JOptionPane.ERROR_MESSAGE);
        }
        logger.debug("startService() END");

        return res;
    }
    
    public boolean JavaExec( String cmd){
        boolean res = true;
        int exitVal = 0;

        logger.debug("startService() START");
        logger.debug("cmd: " + cmd + "process:");

        String UAC = CPSDashConfig.getCPSDashConfigInstance().getCPSDashPath() +  "\\" + UAC_FILE_NAME;
        try{

            runtime = Runtime.getRuntime();
            String cmdLaunch[] = { UAC, cmd, "java -classpath"};
            proc = runtime.exec(cmdLaunch);
            proc.waitFor();
            exitVal = proc.exitValue();

        }catch( IOException ioe){
            logger.error(ioe.getMessage().toString());
            res = false;
        }catch( InterruptedException ie){
            logger.error(ie.getMessage().toString());
            res = false;
        }

       /* if( !checkOnProc( procName ) && exitVal != 0 ){
            JOptionPane.showMessageDialog(null, "Unable to start service for: " + procName, "Process Error", JOptionPane.ERROR_MESSAGE);
        }*/
        logger.debug("startService() END");

        return res;
    }
    
    public boolean stopService( String cmd, String procName ){
        boolean res = true;
        int exitVal = 0;

        logger.debug("stopService() START");
        logger.debug("cmd: " + cmd + " process: " + procName );

        
        String UAC = CPSDashConfig.getCPSDashConfigInstance().getCPSDashPath() +  "\\" + UAC_FILE_NAME;
        try{

            runtime = Runtime.getRuntime();
            String cmdLaunch[] = { UAC, cmd, "net stop"};
            proc = runtime.exec(cmdLaunch);
            proc.waitFor();
            exitVal = proc.exitValue();

        }catch( IOException ioe){
            logger.error(ioe.getMessage().toString());
            res = false;
        }catch( InterruptedException ie){
            logger.error(ie.getMessage().toString());
            res = false;
        }

        if( checkOnProc( procName ) && exitVal != 0 ){
            JOptionPane.showMessageDialog(null, "Unable to stop service for: " + procName, "Process Error", JOptionPane.ERROR_MESSAGE);
        }
        logger.debug("stopService() END");

        return res;
    }

    public boolean checkOnProc( String procName ){
        boolean found = false;

        try{

            runtime = Runtime.getRuntime();
            proc = runtime.exec("cmd /c tasklist");
            //proc.waitFor();
            inputstream = proc.getInputStream();
            inputstreamreader = new InputStreamReader(inputstream);
            bufferedreader = new BufferedReader(inputstreamreader);
            
            Thread.sleep(10);

            try {
	            while( (line = bufferedreader.readLine()) != null ){
	                if( line.indexOf(procName) != -1 ){
	                    found = true;
	                }
	            }
            } finally {
            	bufferedreader.close();
            	inputstreamreader.close();
            	inputstream.close();
            	proc.destroy();
            }

        }catch( IOException ioe){
            logger.error(ioe.getMessage().toString());
        }catch( Exception e){
            logger.error(e.getMessage().toString());
         }/*catch( InterruptedException ie){
            logger.error(ie.getMessage().toString());
        }*/

        return found;

    }

	public boolean OpenProc(String procName) {
		// TODO Auto-generated method stub
		logger.debug("startProc() START");
        logger.debug("procName: " + procName );
        boolean res = true;
        int exitVal = 0;

        if( processName.equals("") )
            processName = procName;
        
        //String UAC = CPSDashConfig.getCPSDashConfigInstance().getCPSDashPath() +  "\\" + UAC_FILE_NAME;
        
        try{

            runtime = Runtime.getRuntime();
            //String cmdLaunch[] = { UAC, procName};
            proc = runtime.exec(procName);
            
            //proc.waitFor();
            //Thread.sleep(1000);
            //exitVal = proc.exitValue();

        }catch( IOException ioe){
            logger.error(ioe.getMessage().toString());
            res = false;
        }/*catch( InterruptedException ie){
            logger.error(ie.getMessage().toString());
            res = false;
        }*/
        
        if( !checkOnProc( processName ) && exitVal != 0 ){
            JOptionPane.showMessageDialog(null, "Unable to start process: " + processName, "Process Error", JOptionPane.ERROR_MESSAGE);
        }
        logger.debug("startProc() END");
        return res;		
	}
}