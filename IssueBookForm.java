import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class IssueBookForm extends JFrame {

    public IssueBookForm(String username) {
        setTitle("Issue Book");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Table columns
        String[] columns = {"ID", "Title", "Author", "Available Copies"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        // Fetch available books
        List<Book> books = new BookDAO().getAvailableBooks();
        for (Book b : books) {
            Object[] row = {b.getId(), b.getTitle(), b.getAuthor(), b.getAvailableCopies()};
            model.addRow(row);
        }

        // Scroll pane for table
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // Issue button
        JButton issueBtn = new JButton("Issue Selected Book");
        add(issueBtn, BorderLayout.SOUTH);

        issueBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int bookId = (int) model.getValueAt(selectedRow, 0);

                boolean success = new BookDAO().issueBook(bookId, username);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Book Issued Successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Book is not available!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to issue.");
            }
        });

        setVisible(true);
    }
}