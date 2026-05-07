import javax.swing.*;
import java.util.List;

public class ViewBooks extends JFrame {

    public ViewBooks() {
        setTitle("Books");
        setSize(400, 300);

        JTextArea area = new JTextArea();
        area.setEditable(false); // user should not edit
        add(new JScrollPane(area));

        List<Book> books = new BookDAO().getAllBooks();

        // Display ID, Title, Author, Available
        area.append(String.format("%-5s %-20s %-20s %-10s\n", "ID", "Title", "Author", "Available"));
        area.append("------------------------------------------------------------\n");

        for (Book b : books) {
            String available = b.isAvailable() ? "Yes" : "No";
            area.append(String.format("%-5d %-20s %-20s %-10s\n", b.getId(), b.getTitle(), b.getAuthor(), available));
        }

        setVisible(true);
    }
}