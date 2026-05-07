import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {

    public UserDashboard(String username) {
        setTitle("User Dashboard");
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));

        JButton view = new JButton("View Books");
        JButton issue = new JButton("Issue Book");
        JButton returnBtn = new JButton("Return Book");

        add(view);
        add(issue);
        add(returnBtn);

        // View books (shows all available books)
        view.addActionListener(e -> new ViewBooks());

        // Issue book (passes username)
        issue.addActionListener(e -> new IssueBookForm(username));

        // Return book (passes username)
        returnBtn.addActionListener(e -> new ReturnBookForm(username));

        setVisible(true);
    }
}