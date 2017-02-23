import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

/**
 * This class creates the CPSDashboard tray icon. On right click a menu pops up
 * with three items:
 * Exit which removes the icon from the tray.
 * About which displays the version of CPSDashboard and CPSDash Tray.
 * Restore which launches the CPSDashboad.
 * @author garciar
 */

public class CPSDashTray{

    static CPSDashTray dashtray;
    String dashpath, dashversion, datapath;
    Logger logger;
    private final String DASH_FILE_NAME = "DashRunning.dsh";
    private final String TRAY_FILE_NAME = "TrayRunning.dsh";

   CPSDashTray(){

   getCPSDashInfo();
   CPSDashLogger log = new CPSDashLogger("CPSDashboard");
   log.configLogger(datapath);
   logger = log.getCPSDashLogger();

    if( SystemTray.isSupported()){

        PopupMenu popup = new PopupMenu();
        
        final MenuItem exitTrayMI = new MenuItem("Exit");
        final MenuItem restoreMI = new MenuItem("Restore");
        final MenuItem aboutMI = new MenuItem("About");
        final SystemTray tray = SystemTray.getSystemTray();
        final String dashpathin = dashpath;
        final String dashversionin = dashversion;

        Image image = Toolkit.getDefaultToolkit().getImage( dashpathin  + "\\Images\\cps_icon.gif" );
        final TrayIcon trayIcon = new TrayIcon( image, "CPSDashboard", popup );
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if( e.getSource().equals(exitTrayMI)){
                    tray.remove(trayIcon);
                    removeTrayFile();
                    System.exit(0);
                  }else if(e.getSource().equals(restoreMI)){
                      if( ! isDashboardRunning() ){
                        restoreCPSDashboard();
                      }
                 }else if(e.getSource().equals(aboutMI)){
                      JOptionPane.showMessageDialog(null, "CPS Dashboard " + dashversionin, "CPSDashboard", JOptionPane.INFORMATION_MESSAGE);
                 }
              }
            };
            aboutMI.addActionListener(listener);
            restoreMI.addActionListener(listener);
            exitTrayMI.addActionListener(listener);
            popup.add(restoreMI);
            popup.add(aboutMI);
            popup.add(exitTrayMI);

            trayIcon.addActionListener(listener);
            MouseListener mouseListener = new MouseAdapter() {
                public void mouseClicked(MouseEvent mouseEvent) {
                    if (mouseEvent.getClickCount() == 2) {
                        //System.out.println("Tray Icon double clicked");
                        if( ! isDashboardRunning() ){
                            restoreCPSDashboard();
                        }
                    }
                }
            };
            trayIcon.addMouseListener(mouseListener);
            try{
                tray.add(trayIcon);
            }catch( AWTException e){
                logger.error("Unable to create tray icon");
            }
            createTrayFile();
                
    }else{
        JOptionPane.showMessageDialog(null, "TrayIcon not supported by your system", "TrayIcon", JOptionPane.ERROR_MESSAGE);
        logger.error("TrayIcon not supported by your system");
    }

  }

