import javax.swing.*;
import java.awt.*;

public class ManageLibrary extends JFrame {

    public ManageLibrary() {
        setTitle("Manage Library");
        setSize(300, 250);
        setLayout(new GridLayout(3, 1));

        JButton addBook = new JButton("Add Book");
        JButton viewBooks = new JButton("View Books");
        JButton createAdmin = new JButton("Create Admin");

        add(addBook);
        add(viewBooks);
        add(createAdmin);

        addBook.addActionListener(e -> new AddBookForm());
        viewBooks.addActionListener(e -> new ViewBooks());
        createAdmin.addActionListener(e -> new CreateAdminForm());

        setVisible(true);
    }
}