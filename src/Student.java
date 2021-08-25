import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Student {
    private JPanel mainPanel;

    private JTextField txt_name;
    private JTextField txt_class;
    private JTextField txt_roll;

    private JTextField txt_maths;
    private JTextField txt_chem;
    private JTextField txt_bio;
    private JTextField txt_phy;
    private JTextField txt_engLang;
    private JTextField txt_engLit;
    private JTextField txt_hindi;
    private JTextField txt_arts;
    private JTextField txt_hist;
    private JTextField txt_geo;

    private JButton getTotalButton;
    private JTextField txt_total;
    private JButton saveButton;
    private JTable table1;

    Connection con;
    PreparedStatement pst;

    public Student() {

        connect();
        table_load();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String s_name, s_class, s_roll;
                int s_maths, s_chem, s_bio, s_phy, s_engLang, s_engLit, s_hindi, s_arts, s_hist, s_geo;

                s_name = txt_name.getText();
                s_class = txt_class.getText();
                s_roll = txt_roll.getText();
                s_maths = Integer.parseInt(txt_maths.getText());
                s_chem = Integer.parseInt(txt_chem.getText());
                s_bio = Integer.parseInt(txt_bio.getText());
                s_phy = Integer.parseInt(txt_phy.getText());
                s_engLang = Integer.parseInt(txt_engLang.getText());
                s_engLit = Integer.parseInt(txt_engLit.getText());
                s_hindi = Integer.parseInt(txt_hindi.getText());
                s_arts = Integer.parseInt(txt_arts.getText());
                s_hist = Integer.parseInt(txt_hist.getText());
                s_geo = Integer.parseInt(txt_geo.getText());

                try {
                    pst = con.prepareStatement("insert into details(name,class,rollNo,maths,chem,bio,phy,engLang,engLit,hindi,arts,his,geo)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, s_name);
                    pst.setString(2, s_class);
                    pst.setString(3, s_roll);
                    pst.setInt(4, s_maths);
                    pst.setInt(5, s_chem);
                    pst.setInt(6, s_bio);
                    pst.setInt(7, s_phy);
                    pst.setInt(8, s_engLang);
                    pst.setInt(9, s_engLit);
                    pst.setInt(10, s_hindi);
                    pst.setInt(11, s_arts);
                    pst.setInt(12, s_hist);
                    pst.setInt(13, s_geo);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, s_name+"'s Data Added.");
                    table_load();

                    txt_name.setText("");
                    txt_class.setText("");
                    txt_roll.setText("");
                    txt_maths.setText("" );
                    txt_chem.setText("");
                    txt_bio.setText("");
                    txt_phy.setText("");
                    txt_engLang.setText("");
                    txt_engLit.setText("");
                    txt_hindi.setText("");
                    txt_arts.setText("");
                    txt_hist.setText("");
                    txt_geo.setText("");

                    txt_name.requestFocus();

                }

                catch (SQLException throwables) {
                    throwables.printStackTrace();

                }

            }
        });
        getTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int s_maths, s_chem, s_bio, s_phy, s_engLang, s_engLit, s_hindi, s_arts, s_hist, s_geo;
                int total;

                s_maths = Integer.parseInt(txt_maths.getText());
                s_chem = Integer.parseInt(txt_chem.getText());
                s_bio = Integer.parseInt(txt_bio.getText());
                s_phy = Integer.parseInt(txt_phy.getText());
                s_engLang = Integer.parseInt(txt_engLang.getText());
                s_engLit = Integer.parseInt(txt_engLit.getText());
                s_hindi = Integer.parseInt(txt_hindi.getText());
                s_arts = Integer.parseInt(txt_arts.getText());
                s_hist = Integer.parseInt(txt_hist.getText());
                s_geo = Integer.parseInt(txt_geo.getText());

                total = s_maths + s_chem + s_bio + s_phy + s_engLang + s_engLit + s_hindi + s_arts + s_hist + s_geo;

                txt_total.setText(String.valueOf(total));

            }
        });
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentmarks", "root","root");
            System.out.println("connection to database is a Success.");
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void table_load() {
        try
        {
            pst = con.prepareStatement("select * from details");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student");
        frame.setContentPane(new Student().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
