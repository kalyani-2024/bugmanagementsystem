import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class BugTrackerGUI extends JFrame {
    private BugManager bugManager = new BugManager();
    private JTable bugTable;

    public BugTrackerGUI() {
        setTitle("Bug Tracking System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom Gradient Background
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(200, 0, 0);
                Color color2 = new Color(18, 18, 18);
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        });

        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Bug Tracking System", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Bug Table
        String[] columnNames = {"ID", "Title", "Description", "Priority", "Status"};
        bugTable = new JTable(new BugTableModel(bugManager));
        bugTable.setFillsViewportHeight(true);
        bugTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bugTable.setRowHeight(25);
        bugTable.setForeground(Color.WHITE);
        bugTable.setBackground(new Color(50, 0, 0));
        bugTable.setGridColor(new Color(0, 0, 0));

        JTableHeader header = bugTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(0, 0, 0));

        JScrollPane scrollPane = new JScrollPane(bugTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Right-click context menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem changeStatusMenuItem = new JMenuItem("Change Status");
        changeStatusMenuItem.setForeground(Color.BLACK);
        changeStatusMenuItem.addActionListener(e -> {
            int selectedRow = bugTable.getSelectedRow();
            if (selectedRow != -1) {
                int bugId = (int) bugTable.getValueAt(selectedRow, 0);
                try {
                    Bug selectedBug = bugManager.findBugById(bugId);
                    String newStatus = JOptionPane.showInputDialog("Enter new status (Open, In Progress, Closed):");
                    if (newStatus != null && !newStatus.isEmpty()) {
                        selectedBug.setStatus(newStatus);
                        ((BugTableModel) bugTable.getModel()).fireTableDataChanged();
                    }
                } catch (BugNotFoundException ex) {
                    JOptionPane.showMessageDialog(BugTrackerGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        popupMenu.add(changeStatusMenuItem);

        JMenuItem deleteBugMenuItem = new JMenuItem("Delete Bug");
        deleteBugMenuItem.setForeground(Color.RED);
        deleteBugMenuItem.addActionListener(e -> {
            int selectedRow = bugTable.getSelectedRow();
            if (selectedRow != -1) {
                int bugId = (int) bugTable.getValueAt(selectedRow, 0);
                try {
                    bugManager.deleteBug(bugId);
                    ((BugTableModel) bugTable.getModel()).fireTableDataChanged();
                } catch (BugNotFoundException ex) {
                    JOptionPane.showMessageDialog(BugTrackerGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        popupMenu.add(deleteBugMenuItem);

        bugTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopupMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopupMenu(e);
                }
            }

            private void showPopupMenu(MouseEvent e) {
                int row = bugTable.rowAtPoint(e.getPoint());
                if (row >= 0 && row < bugTable.getRowCount()) {
                    bugTable.setRowSelectionInterval(row, row);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(18, 18, 18));

        JButton addButton = new JButton("Add Bug");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLACK);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter Bug Title:");
            String description = JOptionPane.showInputDialog("Enter Description:");
            String priority = JOptionPane.showInputDialog("Enter Priority (Low, Medium, High):");

            if (title != null && description != null && priority != null) {
                bugManager.addBug(title, description, priority);
                ((BugTableModel) bugTable.getModel()).fireTableDataChanged();
            }
        });
        bottomPanel.add(addButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
