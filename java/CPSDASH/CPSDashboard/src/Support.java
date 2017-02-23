import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;



class Support extends JPanel implements ActionListener{

    final int MAX_LOG_FILES  = 4;
    JFileChooser fc;
    public Logger logger = CPSDashLogger.getCPSDashLogger();
    JButton zipBtn;
    JButton cpsBtn;
    JButton logBtn1, logBtn2;
    JButton proxBtn1, proxBtn2;
    File zipFile, LicenseFile;
    String [] fileNames = { "" };


    Support(){
        
        zipBtn = new JButton("   ZIP   ");
        cpsBtn = new JButton("Import");
//        Dimension dim1 = cpsBtn.getPreferredSize();
//        logger.debug("Button height:" + dim.getHeight() + " width:" + dim.getWidth());
//        Dimension dim = new Dimension();
//        dim.setSize(71.0, 26.0);
//        zipBtn.setPreferredSize(new Dimension(230, 26));

//        logBtn1 = new JButton("Enable");
//        logBtn2 = new JButton("Disable");
//        proxBtn1 = new JButton("Enable");
//        proxBtn2 = new JButton("Disable");
        setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
        createBox("Zip Support Log Files", zipBtn);
        createBox("ReImport CPS License File", cpsBtn);
        createEmptyBox();
        createEmptyBox();
        //createBox2("Enable/Disable logging", logBtn1, logBtn2);
        //createBox2("Enable/Disable proxy", proxBtn1, proxBtn2);


    }

    /**
     * This function creates an empty box.
     *
     */
    public void createEmptyBox( ){

        JPanel panel = new JPanel();
        //left panel
        panel.setLayout(new GridLayout(1,2));
        JPanel sup1Panel = new JPanel();
        BoxLayout sup1Lay= new BoxLayout( sup1Panel, BoxLayout.X_AXIS);
        sup1Panel.setLayout(sup1Lay);
        JLabel l = new JLabel("   ");
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        sup1Panel.add(l);
        panel.add(sup1Panel);      
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        add(panel);

    }

    /**
     * This function creates a box, document title and button to launch the document.
     * @param docName String document name
     * @param btn
     */
    public void createBox( String Title, JButton btn ){

        JPanel panel = new JPanel();
        //left panel
        panel.setLayout(new GridLayout(1,2));
        JPanel sup1Panel = new JPanel();
        BoxLayout sup1Lay= new BoxLayout( sup1Panel, BoxLayout.X_AXIS);
        sup1Panel.setLayout(sup1Lay);
        JLabel l = new JLabel("   ");
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel supLabel = new JLabel(Title);
        supLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sup1Panel.add(l);
        sup1Panel.add(supLabel);
        panel.add(sup1Panel);

        //rigth panel
        JPanel sup2Panel = new JPanel();
        BoxLayout sup2lay = new BoxLayout( sup2Panel, BoxLayout.X_AXIS);
        sup2Panel.setLayout(sup2lay);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sup2Panel.add(btn);
        panel.add(sup2Panel);

        //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        btn.addActionListener(this);

        add(panel);

    }

    /**
     * This function creates a box, document title and button to launch the document.
     * @param docName String document name
     * @param btn
     */
    public void createBox2( String Title, JButton btn1, JButton btn2 ){

        JPanel panel = new JPanel();
        //left panel
        panel.setLayout(new GridLayout(1,2));
        JPanel sup1Panel = new JPanel();
        BoxLayout sup1Lay= new BoxLayout( sup1Panel, BoxLayout.X_AXIS);
        sup1Panel.setLayout(sup1Lay);
        JLabel l = new JLabel("   ");
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel supLabel = new JLabel(Title);
        supLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sup1Panel.add(l);
        sup1Panel.add(supLabel);
        panel.add(sup1Panel);

        //rigth panel
        JPanel sup2Panel = new JPanel();
        BoxLayout sup2lay = new BoxLayout( sup2Panel, BoxLayout.X_AXIS);
        sup2Panel.setLayout(sup2lay);
        btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
        sup2Panel.add(btn1);
        sup2Panel.add(btn2);
        panel.add(sup2Panel);

        //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        btn1.addActionListener(this);
        btn2.addActionListener(this);

        add(panel);

    }


 public boolean zipFiles(){

    logger.debug("zipFiles() - BEGIN");

    
   // These are the files to include in the ZIP file
    //String[] filenames = new String[]{"filename1", "filename2"};

    // Create a buffer for reading the files
    byte[] buf = new byte[1024];


    try {
        // Create the ZIP file
        //String outFilename = zipFile;
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));

