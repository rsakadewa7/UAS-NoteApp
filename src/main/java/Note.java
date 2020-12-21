import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Note extends JFrame {
    public Note(){
        setTitle("List Note");
        setSize(800,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new DataPanel());
        DataPanel.note.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int baris = DataPanel.note.rowAtPoint(e.getPoint());

                String ids = DataPanel.note.getValueAt(baris, 0).toString();
                NotesEditPanel.txtID.setText(ids);

                String nam = DataPanel.note.getValueAt(baris, 1).toString();
                NotesEditPanel.txtJudul.setText(nam);

                String jud = DataPanel.note.getValueAt(baris, 2).toString();
                NotesEditPanel.txtFile.setText(jud);

            }
        });
        add(new NotesEditPanel());
        add(new ButonPanel());
        setVisible(true);

    }

}


    class DataPanel extends JPanel {
        static JTable note = new JTable();


    public DataPanel(){
        setSize(800,250);
            Database db = new Database();
            db.show();
            JScrollPane scrollPane = new JScrollPane(note);
            note.setFillsViewportHeight(true);
            note.setEnabled(false);

            setLayout(new GridLayout(1,1));
            setBorder(new EmptyBorder(10,10,0,10));

            add(scrollPane);


        }
    }
class NotesEditPanel extends JPanel {
    static  JTextField txtID = new JTextField();
    static JTextField txtJudul = new JTextField();
    static  JTextArea txtFile = new JTextArea();

    public NotesEditPanel() {
        setSize(800,350);
        setLayout(new GridLayout(4,2));
        add(new JLabel("notesId"));
        add(txtID);
        add(new JLabel("notesJudul"));
        add(txtJudul);
        add(new JLabel("notesFile"));
        add(txtFile);



        //border
        Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(etchedBorder,"Employee Record Editor");
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10), titledBorder));

    }
}

class ButonPanel extends JPanel{
    JButton btnAdd = new JButton("Add New");
    JButton btnUpdate = new JButton("UPDATE");
    JButton btnDelete = new JButton("DELETE");
    JButton btnClose = new JButton("CLOSE");

    public ButonPanel(){
        setSize(800,150);
        final Database db =new Database();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.in();

            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.update();

            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                db.delete();

            }
        });
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        setLayout(new GridLayout(1,4));
        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);
        add(btnClose);


    }
}
