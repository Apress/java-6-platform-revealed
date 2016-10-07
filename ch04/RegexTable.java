import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class RegexTable {
  public static void main(String args[]) {
    Runnable runner = new Runnable() {
     public void run() {
        JFrame frame = new JFrame("Regexing JTable");
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
        final TableRowSorter<TableModel> sorter =
          new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Filter");
        panel.add(label, BorderLayout.WEST);
        final JTextField filterText = new JTextField("T");
        panel.add(filterText, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        JButton button = new JButton("Filter");
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String text = filterText.getText();
            if (text.length() == 0) {
              sorter.setRowFilter(null);
            } else {
              sorter.setRowFilter(RowFilter.regexFilter(text));
            }
          }
        });
        frame.add(button, BorderLayout.SOUTH);
        frame.setSize(300, 250);
        frame.setVisible(true);
      }
    };
    EventQueue.invokeLater(runner);
  }
} 
