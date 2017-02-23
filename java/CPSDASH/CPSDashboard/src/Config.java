import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;

/**
 * This class gives functionality to the configuration tab in CPSDashboard.
 * It calls PrinterOn configuration utilities.
 * @author garciar
 */
public class Config extends JPanel implements ActionListener{

    String confNames[] = {"PDS Configuration", "PAS Configuration", "CPS Configuration", "PAD Configuration", "PON Test", "PDG Configuration"};
    //String confEXENames[] = {"DirectorServiceController.exe", "PasConfig.exe", " ", "jPONTest.jar" };
    String confEXENames[] = {"PrintDeliveryStation.exe", "PasConfig.exe", " ", "notepad.exe", "jPONTest.jar", "PrintDeliveryGateway.exe" };
    JButton btns[] = new JButton[confNames.length];
    String confPath = "";
    CPSDashComponent comp;
    String pdsPath, pasPath, cpsPath, ponPath, padPath, pdgPath;
    File ponDir;
    public Logger logger = CPSDashLogger.getCPSDashLogger();

    /**
     * This constructor creates the layout and boxes for the configuration tab pane.
     * It also gets the Installation path for the four configuration tools, PAS, PDS, CPS and PONTest.
     *
     */
    Config(){
       

        setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));

        for( int i=0; i < confNames.length; i++){
            btns[i] = new JButton("Open");
            createDocBox( confNames[i], btns[i] );
        }

        comp = RegistryAccess.searchComp(CPSDashConstants.PDS_COMP);
        if( comp != null ){
            pdsPath = comp.getCompPath();
            logger.debug( "pdsPath:" + pdsPath );
        }else{ btns[0].setEnabled(false);}

        comp = RegistryAccess.searchComp(CPSDashConstants.PDG_COMP);
        if( comp != null ){
            pdgPath = comp.getCompPath();
            logger.debug( "pdgPath:" + pdgPath );
        }else{ btns[5].setEnabled(false);}

        comp = RegistryAccess.searchComp(CPSDashConstants.PRINTANYWHERE_COMP);
        if( comp != null ){
            pasPath = comp.getCompPath();
            logger.debug( "pasPath:" + pasPath );
        }else{ btns[1].setEnabled(false);}

        comp = RegistryAccess.searchComp(CPSDashConstants.CPS_COMP);
        if( comp != null ){
            cpsPath = comp.getCompPath();
            logger.debug( "cpsPath:" + cpsPath );
        }else{ btns[2].setEnabled(false);}

        comp = RegistryAccess.searchComp(CPSDashConstants.PONAD_COMP);
        if( comp != null ){
            //padPath = comp.getCompPath();
            RegistryAccess rg = new RegistryAccess();
            padPath = rg.getRegistryValue("HKEY_LOCAL_MACHINE\\SOFTWARE\\PrinterOn Corporation\\PrinterOn Advertiser", "AppDataPath");        	
            logger.debug( "padPath:" + padPath );
        }else{ btns[3].setEnabled(false);}
        
        comp = RegistryAccess.searchComp(CPSDashConstants.CPSDASH_COMP);
        if( comp != null ){
            ponPath = comp.getCompPath();
            ponPath = ponPath.replace("CPSDashboard", "Utilities");
            ponDir = new File(ponPath);
            if( !ponDir.exists() )
                btns[4].setEnabled(false);
            logger.debug( "ponPath:" + ponPath );
        }

              

    }

    /**
     * This function creates the boxes and buttons for each one of the
     * @param confName String
     * @param btn JButton
     */
    public void createDocBox( String confName, JButton btn ){

        JPanel panel = new JPanel();
        //left panel
        panel.setLayout(new GridLayout(1,2));
        JPanel conf1Panel = new JPanel();
        BoxLayout conf1Lay= new BoxLayout( conf1Panel, BoxLayout.X_AXIS);
        conf1Panel.setLayout(conf1Lay);
        JLabel l = new JLabel("   ");
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel confLabel = new JLabel(confName);
        confLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        conf1Panel.add(l);
        conf1Panel.add(confLabel);
        panel.add(conf1Panel);

        //rigth panel
        JPanel conf2Panel = new JPanel();
        BoxLayout conf2lay = new BoxLayout( conf2Panel, BoxLayout.X_AXIS);
        conf2Panel.setLayout(conf2lay);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        conf2Panel.add(btn);
        panel.add(conf2Panel);

        //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        btn.addActionListener(this);

        add(panel);

    }


    /**
     * Event handler for Config tab pane. It launches the configuration tools.
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e){

        StartStopProc proc = new StartStopProc();

        for(int i=0; i < confNames.length; i++){
            if( ( e.getSource() == btns[i] ) && i == 0 ){
                logger.debug("Starting process: " + confEXENames[i] );
                proc.startProc( pdsPath + "\\" + confEXENames[i] );
               /* try{
                    Thread.sleep(3000);
                }catch( InterruptedException exc ){
                    logger.error("Unable to wait for second PDS launch reason: " + exc.getMessage().toString());
                }
                proc.startProc( pdsPath + "\\" + confEXENames[i] );*/
                break;
            }
            else if( ( e.getSource() == btns[i] ) && i == 1 ){
                logger.debug("Starting process: " + confEXENames[i] );
                proc.startProc( pasPath + "\\" + confEXENames[i] );
                break;
            }
            else if( ( e.getSource() == btns[i] ) && i == 2 ){
                logger.debug("Starting process: " + confEXENames[i] );
                if( Desktop.isDesktopSupported() ){
                    try{
                        Desktop desktop = Desktop.getDesktop();
                        desktop.browse(new URI("http://localhost/cps/admin") );
                    }catch( Exception ex){
                        logger.debug( ex.getMessage().toString());
                    }
                }           
                break;
            }
            else if( ( e.getSource() == btns[i] ) && i == 3 ){
                logger.debug("Starting process: " + confEXENames[i] );
                String configFile = padPath + "\\Config\\" + CPSDashConstants.PAD_CONFIG_FILENAME;  
                proc.startProc( confEXENames[i], configFile );
                break;
            }            
            else if( ( e.getSource() == btns[i] ) && i == 4 ){
                logger.debug("Starting process: " + confEXENames[i] );
                logger.debug("Path: " + ponPath );
               
                try{
                    Process p = Runtime.getRuntime().exec( "java -jar " + "\"" + ponPath + "\\jPONTest.jar" + "\"" );
                        p.getErrorStream().close();
                        p.getInputStream().close();
                        p.getOutputStream().close();
                }catch( java.io.IOException io){
                    logger.debug(io.getMessage().toString());
                }

                break;
            }else if( ( e.getSource() == btns[i] ) && i == 5 ){
                logger.debug("Starting process: " + confEXENames[i] );
                proc.startProc( pdgPath + "\\" + confEXENames[i] );
            }

        }
    }

}