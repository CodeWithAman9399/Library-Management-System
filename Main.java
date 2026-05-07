import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    public Main() {
        setTitle("Library System");
        setSize(350, 250);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        add(loginBtn);
        add(registerBtn);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> new RegisterForm());

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void login() {
        User user = new UserDAO().login(
                usernameField.getText(),
                new String(passwordField.getPassword())
        );

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Success");

            if (AuthService.isAdmin(user)) {
                new AdminDashboard();
            } else {
                new UserDashboard(user.getUsername()); // ✅ FIXED
            }

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Login");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}