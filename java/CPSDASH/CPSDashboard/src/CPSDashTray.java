import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

public class CPSDashTray{

    static CPSDashTray dashtray;

   CPSDashTray(){
    if( SystemTray.isSupported()){
        PopupMenu popup = new PopupMenu();
        final MenuItem exitTrayMI = new MenuItem("Exit");
        final MenuItem restoreMI = new MenuItem("Restore");
        final MenuItem aboutMI = new MenuItem("About");
        final SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage("D:\\CPSDashTray\\images\\cps_icon.gif");
        final TrayIcon trayIcon = new TrayIcon( image, "CPSDashboard", popup );
        ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if( e.getSource().equals(exitTrayMI)){
                    tray.remove(trayIcon);
                    System.exit(0);
                  }else if(e.getSource().equals(restoreMI)){
                      /*
                      if (Desktop.isDesktopSupported()){
                      try{
                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(new URI("http://www.printeron.com") );
                      }catch( Exception ex){

                      }}*/
                        try{

                            System.out.println("Trying to launch CPSDashboard");
                            Runtime runtime = Runtime.getRuntime();
                            Process proc = runtime.exec("java -jar ..\\CPSDashboard\\jar\\CPSDashboard.jar");
                            proc.waitFor();
                            //Thread.sleep(1000);
                            //exitVal = proc.exitValue();
                            System.exit(0);

                        }catch( IOException ioe){
                            //logger.error(ioe.getMessage().toString());
                            //res = false;
                            System.out.println(ioe.getMessage().toString());
                        }catch(InterruptedException ie){
                               System.out.println(ie.getMessage().toString());
                        }
                 }else if(e.getSource().equals(aboutMI)){
                      JOptionPane.showMessageDialog(null, "CPS Dashboard 1.0", "CPSDashboard", JOptionPane.INFORMATION_MESSAGE);
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
            try{
                tray.add(trayIcon);
            }catch( AWTException e){
                System.out.println("Unable to create tray icon");
            }
    }else{
        JOptionPane.showMessageDialog(null, "TrayIcon not supported by your system", "TrayIcon", JOptionPane.ERROR_MESSAGE);
    }
  }
   public static void main(String args[]) {
       dashtray = new CPSDashTray();
   }

}