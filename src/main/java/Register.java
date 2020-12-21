import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register  extends JFrame {
    public Register() {
        setTitle("REGISTER");
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new UserEditPanel());
        add(new ButtonPanel());

        setVisible(true);
    }
}
    class UserEditPanel extends JPanel {

        static  JTextField txtName = new JTextField();
        static JTextField txtLast = new JTextField();
        static  JTextField txtEmail = new JTextField();
        static JTextField txtPass = new JTextField();

        public UserEditPanel() {
            setLayout(new GridLayout(4,2));

            add(new JLabel("Nama Depan"));
            add(txtName);
            add(new JLabel("Nama BElakang"));
            add(txtLast);
            add(new JLabel("Email"));
            add(txtEmail);
            add(new JLabel("Password"));
            add(txtPass);

            //border
            Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder,"User Registration Form");
            setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10), titledBorder));

        }
    }

class ButtonPanel extends JPanel {
    JButton btnAdd = new JButton("Add New");
    JButton btnBack = new JButton("login");

    public ButtonPanel(){
        final Database db =new Database();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.insert();

            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();

            }
        });

        setLayout(new GridLayout(1,2));
        add(btnAdd);
        add(btnBack);



    }

}


