/**
 * Blinker.java
 */
//package CPSDashboard;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;



class Blinker extends JPanel implements Runnable
{

    private ImageIcon runningIcon, disabledRunningIcon;
    private ImageIcon stoppedIcon;
    private ImageIcon transIcon;
    private String processName;
    private JLabel label;
    //private WatchProcess wproc;
    //private Thread wprocthr;
    private boolean bProcAlive;
    private boolean bProcTrans;
    private boolean bBlinking;
    private boolean bInstalled;

    public Logger logger = CPSDashLogger.getCPSDashLogger();

    /**
     * Blinker class constructor, it just inits the processName to blank.
     */
    Blinker(){
        processName = "";
    }

    /**
     * This constructor inits the process name to be monitored and the label where
     * the monitor icon will be placed.
     * @param processName String
     * @param label JLabel
     */
    Blinker( String processName, JLabel label ){
        this.processName = processName;
        this.label = label;
        bBlinking = CPSDashConfig.getCPSDashConfigInstance().getBlinking();
        bInstalled = isInstalled();
        //loadIconImages();
    }

    public boolean isInstalled(){
    	boolean bInstalled = true;
    	if( processName.equals("PonServices")){
    		CPSDashComponent comp = RegistryAccess.searchComp(CPSDashConstants.PONAD_COMP);
    	    if( comp == null ){
    	    	return false;
    	    }    		   		
    	}
    	return bInstalled;
    }
    /**
     * Public function to acces the status of a process
     * @return boolean process alive or not.
     */
    public boolean isProcessAlive()
    {
        return bProcAlive;
    }
    /**
     * Not used. Left for future reference.
     * @param val
     */
    public void setTransProc( boolean val ){
        bProcTrans = val;
    }

    /**
     * Not used. Left for future reference.
     * @return boolean
     */
    public boolean getTransProc(){
        return bProcTrans;
    }

    /**
     * This function is actually not used as there were no nice monitoring icons during
     * the development of this application. However it is left in case the application
     * needs different look.
     */

    void loadIconImages(){

        logger.debug("loadIconImages - START");
        
        runningIcon = new ImageIcon("D:\\CPSDashboard\\images\\runningIcon.jpg");
        if( runningIcon == null)
            logger.error("Unable to load runningIcon");

        disabledRunningIcon = new ImageIcon("D:\\CPSDashboard\\images\\disabledRunningIcon.jpg");
        if( disabledRunningIcon == null)
            logger.error("Unable to load disabledRunningIcon");

        stoppedIcon = new ImageIcon("D:\\CPSDashboard\\images\\stoppedIcon.jpg");
        if( stoppedIcon == null)
            logger.error("Unable to load stoppedIcon");

        transIcon = new ImageIcon("D:\\CPSDashboard\\images\\progressIcon.jpg");
        if( transIcon == null)
            logger.error("Unable to load progressIcon");

        label.setIcon(runningIcon);

        logger.debug("loadIconImages - END");
        
    }

    /**
     * Starts the execution of the Blinker thread.
     */
    public void run(){

//        wproc = new WatchProcess(processName);
//        wprocthr = new Thread(wproc);
//        wprocthr.start();
        Icon run = new ColoredSquare(Color.GREEN);
        Icon stop = new ColoredSquare(Color.RED);
        Icon dis = new ColoredSquare(Color.LIGHT_GRAY);
        
        while( Thread.currentThread().isAlive()){

            try{
                if( WatchProcesses.isProcAlive(processName) ){
                    bProcAlive = true;
                    label.setIcon(run);
                    if( bBlinking ){
                        Thread.sleep(1000);
                        label.setIcon(dis);
                        Thread.sleep(1000);
                        label.setIcon(run);
                    }else Thread.sleep(2000);
               
                }else{
                    bProcAlive = false;
                    if( bInstalled )
                    	label.setIcon(stop);
                    else
                    	label.setIcon(dis);
                    Thread.sleep(2000);
                }
            }catch( InterruptedException e){
                logger.error(e.getMessage().toString());
            }

        }

    }

}
/**
 * This class creates a square coloured icon to show the state of a process, green for active
 * and red for stopped.
 * @author garciar
 */
class ColoredSquare implements Icon {
  Color color;

  public ColoredSquare(Color color) {
    this.color = color;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) {
    Color oldColor = g.getColor();
    g.setColor(color);
    g.fill3DRect(x, y, getIconWidth(), getIconHeight(), true);
    g.setColor(oldColor);
  }

  public int getIconWidth() {
    return 12;
  }

  public int getIconHeight() {
    return 12;
  }
}