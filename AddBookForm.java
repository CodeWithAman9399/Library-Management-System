import javax.swing.*;
import java.awt.*;

public class AddBookForm extends JFrame {

    public AddBookForm() {
        setTitle("Add Book");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2)); // 4 rows: Title, Author, Copies, Button

        // Text fields
        JTextField title = new JTextField();
        JTextField author = new JTextField();
        JTextField copies = new JTextField(); // Number of copies

        // Add labels and fields
        add(new JLabel("Title:"));
        add(title);

        add(new JLabel("Author:"));
        add(author);

        add(new JLabel("Copies:"));
        add(copies);

        // Add button
        JButton btn = new JButton("Add Book");
        add(btn);

        // Empty label to fill grid
        add(new JLabel(""));

        // Button action
        btn.addActionListener(e -> {
            try {
                Book b = new Book();
                b.setTitle(title.getText());
                b.setAuthor(author.getText());
                int num = Integer.parseInt(copies.getText());
                b.setTotalCopies(num);
                b.setAvailableCopies(num);

                new BookDAO().addBook(b);
                JOptionPane.showMessageDialog(this, "Book Added Successfully!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for copies.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}