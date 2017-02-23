/**
 * <dl>
 * <dt>Purpose: The CPSDashboard is a tool that allows easy access to some of the most
 * common administration task of PrinterOn system, such as start/stop processes, view
 * log files and documentation, components version and configuration.
 *
 * </dl>
 * @version $Date: 2009/May/01
 * @author Ramses A. Garcia
 */

//package CPSDashboard;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.BitSet;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;




public class CPSDashboard extends JPanel implements ActionListener
{

    private JMenuItem exitMI, /*lookandfeelMI, saveConfMI,*/ trayIconMI, aboutMI, refreshMI;
    //private JMenuItem performanceCB;
    //private JRadioButtonMenuItem /*lnfJavaRB, lnfWinRB, lnfMotifRB, lnfDefaultRB;*/
    static CPSDashboard dashboard;
    public static JFrame frame;
    public static String lnfNameGen;
    public static Logger logger;
    private final static String DASH_FILE_NAME = "DashRunning.dsh";
    private final static String TRAY_FILE_NAME = "TrayRunning.dsh";
    
    Logs logObj;


    /**
     * Constructs CPSDashboard. It takes no parameters. It creates the border,
     * layout. menu bar and tabs.
     */

    CPSDashboard(){

        setLayout(new BorderLayout());
        setBorder(new EtchedBorder());

        //Creating Logs object. It is done this way because we may need to call some function
        // from Logs object during a refresh.
        logObj = new Logs();

        add(createMenuBar(), BorderLayout.NORTH);
        add(createTabbedPane(),BorderLayout.CENTER);
      
    }

    /**
     * Creates the application Tabs with each of the corresponding apps objects
     * @return JTabbedPane
     */
    private JTabbedPane createTabbedPane(){

        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.setFont(new Font("serif", Font.PLAIN, 12));

        tabbedPane.addTab("Status", new Status() );
        tabbedPane.addTab("Components", new POComponents());
        tabbedPane.addTab("Configuration", new Config());
        //tabbedPane.addTab("Logs", new Logs());
        tabbedPane.addTab("Logs", logObj );
        tabbedPane.addTab("Documentation", new Docs());
        tabbedPane.addTab("Support", new Support());
        
        //tabbedPane.addTab("Performance", new JLabel("Panel 6"));
        ChangeListener changeListener = new ChangeListener() {
              public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                //logger.debug("Tab changed to index: " + index);
                if( index == 0 )
                    WatchProcesses.suspended = false;
                else
                    WatchProcesses.suspended = true;
              }
            };
         tabbedPane.addChangeListener(changeListener);