/**
 * This function queries the registry to get installation path and version of CPSDashboard
 */
  public void getCPSDashInfo(){
 
      String cmd = "reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\PrinterOn Corporation\\CPS Dashboard\" /s";
      String line ="";

        try{

            Process process = Runtime.getRuntime().exec(cmd);
            InputStreamReader reader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedreader = new BufferedReader(reader);

            //process.waitFor();
            Thread.sleep(1000);
            //exitCode = process.exitValue();

            while( (line = bufferedreader.readLine() ) != null ){
                if(  line.indexOf("Path") != -1 ){
                    dashpath = getCompVal(line);
                    //logger.debug("path:" + path);
                }
                if( line.indexOf("Version") != -1){
                    dashversion = getCompVal(line);
                }
                if( line.indexOf("Temp") != -1){
                	datapath = getCompVal(line);
                }
            }

        }catch( InterruptedException ie ){
            //logger.error(ie.getMessage().toString());
        }catch( Exception e){
           //logger.error(e.getMessage().toString());
        }
        
  }
    /**
     * This function gets the value of a registry key.
     * @param line
     * @return String
     */
      public String getCompVal( String line ){

        //logger.debug("getCompPath() START");
        //logger.debug("line:" + line);
        String REG_STR = "REG_SZ";
        int p = line.indexOf(REG_STR);

        if( p == -1)
            return "";

        //logger.debug("getCompPath() END");

        return line.substring( p + REG_STR.length()).trim();
    }

      /**
       * This function restores the CPSDasgboard
       */
   public void restoreCPSDashboard(){
    try{
        Runtime runtime = Runtime.getRuntime();
        String cmd = "java -jar " + "\"" + dashpath + "\\CPSDashboard.jar" + "\"";
        logger.debug(cmd);

        Process proc = runtime.exec( cmd );

        proc.getErrorStream().close();
        proc.getInputStream().close();
        proc.getOutputStream().close();

   }catch( IOException ioe){
                    logger.error(ioe.getMessage().toString());
                           
   }
  }

   /**
    * This function creats the tray flag file which contains the tray process PID
    * and it is an indication that the CPSDash tray might be already running.
    * @return boolean
    */
   public boolean createTrayFile(){

       boolean res= false;
       String pid1, pid  = "";

       File trayFile = new File( datapath + "\\conf\\" + TRAY_FILE_NAME );
       pid1 = ManagementFactory.getRuntimeMXBean().getName();
       pid = pid1.substring(0, pid1.indexOf("@"));
       logger.debug("pid: " + pid);

       try{
            res = trayFile.createNewFile();
            FileWriter fw = new FileWriter(trayFile);
            fw.write(pid);
            fw.close();
       }catch( IOException e ){
            logger.error("Unable to create file:" + TRAY_FILE_NAME);
            logger.error(e.getMessage().toString());
       }
       
       return res;
   }

   /**
    * It return true if the CPSDashboard is already running. It opens
    * the CPSDashboard file and gets the process PID, then checks whether
    * the process is actually running in memory, returns true if it is, otherwise
    * deletes the file and returns false.
    *
    * @return boolean
    */
   public boolean isDashboardRunning(){

       boolean res;
       String line = "";

       File dashFile = new File( datapath + "\\conf\\" + DASH_FILE_NAME );

       if( ! dashFile.exists() )
           return false;
       
       try{
        FileReader fr = new FileReader(dashFile);
        BufferedReader br = new BufferedReader(fr);
        line = br.readLine();
        logger.debug("pid from file:" + line );
        br.close();
        fr.close();
       }catch( IOException e){
            logger.error("Unable to read file:" + DASH_FILE_NAME);
            logger.error(e.getMessage().toString());
       }

       res = checkPID( line );

       if( res == false && dashFile.exists() )
           dashFile.delete();

       return ( res );
       
   }

   /**
    * It just removes the tray flag file if this exists.
    */
   public void removeTrayFile(){
       File trayFile = new File( datapath + "\\conf\\" + TRAY_FILE_NAME );

       if( trayFile.exists()){
           boolean del = trayFile.delete();
           if( del )
               logger.debug("File:" + TRAY_FILE_NAME + " successfully deleted");
           else
               logger.debug("File:" + TRAY_FILE_NAME + " was not deleted");

       }

   }

   /**
    * It checks whether the process with PID is actually running.
    * @param pid
    * @return boolean
    */
   public boolean checkPID( String pid ){

      String cmd = "tasklist /FI \"PID eq " + pid + "\"";
      String line ="";

      logger.debug( "checkPID - cmd:" + cmd );
      try{

        Process process = Runtime.getRuntime().exec(cmd);
        InputStreamReader reader = new InputStreamReader(process.getInputStream());
        BufferedReader bufferedreader = new BufferedReader(reader);

        while( (line = bufferedreader.readLine() ) != null ){
            if(  line.indexOf("java") != -1 ){
                logger.debug("PID: " + pid + " found");
                return true;
            }
        }

      }catch( Exception e){
           logger.error(e.getMessage().toString());
      }

      return false;

   }

   public static void main(String args[]) {
       dashtray = new CPSDashTray();
   }

}