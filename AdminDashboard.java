import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin");
        setSize(300, 200);
        setLayout(new FlowLayout());

        JButton manageBtn = new JButton("Manage Library");

        add(manageBtn);

        manageBtn.addActionListener(e -> new ManageLibrary());

        setVisible(true);
    }
}