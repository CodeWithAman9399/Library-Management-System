import javax.swing.*;
import java.awt.*;

public class RegisterForm extends JFrame {

    public RegisterForm() {
        setTitle("Register");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        add(new JLabel("Username:"));
        add(userField);

        add(new JLabel("Password:"));
        add(passField);

        JButton registerBtn = new JButton("Register");
        add(registerBtn);

        registerBtn.addActionListener(e -> {
            User user = new User();
            user.setUsername(userField.getText());
            user.setPassword(new String(passField.getPassword()));
            user.setRole("USER"); // FIXED

            if (new UserDAO().register(user)) {
                JOptionPane.showMessageDialog(this, "Registered as USER");
                dispose();
            }
        });

        setVisible(true);
    }
}