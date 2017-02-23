import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


/**
 * This class allows the user to lunch PrinterOn log files.
 * @author garciar
 */
public class Logs extends JPanel implements ActionListener{

   
    String logNames[] = {"Tomcat Logs", "PrintAnywhere Processing Log", "PrintAnywhere Status Log", "Print Delivery Station Log", "PrinterOn Advertiser Log", "Print Delivery Gateway Log" };
    JButton btns[] = new JButton[logNames.length];
    public Logger logger = CPSDashLogger.getCPSDashLogger();
    String today; //Today's date yyyy-mm-dd
    String tomcat_logfile_name = "";
    String [] tomcat_logfile_names = {""};
    String tomcat_full_logfile_name = "";
    String printaw_prologfile_name = "";
    String printaw_statlogfile_name = "";
    String pds_logfile_path = "";
    String pdg_logfile_path = "";
    String [] pds_logfile_names = {""};  //All log files in directory
    String [] pdg_logfile_names = {""};  //All log files in directory
    String pds_logfile_name = ""; //Latest log file created
    String pdg_logfile_name = ""; //Latest log file created
    String pad_logfile_name = "";
    String pad_logfile_path = "";
    String [] pad_logfile_names = {""};
    public static String LogNames [] ={""};//
    String pasport_logfile_name;


