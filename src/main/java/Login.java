import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    public Login(){
        setTitle("Login");
        setSize(400,250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new LoginPanel());
        add(new ButtonsPanel());
        setVisible(true);

    }

}
class LoginPanel extends JPanel {

    static  JTextField txtEmail = new JTextField();
    static JTextField txtPass = new JTextField();

    public LoginPanel() {
        setLayout(new GridLayout(5,2));

        add(new JLabel("Email"));
        add(txtEmail);
        add(new JLabel("Password"));
        add(txtPass);

        //border
        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder,"LOGIN FORM");
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10), titledBorder));

    }
}

class ButtonsPanel extends JPanel{

    JButton btnLogin = new JButton("Login");
    JButton btnRegister = new JButton("Register");

    public ButtonsPanel(){
        final Database db =new Database();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            db.cek();

            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();


            }
        });
        setLayout(new GridLayout(1,4));
        add(btnLogin);
        add(btnRegister);



    }
}
