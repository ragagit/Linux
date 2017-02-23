import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;



public class POComponents extends JPanel{

    POComponents(){

    Logger logger = CPSDashLogger.getCPSDashLogger();
    
    String col[] = {"Name", "Version", "Path"};

    Vector<CPSDashComponent> comps = RegistryAccess.getAllComponents();
    logger.debug("size of comps:" + comps.size() );
    String data[][] = new String[comps.size()][3];

    for( int i=0; i < comps.size(); i++){

        if( comps.elementAt(i) != null){
            data[i][0] = comps.elementAt(i).getCompName();
            data[i][1] = comps.elementAt(i).getCompVersion();
            data[i][2] = comps.elementAt(i).getCompPath();
        }        
        
    }

    //DefaultTableModel model = new DefaultTableModel(data, col);
    MyDefaultTableModel model = new MyDefaultTableModel(data, col);

    JTable table = new JTable(model){
        public Component prepareRenderer(TableCellRenderer renderer, int row, int col){
            Component comp = super.prepareRenderer(renderer, row, col);
            JComponent jcomp = (JComponent)comp;
            if(comp == jcomp){
                jcomp.setToolTipText((String)getValueAt(row, col));
            }
            return comp;
        }

    };

    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    TableColumn colName = table.getColumnModel().getColumn(0);
    colName.setPreferredWidth(130);
    TableColumn colVer = table.getColumnModel().getColumn(1);
    colVer.setPreferredWidth(60);
    TableColumn colPath = table.getColumnModel().getColumn(2);
    colPath.setPreferredWidth(300);
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);    
    }
}
class MyDefaultTableModel extends DefaultTableModel{
    public MyDefaultTableModel(Object[][] data, Object[] colNames){
        super(data, colNames);
    }
    public boolean isCellEditable(int row, int col){
        return false;
    }
}