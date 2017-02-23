/**
 * Status.java
*/


import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class displays the status of each of the PrinterOn components. If any is not
 * installed the Start/Stop button gets disabled.
 * @author garciar
 */

public class Status extends JPanel implements ActionListener
{

    String procNames[] = {"tomcat", "DirectorService", "ProcessingServer", "StatusServer", "PonServices", "GatewayService" };
    String CompNames[] = {"Tomcat", "Print Delivery Station", "PrintAnywhere Processing", "PrintAnywhere Status", "PrinterOn Advertiser", "Print Delivery Gateway" };
    String blkNames[] = {"tomcatBlkThr", "PDSBlkThr", "PAWProBlkThr", "PAWStatBlkThr", "PonAdBlkThr" };
    JButton btns[] = new JButton[procNames.length];
    Blinker blks[] = new Blinker[procNames.length];
    Thread blksThrs[] = new Thread[procNames.length];
    JLabel lbls[] = new JLabel[procNames.length];
    public Logger logger = CPSDashLogger.getCPSDashLogger();

Status(){

    CPSDashComponent comp;
    setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));

    for( int i=0; i < procNames.length; i++){
        btns[i] = new JButton("Start/Stop");
        lbls[i] = new JLabel("");
        blks[i] = new Blinker(procNames[i], lbls[i]);
        blksThrs[i] = new Thread(blks[i]);
        createProcBox( CompNames[i], btns[i], blksThrs[i], lbls[i]);
    }

    comp = RegistryAccess.searchComp(CPSDashConstants.TOMCAT_COMP);
    if( comp == null ){
        btns[0].setEnabled(false);
    }

    comp = RegistryAccess.searchComp(CPSDashConstants.PDS_COMP);
    if( comp == null ){
        btns[1].setEnabled(false);
    }
    
    comp = RegistryAccess.searchComp(CPSDashConstants.PRINTANYWHERE_COMP);
    if( comp == null ){
        btns[2].setEnabled(false);
        btns[3].setEnabled(false);
    }
    
    comp = RegistryAccess.searchComp(CPSDashConstants.PONAD_COMP);
    if( comp == null ){
        btns[4].setEnabled(false);
    }

    comp = RegistryAccess.searchComp(CPSDashConstants.PDG_COMP);
    if( comp == null ){
        btns[5].setEnabled(false);
    }
    
}

/**
 * Currently not used.
 * @param word
 * @return String
 */
public String capWord( String word ){

    String firstLetter = word.substring(0,1);  // Get first letter
    String remainder   = word.substring(1);    // Get remainder of word.
    String capitalized = firstLetter.toUpperCase() + remainder.toLowerCase();

    return capitalized;
}

/**
 * This function creates a box, status icon, process name and Start/Stop button.
 * @param procName String
 * @param btn JButton
 * @param procThr Thread which monitors the process.
 * @param label JLabel
 */
public void createProcBox( String procName, JButton btn, Thread procThr, JLabel label ){

    JPanel panel = new JPanel();
    
    GridLayout gl = new GridLayout(1,3);
    panel.setLayout(gl);

    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    panel.add(p1);
    panel.add(p2);
    panel.add(p3);
    GridBagLayout gbl1 = new GridBagLayout();
    GridBagLayout gbl2 = new GridBagLayout();
    GridBagLayout gbl3 = new GridBagLayout();
    p1.setLayout(gbl1);
    p2.setLayout(gbl2);
    p3.setLayout(gbl3);
    p1.setPreferredSize(new Dimension(50,0));
    
    //panel.setLayout(gbl);
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel procLabel = new JLabel(procName);

    //gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 100;
    gbc.weighty = 100;
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbl1.setConstraints(label, gbc);
    p1.add(label);

    //gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.weightx =100;
    gbc.weighty = 100;
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 10;
    gbc.gridheight = 1;
    gbl2.setConstraints(procLabel, gbc);
    p2.add(procLabel);

    //gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx =100;
    gbc.weighty = 100;
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 7;
    gbc.gridheight = 1;
    gbl3.setConstraints(btn, gbc);
    p3.add(btn);

    //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setBorder(BorderFactory.createRaisedBevelBorder());

    btn.addActionListener(this);
    procThr.start();
    add(panel);

    
}

public void actionPerformed(ActionEvent e){
       
    StartStopProc proc = new StartStopProc();
    
    for(int i=0; i < procNames.length; i++){

        if( ( e.getSource() == btns[i] ) && i == 0 ){

            if( ! blks[i].isProcessAlive()){
                logger.debug("Starting Tomcat Service");
                proc.startService("Apache Tomcat", procNames[i] );
            }
            else{
                logger.debug("Stopping Tomcat Service");
                proc.stopService("Apache Tomcat", procNames[i] );
            }

            break;
        }else if( ( e.getSource() == btns[i] ) && i == 1 ){

            if( ! blks[i].isProcessAlive()){
                logger.debug("Starting PDS");
                proc.startService("Print Delivery Station", procNames[i] );
            }
            else{
                logger.debug("Stopping PDS");
                proc.stopService("Print Delivery Station", procNames[i] );
            }

            break;

        }else if( ( e.getSource() == btns[i] ) && i == 5 ){

            if( ! blks[i].isProcessAlive()){
                logger.debug("Starting PDG");
                proc.startService("Print Delivery Gateway", procNames[i] );
            }
            else{
                logger.debug("Stopping PDG");
                proc.stopService("Print Delivery Gateway", procNames[i] );
            }

            break;

        }else if( ( e.getSource() == btns[i] ) && i == 2 ){
            if( ! blks[i].isProcessAlive()){
                logger.debug("Starting PrintAnywhere Processing");
                proc.startService("PrinterOn Processing Server", procNames[i]);
            }
            else{
                logger.debug("Stopping PrintAnywhere Processing Server");
                proc.stopService("PrinterOn Processing Server", procNames[i]);
            }
        }else if( ( e.getSource() == btns[i] ) && i == 3 ){
            if( ! blks[i].isProcessAlive()){
                logger.debug("Starting PrintAnywhere Status Server");
                proc.startService("PrinterOn Status Server", procNames[i]);
            }
            else{
                logger.debug("Stopping PrintAnywhere Status Server");
                proc.stopService("PrinterOn Status Server", procNames[i]);
            }
        }else if( ( e.getSource() == btns[i] ) && i == 4 ){
            if( ! blks[i].isProcessAlive()){
                logger.debug("Starting PrinterOn Advertiser");
                proc.startService("PrinterOn Advertiser", procNames[i]);
            }
            else{
                logger.debug("Stopping PrinterOn Advertiser");
                proc.stopService("PrinterOn Advertiser", procNames[i]);
            }
        }
    }
    proc = null;
}


}