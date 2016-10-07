import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class SortedTable {
  public static void main(String args[]) {
    Runnable runner = new Runnable() {
     public void run() {
        JFrame frame = new JFrame("Sorting JTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object rows[][] = {
            {"AMZN", "Amazon", 44.36},
            {"EBAY", "eBay", 44.84},
            {"GOOG", "Google", 463.63},
            {"MSFT", "Microsoft", 27.14},
            {"N", "Inco Ltd.", 44.57},
            {"O", "Realty Income Corp.", 23.15},
            {"SUNW", "Sun Microsystems", 4.40},
            {"T",  "AT&T", 24.96},
            {"TIVO", "Tivo Inc", 5.45},
            {"X",  "US Steel", 49.54},
            {"Y", "Alleghany", 280.00}
          };
        String columns[] = {"Symbol", "Name", "Price"};
        TableModel model = new DefaultTableModel(rows, columns) {
          public Class getColumnClass(int column) {
            Class returnValue;
            if ((column >= 0) && (column < getColumnCount())) {
              returnValue = getValueAt(0, column).getClass();
            } else {
              returnValue = Object.class;
            }
            return returnValue;
          }
        };

        final JTable table = new JTable(model);
        RowSorter<TableModel> sorter =
          new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.CENTER);

        JButton button = new JButton("Print Selected Rows");
        ActionListener listener = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            for (int row: table.getSelectedRows()) {
              System.out.println("Selected row: " + 
                table.convertRowIndexToModel(row));
            }
          }
        };
        button.addActionListener(listener);
        frame.add(button, BorderLayout.SOUTH);

        frame.setSize(300, 250);
        frame.setVisible(true);
      }
    };
    EventQueue.invokeLater(runner);
  }
} 
