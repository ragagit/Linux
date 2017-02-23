import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * This class allows user to launch the PrinterOn documentation.
 * It requires to know intallation path of Docs and Utilities, however
 * Docs&Utilities is on a component therefore it uses CPSDashboard installtion
 * path whch share the same root path.
 * @author garciar
 */
public class Docs extends JPanel implements ActionListener{

    String docNames[] = {"CPS Customization Guide", "CPS Administrator's Guide", "PDS User Guide"};
    String docPDFNames[] = {"CPS Customization Guide.pdf", "CPS Administrator's Guide.pdf", "PDS User Guide.pdf" };
    JButton btns[] = new JButton[docNames.length];
    String tmp = "";
    String docPath = "";

    CPSDashComponent comp;
    File docDir;
    public Logger logger = CPSDashLogger.getCPSDashLogger();

    Docs(){

        setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
        for( int i=0; i < docNames.length; i++){
            btns[i] = new JButton("Open");
            createDocBox( docNames[i], btns[i] );
        }
        createEmptyBox();


        //Getting info from registry
        comp = RegistryAccess.searchComp(CPSDashConstants.CPSDASH_COMP);

        if( comp != null ){

            tmp = comp.getCompPath();
            docPath = tmp.replace("CPSDashboard", "Documentation" );
            logger.debug("docPath:" + docPath );            
            try{docDir = new File(docPath);}catch(Exception e){logger.error(e.getMessage().toString()); }
            if( ! docDir.exists() ){
                btns[0].setEnabled(false);
                btns[1].setEnabled(false);
                btns[2].setEnabled(false);
            }
            if( ! new File( docPath + "\\" + docPDFNames[0] ).exists() ){
                btns[0].setEnabled(false);
            }
            if( ! new File( docPath + "\\" + docPDFNames[1] ).exists() ){
                btns[1].setEnabled(false);
            }
            if( ! new File( docPath + "\\" + docPDFNames[2] ).exists() ){
                btns[2].setEnabled(false);
            }
            
        }

    }

    /**
     * This function creates a box, document title and button to launch the document.
     * @param docName String document name
     * @param btn
     */
    public void createDocBox( String docName, JButton btn ){

        JPanel panel = new JPanel();
        //left panel
        panel.setLayout(new GridLayout(1,2));
        JPanel doc1Panel = new JPanel();
        BoxLayout doc1Lay= new BoxLayout( doc1Panel, BoxLayout.X_AXIS);
        doc1Panel.setLayout(doc1Lay);
        JLabel l = new JLabel("   ");
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel docLabel = new JLabel(docName);
        docLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        doc1Panel.add(l);
        doc1Panel.add(docLabel);
        panel.add(doc1Panel);

        //rigth panel
        JPanel doc2Panel = new JPanel();
        BoxLayout doc2lay = new BoxLayout( doc2Panel, BoxLayout.X_AXIS);
        doc2Panel.setLayout(doc2lay);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        doc2Panel.add(btn);
        panel.add(doc2Panel);

        //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        btn.addActionListener(this);

        add(panel);

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
    
    public void actionPerformed(ActionEvent e){

        StartStopProc proc = new StartStopProc();

        for(int i=0; i < docNames.length; i++){
            if( ( e.getSource() == btns[i] ) ){
                logger.debug("Starting process: "  );
                proc.startProc( "rundll32 url.dll,FileProtocolHandler", docPath + "\\" + docPDFNames[i] );
            }

        }
    }
}