        return tabbedPane;
    }

    /**
     * Creates frame menu bar.
     * @return JMenuBar
     */

    private JMenuBar createMenuBar() {

        CPSDashConfig cfg = CPSDashConfig.getCPSDashConfigInstance();
        String mode = cfg.getLookAndFeelMode();
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = (JMenu) menuBar.add(new JMenu("File"));
       // JMenu options = (JMenu) menuBar.add(new JMenu("Options"));
       // JMenu tools = (JMenu)menuBar.add( new JMenu("Tools"));
        JMenu help = (JMenu) menuBar.add(new JMenu("Help"));

        //saveConfMI = (JMenuItem) file.add(new JMenuItem("Save Configuration"));
       // saveConfMI.addActionListener(this);

        refreshMI = (JMenuItem)file.add(new JMenuItem("Refresh"));
        refreshMI.addActionListener(this);
        
        trayIconMI = (JMenuItem) file.add(new JMenuItem("Tray Icon"));
        trayIconMI.addActionListener(this);

        file.addSeparator();
        
        exitMI = (JMenuItem) file.add(new JMenuItem("Exit"));
        exitMI.addActionListener(this);

        ButtonGroup bg = new ButtonGroup();

        //lookandfeelMI = (JMenuItem) options.add(new JMenu("Look and Feel"));
           // lnfJavaRB = (JRadioButtonMenuItem) lookandfeelMI.add(new JRadioButtonMenuItem("Java", mode.equals("Java")));
          //  lnfWinRB  = (JRadioButtonMenuItem) lookandfeelMI.add(new JRadioButtonMenuItem("Windows", mode.equals("Windows")));
          //  lnfMotifRB= (JRadioButtonMenuItem) lookandfeelMI.add(new JRadioButtonMenuItem("Motif", mode.equals("Motif")));
            //lnfDefaultRB= (JRadioButtonMenuItem) lookandfeelMI.add(new JRadioButtonMenuItem("Default", true));
           // bg.add(lnfJavaRB);
           // bg.add(lnfWinRB);
          //  bg.add(lnfMotifRB);
          //  bg.add(lnfDefaultRB);
       // lookandfeelMI.addActionListener(this);
     //   lnfJavaRB.addActionListener(this);
     //   lnfWinRB.addActionListener(this);
     //   lnfMotifRB.addActionListener(this);

        aboutMI = (JMenuItem) help.add(new JMenuItem("About"));
        aboutMI.addActionListener(this);

        //lnfDefaultRB.addActionListener(this);
        //performanceCB = (JCheckBoxMenuItem) options.add( new JCheckBoxMenuItem("Performance", false));
        //performanceCB.addActionListener(this);

        return menuBar;
    }

    /**
     * This function is called when the application is restored from an iconized state.
     */
    public void start() {
        WatchProcesses.suspended = false;
    }

    /**
     * This function is called when the application gets minimized. The process
     * monitoring thread gets stopped and no CPU is used.
     */

    public void stop() {
        WatchProcesses.suspended = true;
    }

    /**
     * Main menu event handler.
     * @param ActionEvent e
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(exitMI)) {
            this.removeDashFile(CPSDashConfig.getCPSDashConfigInstance().getCPSDataPath());
            System.exit(0);
        }/*else if(e.getSource().equals(lnfJavaRB)){
            lnfNameGen = "Java";
            setLookAndFeel("Java");
        }else if(e.getSource().equals(lnfWinRB)){
            lnfNameGen = "Windows";
            setLookAndFeel("Windows");
        }else if(e.getSource().equals(lnfMotifRB)){
            lnfNameGen = "Motif";
            setLookAndFeel("Motif");
        }else if(e.getSource().equals(saveConfMI)){
         CPSDashConfig cfg = CPSDashConfig.getCPSDashConfigInstance();
         cfg.setLookAndFeelMode(lnfNameGen);
         cfg.writeConfigFile();
        }*/else if(e.getSource().equals(trayIconMI)){
              try{

                    Runtime runtime = Runtime.getRuntime();
                    CPSDashConfig cfg = CPSDashConfig.getCPSDashConfigInstance();
                    String dashpath = cfg.getCPSDashPath();                    
                    if( ! isTrayRunning( cfg.getCPSDataPath() ) ){
                        String cmd = "java -jar " + "\"" + dashpath + "\\CPSDashTray.jar" + "\"";
                        Process proc = runtime.exec( cmd );
                        //proc.waitFor();
                        //Thread.sleep(1000);
                        //exitVal = proc.exitValue();
                    }
                    this.removeDashFile(CPSDashConfig.getCPSDashConfigInstance().getCPSDataPath());
                    System.exit(0);

                }catch( IOException ioe){
                    //logger.error(ioe.getMessage().toString());
                    //res = false;
                    //System.out.println(ioe.getMessage().toString());
                }


        }else if( e.getSource().equals(aboutMI)){
            JOptionPane.showMessageDialog(null, "CPS Dashboard " + CPSDashConfig.getCPSDashConfigInstance().getCPSDashVersion(), "CPSDashboard", JOptionPane.INFORMATION_MESSAGE);
        }else if(e.getSource().equals(refreshMI)){
            logObj.refreshLogs();
        }
    }

    /**
     * This function sets the look and feel of the CPSDahsboard User Interface
     * There are three styles: Java, Windows and Motif.
     * @param String Name. Look and feel mode
     */
    private static void setLookAndFeel(String Name){

      String lnfName = null;
/*
      if( Name.equals("Java"))
          lnfName = CPSDashConstants.LNF_JAVA;
      else if( Name.equals("Motif"))
          lnfName = CPSDashConstants.LNF_MOTIF;
      else if( Name.equals("Windows"))
          lnfName = CPSDashConstants.LNF_WINDOWS;
      else*/
          lnfName = CPSDashConstants.LNF_JAVA;

      try {
        UIManager.setLookAndFeel(lnfName);
        SwingUtilities.updateComponentTreeUI(frame);

        //frame.repaint();
      } catch (Exception evt) {
        JOptionPane.showMessageDialog(null, "setLookAndFeel didn't work: " + evt, "UI Failure",
            JOptionPane.INFORMATION_MESSAGE);
        //previousButton.setSelected(true); // reset the GUI to agree
      }

    }
    /**
     * This function creates the CPSDashboard flag file containing the
     * PID of the process.
     * @param dashpath String
     * @return
     */
    public static boolean createDashFile( String dashpath ){

       boolean res= false;
       String pid1, pid  = "";

       File trayFile = new File( dashpath + "\\conf\\" + DASH_FILE_NAME );
       pid1 = ManagementFactory.getRuntimeMXBean().getName();
       pid = pid1.substring(0, pid1.indexOf("@"));

       logger.debug("pid: " + pid);

       try{
            res = trayFile.createNewFile();
            FileWriter fw = new FileWriter(trayFile);
            fw.write(pid);
            fw.close();
       }catch( IOException e ){
            logger.debug("Unable to create file:" + DASH_FILE_NAME);
            logger.debug(e.getMessage().toString());
       }

       return res;

   }

    /**
     * This function opens the CPSDashboard flag file, gets the
     * process PID and checks whether it is actually running in
     * memory, returns true if so, otherwise deletes the file
     * and returns false.
     * @param dashpath String
     * @return boolean
     */
      public static boolean isDashboardRunning(String dashpath){

       boolean res;
       String line="";

       File dashFile = new File( dashpath + "\\conf\\" + DASH_FILE_NAME );

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
            logger.debug("Unable to read file:" + DASH_FILE_NAME);
            logger.debug(e.getMessage().toString());
       }

       res = checkPID( line );

       if( res == false && dashFile.exists() ){
           boolean del = dashFile.delete();
           if( del )
               logger.debug("File:" + DASH_FILE_NAME + " successfully deleted");
           else
               logger.debug("File:" + DASH_FILE_NAME + " was not deleted");
       }

       return ( res );

   }

      /**
       * It checks whether the process with PID is actally running in memory.
       * @param pid
       * @return boolean
       */
   public static boolean checkPID( String pid ){

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


   /**
    * This functions removes the CPSDashboard flat file
    * @param dashpath String
    */
   public static void removeDashFile( String dashpath ){
       File dashFile = new File( dashpath + "\\conf\\" + DASH_FILE_NAME );

       if( dashFile.exists()){
           logger.debug("removeDashFile - file:" + dashpath + "\\conf\\" + DASH_FILE_NAME + " exists, trying to delete it");
           boolean del = dashFile.delete();
           if( del )
               logger.debug("File:" + DASH_FILE_NAME + " successfully deleted");
           else
               logger.debug("File:" + DASH_FILE_NAME + " was not deleted");

       }

   }

   /**
    * This function open the CPSDash tray if exists and gets the process
    * PID and validates whether the process is actaully running or not.
    * @param dashpath
    * @return boolean
    */
   public boolean isTrayRunning( String dashpath ){

       boolean res;

       File trayFile = new File( dashpath + "\\conf\\" + TRAY_FILE_NAME );

       if( ! trayFile.exists() )
           return false;

       String line="";
       try{
        FileReader fr = new FileReader(trayFile);
        BufferedReader br = new BufferedReader(fr);
        line = br.readLine();
        logger.debug("pid from file:" + line );
        br.close();
        fr.close();
       }catch( IOException e){
            logger.debug("Unable to read file:" + TRAY_FILE_NAME);
            logger.debug(e.getMessage().toString());
       }

       res = checkPID( line );

       if( res == false && trayFile.exists() ){
           boolean del = trayFile.delete();
           if( del )
               logger.debug("File:" + TRAY_FILE_NAME + " successfully deleted");
           else
               logger.debug("File:" + TRAY_FILE_NAME + " was not deleted");
       }
       
       return ( res );

   }

   /**
    * It just sets the application logger, so it can be used here and through out
    * the CPSDashboard application.
    */
public static void setLogger(){
      CPSDashLogger log = new CPSDashLogger("CPSDashboard");
      log.configLogger();
      logger = log.getCPSDashLogger();
}

    /**
     * Main entry point of CPS Dashboard.
     * @param String args. Arguments
     */
    public static void main(String args[]) {

      int WIDTH = 500, HEIGHT = 300;
      int l_width = 200, l_height = 100;
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
      String procNames[] = {"tomcat", "DirectorService", "ProcessingServer", "StatusServer", "PonServices","GatewayService" };
      final String dashpath;
      final String datapath;

      //Configuring log process, only one object for the whole application
      setLogger();

      //We need to know where the CPSDashboard installation path and check for the flag file
      //which will tell us whether there is an instance already running.
      RegistryAccess regA = new RegistryAccess();
      CPSDashComponent cps = regA.getRegistryComponent(CPSDashConstants.CPSDASH_COMP);
      dashpath = cps.getCompPath();
      datapath = cps.getDataPath();
      
      if( isDashboardRunning( datapath ) ){
          System.exit(0);
      }

      JWindow jwin = new JWindow();
      jwin.getContentPane().add( new JLabel("Loading CPS Dashboard ...", SwingConstants.CENTER));
      jwin.setLocation(d.width/2 - l_width/2, d.height/2 - l_height/2);
      jwin.setSize( l_width, l_height );
      jwin.setVisible(true);

        logger.info("Starting CPSDashboard Application");

        //Getting PrinterOn software info from registry
        
        Vector<CPSDashComponent> comps = regA.getAllPORegistryComponents();
        RegistryAccess.setAllComponents(comps);

        //Reading CPS Dashboard configuration file
        CPSDashConfig confobj = new CPSDashConfig();
        CPSDashConfig.setCPSDashConfigInstance(confobj);
        confobj.readCPSDashConfig( confobj.getCPSDataPath() + "\\conf\\" + CPSDashConstants.CPSDASH_CONFIG_FILE);

        //Starting monitoring processes thread
        WatchProcesses wps = new WatchProcesses(procNames);
        BitSet bs = wps.getBitsActProcs();
        WatchProcesses.setBitsActProcs(bs);
        WatchProcesses.setProcsInfo(procNames);
        Thread wpsthr = new Thread(wps);
        wpsthr.start();

        frame = new JFrame("CPS Dashboard");
        dashboard = new CPSDashboard();

        frame.getAccessibleContext().setAccessibleDescription("Central Print Services Dashboard");        
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(dashboard, BorderLayout.CENTER);
        frame.setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);
        frame.setSize(WIDTH, HEIGHT);
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon( confobj.getCPSDashPath() + "\\images\\cps_icon.gif").getImage() );
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logger.info("Exiting CPSDashboard Application");
                removeDashFile( datapath );
                System.exit(0);
            }
            public void windowDeiconified(WindowEvent e) {
                if (dashboard != null) { dashboard.start(); }
            }
            public void windowIconified(WindowEvent e) {
                if (dashboard != null) { dashboard.stop(); }
            }
        });
        setLookAndFeel(confobj.getLookAndFeelMode());
        frame.validate();
        frame.repaint();
        jwin.setVisible(false);
        jwin.dispose();
        frame.setVisible(true);
        createDashFile( datapath );
    }

}
