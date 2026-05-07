import javax.swing.*;
import java.awt.*;

public class CreateAdminForm extends JFrame {

    public CreateAdminForm() {
        setTitle("Create Admin");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        add(new JLabel("Username:"));
        add(user);

        add(new JLabel("Password:"));
        add(pass);

        JButton btn = new JButton("Create Admin");
        add(btn);

        btn.addActionListener(e -> {
            User u = new User();
            u.setUsername(user.getText());
            u.setPassword(new String(pass.getPassword()));
            u.setRole("ADMIN");

            if (new UserDAO().register(u)) {
                JOptionPane.showMessageDialog(this, "Admin Created");
                dispose();
            }
        });

        setVisible(true);
    }
}