    /**
     * This cunstructor creates the boxes and buttons dor log files. If any of the components with
     * log files is not currently installed the button gets disabled.
     */
    public Logs(){

        for( int i=0; i < logNames.length; i++){
            btns[i] = new JButton("Open");
            createLogBox( logNames[i], btns[i] );
        }

        getTomcatLogs();
        getPrintWhereLogs();
        getPDSLogs();
        getPADLogs();
        getPDGLogs();
        gatherLogNames();
        
        setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void refreshLogs(){
        getTomcatLogs();
        getPrintWhereLogs();
        getPDSLogs();
        gatherLogNames();
        getPDGLogs();
    }

    public void getPrintWhereLogs(){

    	boolean			 bStatLogFound = false;
    	boolean			 bProcLogFound = false;
    	String			 sStatusServerLog = "";
    	String			 sProcessingServerLog = "";
        String			 sPasportLog = "";
    	String			 sAppData_PONDir = "";
        CPSDashComponent comp = RegistryAccess.searchComp(CPSDashConstants.PRINTANYWHERE_COMP);

        RegistryAccess rg = new RegistryAccess();
        String sTmp = rg.getRegistryValue("HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders", "Common AppData");
        sAppData_PONDir = sTmp + "\\" + CPSDashConstants.PON_DIR + "\\";
        
        sStatusServerLog = sAppData_PONDir + "PrintAnywhere\\Logs\\" + CPSDashConstants.PRINTANYWHERE_STATLOGFILE_NAME;
        sProcessingServerLog = sAppData_PONDir + "PrintAnywhere\\Logs\\" + CPSDashConstants.PRINTANYWHERE_PROLOGFILE_NAME;
        sPasportLog = sAppData_PONDir + "PrintAnywhere\\Logs\\" + CPSDashConstants.PRINTANYWHERE_PASPORTLOGFILE_NAME;
        
        if( comp != null )
        {
            if( new File(sPasportLog).exists() )
            {
                pasport_logfile_name = sPasportLog;
            }
        	if( ! new File(sStatusServerLog).exists() )
        	{		
        		printaw_statlogfile_name = comp.getCompPath() + "\\Logs\\" + CPSDashConstants.PRINTANYWHERE_STATLOGFILE_NAME;
        		if( ! new File(printaw_prologfile_name).exists() )
        		{
        			bStatLogFound = false;
        		}
        		else
        		{
        			bStatLogFound = true;
        		}
        		
        	}
        	else
        	{
        		printaw_statlogfile_name = sStatusServerLog;
        		bStatLogFound = true;
        	}
        	
        	
        	if( ! new File(sProcessingServerLog).exists() )
        	{		
        		printaw_prologfile_name = comp.getCompPath() + "\\Logs\\" + CPSDashConstants.PRINTANYWHERE_STATLOGFILE_NAME;
        		if( ! new File(printaw_prologfile_name).exists() )
        		{
        			bProcLogFound = false;
        		}
        		else
        		{
        			//comp.setCompLogPath(printaw_prologfile_name);
        			bProcLogFound = true;
        		}
        		
        	}
        	else
        	{
        		printaw_prologfile_name = sProcessingServerLog;
        		bProcLogFound = true;
        	}        	
        }
        
        if (bProcLogFound ==  false)
        {
            btns[1].setEnabled(false);
            printaw_prologfile_name="";
        }
        else
        {
        	btns[1].setEnabled(true);
        }        
        
        if (bStatLogFound ==  false)
        {
            btns[2].setEnabled(false);
            printaw_statlogfile_name="";
        }
        else
        {
        	btns[2].setEnabled(true);
        }

/*        
        if( ! new File(printaw_prologfile_name).exists() )
        {
            btns[1].setEnabled(false);
            printaw_prologfile_name="";
        }
        else
        {
        	btns[1].setEnabled(true);
        }

        if( ! new File(printaw_statlogfile_name).exists()){
            btns[2].setEnabled(false);
            printaw_statlogfile_name="";
        }else{btns[2].setEnabled(true);}
*/
    }
   public void getTomcatLogs(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        today = formatter.format(date);
        tomcat_logfile_name = CPSDashConstants.TOMCAT_LOGFILE_NAME_STD + "." + today + ".log";
        logger.debug("Tomcat log file name: " + tomcat_logfile_name );
        CPSDashComponent comp = RegistryAccess.searchComp(CPSDashConstants.TOMCAT_COMP);

        if( comp != null ){
            tomcat_full_logfile_name = comp.getCompPath() + "\\logs\\" + tomcat_logfile_name;

            if( ( new File(comp.getCompPath()).exists() ) ){
                tomcat_logfile_names = getTomcatStdOutLogs(comp.getCompPath() + "\\logs\\");
                tomcat_full_logfile_name = tomcat_logfile_names[0];
            }
            logger.debug( "tomcat log file:" + tomcat_full_logfile_name );
        }


        if( ! new File(tomcat_full_logfile_name).exists()){
            btns[0].setEnabled(false);
            tomcat_full_logfile_name = "";
        }else{ btns[0].setEnabled(true);}

    }


    private String[] getTomcatStdOutLogs(String path){
        String[] files = null;
        File folder = new File(path);
        if(folder.exists()){
            File[] listOfFiles = folder.listFiles();
            Arrays.sort( listOfFiles, new Comparator(){

                public int compare( Object o1, Object o2){

                    File filea = (File)o1;
                    File fileb = (File)o2;
                    if ( filea.lastModified() > fileb.lastModified()) {
                        return -1;
                    } else if (filea.lastModified() < fileb.lastModified()) {
                        return +1;
                    } else {
                        return 0;
                    }
                }
            });
            ArrayList<String> stdoutFiles = new ArrayList<String>();
            for(File f : listOfFiles){
                if(f.getName().contains(CPSDashConstants.TOMCAT_LOGFILE_NAME_STD))
                    stdoutFiles.add(f.getPath());
            }
            files =  stdoutFiles.toArray(new String[stdoutFiles.size()]);
        }

        return files;
    }


    /**
     * This function queries the registry to get PDS logs path and check if the
     * files exist.
     */
    public void getPDSLogs(){

        //Getting PDS log file path
        RegistryAccess rg = new RegistryAccess();
        pds_logfile_path = rg.getRegistryValue("HKEY_LOCAL_MACHINE\\SOFTWARE\\PrinterOn Corporation\\Print Delivery Station", "LogDirectory");
        logger.debug("pds_logfile_path: " + pds_logfile_path );

        if( pds_logfile_path.length() != 0 && ( new File(pds_logfile_path).exists() ) ){
            logger.debug("pds_logfile_name is not empty");
            pds_logfile_names = getDirFiles(pds_logfile_path);

            // We take the most up-to-date pds log file
            if( pds_logfile_names.length != 0 )
                pds_logfile_name = pds_logfile_names[0];
        }
        // Button gets disable if there is no log file.
        if( pds_logfile_name.equals(""))
            btns[3].setEnabled(false);
        else
            btns[3].setEnabled(true);

    }


    public void getPDGLogs(){

        //Getting PDS log file path
        RegistryAccess rg = new RegistryAccess();
        pdg_logfile_path = rg.getRegistryValue("HKEY_LOCAL_MACHINE\\SOFTWARE\\PrinterOn Corporation\\Print Delivery Gateway", "LogDirectory");
        logger.debug("pdg_logfile_path: " + pdg_logfile_path );

        if( pdg_logfile_path.length() != 0 && ( new File(pdg_logfile_path).exists() ) ){
            logger.debug("pds_logfile_name is not empty");
            pdg_logfile_names = getDirFiles(pdg_logfile_path);

            // We take the most up-to-date pds log file
            if( pdg_logfile_names.length != 0 )
                pdg_logfile_name = pdg_logfile_names[0];
        }
        // Button gets disable if there is no log file.
        if( pdg_logfile_name.equals(""))
            btns[3].setEnabled(false);
        else
            btns[3].setEnabled(true);

    }

    /**
     * This function queries the registry to get PAD logs path and check if the
     * files exist.
     */
    public void getPADLogs(){

        //Getting PAD log file path
        RegistryAccess rg = new RegistryAccess();
        pad_logfile_path = rg.getRegistryValue("HKEY_LOCAL_MACHINE\\SOFTWARE\\PrinterOn Corporation\\PrinterOn Advertiser", "AppDataPath") + "\\Logs\\";
        logger.debug("pad_logfile_path: " + pad_logfile_path );

        if( pad_logfile_path.length() != 0 && ( new File(pad_logfile_path).exists() ) ){
            logger.debug("pad_logfile_name is not empty");
            pad_logfile_names = getDirFiles(pad_logfile_path);

            // We take the most up-to-date pad log file
            if( pad_logfile_names.length != 0 )
                pad_logfile_name = pad_logfile_names[0];
        }
        // Button gets disable if there is no log file.
        if( pad_logfile_name.equals(""))
            btns[4].setEnabled(false);
        else
            btns[4].setEnabled(true);

    }    
    

    /**
     * This function gathers the names of all log files as they are used in the Support tab.
     */
    public void gatherLogNames(){

      logger.debug("gatherLogNames() BEGIN");

      Vector<String> names = new Vector<String>();

      if( tomcat_full_logfile_name.length() != 0 ){
          for(int i=0; i< tomcat_logfile_names.length; i++){
              logger.debug("Adding " + tomcat_logfile_names[i] );
              names.add(tomcat_logfile_names[i]);
          }
      }
      if( printaw_prologfile_name.length() != 0 ){
        logger.debug("Adding " + printaw_prologfile_name );
        names.add(printaw_prologfile_name);
      }
      if( printaw_statlogfile_name.length() != 0 ){
        logger.debug("Adding " + printaw_statlogfile_name);
        names.add(printaw_statlogfile_name);
      }
      if( pds_logfile_name.length() != 0 ){
        for(int i=0; i< pds_logfile_names.length; i++){
          logger.debug("Adding " + pds_logfile_names[i] );
          names.add(pds_logfile_names[i]);
        }
      }
      if (pdg_logfile_name.length() != 0) {
        for (int i = 0; i < pdg_logfile_names.length; i++) {
            logger.debug("Adding " + pdg_logfile_names[i]);
            names.add(pdg_logfile_names[i]);
        }
      }
      if( pad_logfile_name.length() != 0 ){
    	  logger.debug("Adding " + pad_logfile_name); 
    	  names.add(pad_logfile_name);
      }
      if( pasport_logfile_name.length() != 0 ){
            logger.debug("Adding " + pasport_logfile_name);
            names.add(pasport_logfile_name);
      }

      Logs.makeLogNamesPublic(names);

      logger.debug("gatherLogNames() END");

    }

    public static void makeLogNamesPublic( Vector v)
    {
        Logs.LogNames = new String[v.size()];
        v.copyInto(Logs.LogNames);
    }

   /**
    * This function returns the files contained in the specified directory
    * @param dir Strubg
    * @return the files in directory
    */
    @SuppressWarnings("unchecked")
    public String[] getDirFiles( String dir ){
        String [] fileNames = {""};
        logger.debug("getDirFiles() BEGIN");

        FilenameFilter filter = new FilenameFilter(){
            public boolean accept( File dir, String name){
                return Pattern.matches(".*\\.(log|Director)", name);
            }
        };
        try{

           File directory = new File( dir );
           
           if( dir.isEmpty())
               return fileNames;

           File [] files = directory.listFiles(filter);

            
           Arrays.sort( files, new Comparator(){
           
            public int compare( Object o1, Object o2){
                
                File filea = (File)o1;
                File fileb = (File)o2;
                if ( filea.lastModified() > fileb.lastModified()) {
                    return -1;
                } else if (filea.lastModified() < fileb.lastModified()) {
                    return +1;
                } else {
                    return 0;
                }
            }
           });
        fileNames = new String[files.length];
        for( int i=0; i < files.length; i++){
            fileNames[i] = files[i].getPath();
            logger.debug("getDirFiles()-fileNames:" + fileNames[i]);
        }

        }catch( SecurityException e ){
           logger.error(e.getMessage().toString());
        }
        logger.debug("getDirFiles() END");
        return fileNames;
    }
    /**
     * Creates box, log file name and button.
     * @param logName String
     * @param btn
     */
    public void createLogBox( String logName, JButton btn ){
 
        JPanel panel = new JPanel();
        //left panel
        panel.setLayout(new GridLayout(1,2));
        JPanel log1Panel = new JPanel();
        BoxLayout log1Lay= new BoxLayout( log1Panel, BoxLayout.X_AXIS);
        log1Panel.setLayout(log1Lay);
        JLabel l = new JLabel("   ");
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel logLabel = new JLabel(logName);
        logLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        log1Panel.add(l);
        log1Panel.add(logLabel);
        panel.add(log1Panel);

        //rigth panel
        JPanel log2Panel = new JPanel();
        BoxLayout log2lay = new BoxLayout( log2Panel, BoxLayout.X_AXIS);
        log2Panel.setLayout(log2lay);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        log2Panel.add(btn);
        panel.add(log2Panel);

        //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        btn.addActionListener(this);

        add(panel);

    }

    public void actionPerformed(ActionEvent e){

        StartStopProc proc = new StartStopProc();

        for(int i=0; i < logNames.length; i++){
            if( ( e.getSource() == btns[i] ) && i == 0 ){
                logger.debug("Starting process: " + "notepad" + " " + tomcat_full_logfile_name );
                if( tomcat_full_logfile_name != "" )
                    proc.OpenProc( "notepad" + " " + tomcat_full_logfile_name );
                break;
            }
            else if( ( e.getSource() == btns[i]) && i == 1 ){
                logger.debug("Starting process: " + "notepad" + " " + printaw_prologfile_name );
                if( printaw_prologfile_name != "" )
                    proc.OpenProc( "notepad" + " " + printaw_prologfile_name );
                break;
            }
            else if( ( e.getSource() == btns[i]) && i == 2 ){
                logger.debug("Starting process: " + "notepad" + " " + printaw_statlogfile_name );
                if( printaw_statlogfile_name != "" )
                    proc.OpenProc( "notepad" + " " + printaw_statlogfile_name );
                break;
            }
            else if( ( e.getSource() == btns[i]) && i == 3 ){
                logger.debug("Starting process: " + "notepad" + " " + pds_logfile_name );
                if( pds_logfile_name != "" )
                    proc.OpenProc( "notepad" + " " + pds_logfile_name );
                break;
            }
            else if( ( e.getSource() == btns[i]) && i == 4 ){
                logger.debug("Starting process: " + "notepad" + " " + pad_logfile_name );
                if( pad_logfile_name != "" )
                    proc.OpenProc( "notepad" + " " + pad_logfile_name );
                break;
            }
            else if( ( e.getSource() == btns[i]) && i == 5 ){
                logger.debug("Starting process: " + "notepad" + " " + pdg_logfile_name );
                if( pdg_logfile_name != "" )
                    proc.OpenProc( "notepad" + " " + pdg_logfile_name );
                break;
            }


        }
       
    }

}

