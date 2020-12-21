import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Database {

        private Connection db;
        static DefaultTableModel dtm;

    public Database() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/uas_java";
            String user = "root";
            String pass = "";

            Class.forName(driver);
            db = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void insert(){

        String fname = UserEditPanel.txtName.getText();
        String lname = UserEditPanel.txtLast.getText();
        String email = UserEditPanel.txtEmail.getText();
        String pass = UserEditPanel.txtPass.getText();

            try{
            String sql = "INSERT INTO user Value (?, ?, ?, ?)";
            PreparedStatement ps =db.prepareStatement(sql);

            ps.setString(1, fname);
            ps.setString(2,lname);
            ps.setString(3,email);
            ps.setString(4,pass);
            ps.execute();

            JOptionPane.showMessageDialog(null, "SUCCESS");

        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"FAILED");
        }

    }
        public void cek() {
        PreparedStatement ps;
        ResultSet rs;
        String email = LoginPanel.txtEmail.getText();
        String pass = LoginPanel.txtPass.getText();


        try {
            String sql = "SELECT * FROM user WHERE `userEmail` =? AND `userPassword` =?";
            PreparedStatement pd =db.prepareStatement(sql);

            pd.setString(1, email);
            pd.setString(2, pass);


            rs = pd.executeQuery();

            if(rs.next())
            {

               new Note();

            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"FAILED");
        }


    }
    public void show(){
        ResultSet rs;
        String mail = LoginPanel.txtEmail.getText();
        try {
            Object[] column = {"notesId","notesJudul","notesFile","userEmail"};
            dtm = new DefaultTableModel(null,column);
            DataPanel.note.setModel(dtm);

            try{
                String sql ="SELECT * FROM notes where `userEmail` = ? ";
                PreparedStatement st =db.prepareStatement(sql);

                st.setString(1, mail);

                rs = st.executeQuery();



                while (rs.next()){
                    Object[] row ={
                            rs.getString("notesId"),rs.getString("notesJudul"),rs.getString("notesFile"),
                            rs.getString("userEmail")
                    };
                    dtm.addRow(row);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void in(){
        String id =NotesEditPanel.txtID.getText();
        String judul = NotesEditPanel.txtJudul.getText();
        String file =NotesEditPanel.txtFile.getText();
        String email = LoginPanel.txtEmail.getText();


        try{
            String sql = "INSERT INTO notes Value (?, ?, ?, ?)";
            PreparedStatement ps =db.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2, judul);
            ps.setString(3,file);
            ps.setString(4,email);

            ps.execute();
            show();
            JOptionPane.showMessageDialog(null, "SUCCESS");
        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"FAILED");
        }

    }

    public void update(){
        try{
            String sql = "UPDATE  notes set notesJudul =?, notesFile =? WHERE notesId=?";
            PreparedStatement st =db.prepareStatement(sql);
            st.setString(1,NotesEditPanel.txtJudul.getText());
            st.setString(2,NotesEditPanel.txtFile.getText());
            st.setString(3,NotesEditPanel.txtID.getText());
            st.execute();
            show();
            JOptionPane.showMessageDialog(null, "UPDATE SUCCESS ");
        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"UPDATE FAILED");
        }

    }



    public void delete(){
        try{
            String sql = "DELETE FROM notes WHERE NotesId='"+NotesEditPanel.txtID.getText()+"'";
            PreparedStatement stt =db.prepareStatement(sql);

            stt.execute();
            show();
            JOptionPane.showMessageDialog(null, "DELETE SUCCESSFUL");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "DELETE FAILED");
        }
    }




}