        // Compress the files
        for (int i=0; i<fileNames.length; i++) {
            logger.debug("zipFiles: zipping file:" + fileNames[i]);
            FileInputStream in = new FileInputStream(fileNames[i]);
            File f = new File(fileNames[i]);
            
            // Add ZIP entry to output stream.
            //out.putNextEntry(new ZipEntry(fileNames[i]));
            out.putNextEntry(new ZipEntry(f.getName()));

            // Transfer bytes from the file to the ZIP file
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
                //logger.debug("len:" + len);
            }
            // Complete the entry
            out.closeEntry();
            in.close();
        }
        // Complete the ZIP file
        out.close();
   } catch (IOException e) {
        logger.error("IOEception ocurred: " + e.getMessage().toString());
        JOptionPane.showMessageDialog(null, e.getMessage().toString(), "Zip Log File", JOptionPane.INFORMATION_MESSAGE);
   }
    return true;
 }

 public void gatherLogFileNames(){

     fileNames = Logs.LogNames;
     for( int i=0; i < fileNames.length; i++)
         logger.debug("gatherLogFileNames()-" + fileNames[i]);
     
 }
     public void actionPerformed(ActionEvent e){

        //StartStopProc proc = new StartStopProc();

        if( e.getSource() == zipBtn  ){

            gatherLogFileNames();
            if( fileNames.length == 0){
                JOptionPane.showMessageDialog(null, "No log files found", "Zip Log Files", JOptionPane.INFORMATION_MESSAGE);
            }

            else{
                logger.debug("Calling File Chooser");
                fc = new JFileChooser();
                //fc.setCurrentDirectory(new File(RegistryAccess.searchComp(CPSDashConstants.CPSDASH_COMP).getCompPath()));
                fc.setDialogTitle("PrinterOn ZIP log file location");
                fc.setSelectedFile(new File("printerOnLogs.zip"));
                int ret = fc.showSaveDialog(this);
                zipFile = fc.getSelectedFile();
                logger.debug("zipLog File:" + zipFile.getPath());
                if( ret == JFileChooser.APPROVE_OPTION){
                    zipFiles();
                }
            }
            
        }else if( e.getSource() == cpsBtn ){
                fc = new JFileChooser();
                fc.setDialogTitle("PrinerOn license file location");
                fc.setSelectedFile(new File("PrinterOnConfig.txt"));
                FileFilter filter = new FileFilter(){
                        public boolean accept (File name) {
                            return name.getName().toLowerCase().endsWith(".txt");
                        }
                        public String getDescription(){
                            return "*.txt";
                        }
                };

                fc.setFileFilter(filter);
                int ret = fc.showOpenDialog(this);
                LicenseFile = fc.getSelectedFile();
                logger.debug("PrinterOn license File:" + LicenseFile.getPath());
                if( ret == JFileChooser.APPROVE_OPTION){
                    reimportCPSLicense();
                }
        }

    }
     public void reimportCPSLicense(){
         String cpsPath = RegistryAccess.searchComp(CPSDashConstants.CPS_COMP).getCompPath();
         String jarPath = cpsPath + "\\cps\\WEB-INF\\lib";
         String jar1 = jarPath + "\\jdom.jar";
         String jar2 = jarPath + "\\xerces.jar";
         String jar3 = jarPath + "\\log4j-1.2.9.jar";
         String jar4 = jarPath + "\\HTTPClient.jar";
         String jar5 = jarPath + "\\commons-httpclient-3.1.jar";
         String jar6 = jarPath + "\\commons-codec-1.3.jar";
         String jars = "\"" + jar1 + ";" + jar2 + ";" + jar3 + ";" + jar4 + ";" + jar5 + ";" + jar6 + "\"";
         String cfg = "\"" + RegistryAccess.searchComp(CPSDashConstants.CPSDASH_COMP).getCompPath() + "\\" + "CpsConfig.jar" + "\" " + "\"com.printeron.cps.tools.CpsUpdater\"";
         String configPath = "\"" + cpsPath + "\\cps" + "\"";
         String licenseFile = "\"" + LicenseFile.getPath() + "\"";
         String sourceWar = "\"" + cpsPath + "\\cps.war" + "\"";
         String cmd = /*"\"" +*/ /*"java -classpath " +*/ jars + ";" + cfg + " " + configPath + " " + licenseFile + " " + sourceWar;// + "\"";
         logger.debug( "reimportCPSLicense()- cmd:" + cmd );
         
         try{

           /* Process process = Runtime.getRuntime().exec(cmd);
            InputStreamReader reader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedreader = new BufferedReader(reader);

            while( (line = bufferedreader.readLine() ) != null ){
                logger.debug(line);
            }    */
        	 
             StartStopProc proc = new StartStopProc();
             String procName = "java";
             proc.JavaExec(cmd);            
           
         }catch( Exception e){
           logger.error(e.getMessage().toString());
         }

     }

}