import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReturnBookForm extends JFrame {

    public ReturnBookForm(String username) {
        setTitle("Return Book");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Table columns
        String[] columns = {"Book ID", "Title", "Author", "Issued On"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        // Fetch issued books for this user
        List<IssuedBook> issuedBooks = new BookDAO().getIssuedBooksForUser(username);
        for (IssuedBook ib : issuedBooks) {
            Object[] row = {ib.getBookId(), ib.getTitle(), ib.getAuthor(), ib.getIssueDate()};
            model.addRow(row);
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton returnBtn = new JButton("Return Selected Book");
        add(returnBtn, BorderLayout.SOUTH);

        returnBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int bookId = (int) model.getValueAt(selectedRow, 0);
                boolean success = new BookDAO().returnBook(bookId, username);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Book Returned Successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error returning book!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to return.");
            }
        });

        setVisible(true);
    }
}