import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BugTableModel extends AbstractTableModel {
    private BugManager bugManager;
    private String[] columnNames = {"ID", "Title", "Description", "Priority", "Status"};

    public BugTableModel(BugManager bugManager) {
        this.bugManager = bugManager;
    }

    @Override
    public int getRowCount() {
        return bugManager.listBugs().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Bug> bugs = bugManager.listBugs();
        Bug bug = bugs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bug.getId();
            case 1:
                return bug.getTitle();
            case 2:
                return bug.getDescription();
            case 3:
                return bug.getPriority();
            case 4:
                return bug.getStatus();
            default:
                return null;
        }
    }

    // Optional: Overriding setValueAt if you want to make cells editable.
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // For example, only the status column might be editable.
        return columnIndex == 4; // Set to true if you want any column to be editable.
    }
    
    // Optional: Implement if you want to update the model from editable cells.
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        List<Bug> bugs = bugManager.listBugs();
        Bug bug = bugs.get(rowIndex);
        if (columnIndex == 4 && aValue instanceof String) { // Assuming column 4 is Status
            bug.setStatus((String) aValue);
            fireTableCellUpdated(rowIndex, columnIndex); // Notify the table that data has changed
        }
    }
}
