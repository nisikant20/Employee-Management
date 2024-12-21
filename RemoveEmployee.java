import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice cEmpid;
    JButton remove, back;
    JLabel lblname, lblphone, lblemail;

    RemoveEmployee() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel labelempid = new JLabel("Employee ID");
        labelempid.setBounds(50, 50, 100, 30);
        add(labelempid);

        cEmpid = new Choice();
        cEmpid.setBounds(200, 50, 150, 30);
        add(cEmpid);

        try {
            Conn c = new Conn();
            String query = "select empid from employee";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cEmpid.add(rs.getString("empid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelname = new JLabel("Name:");
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);

        lblname = new JLabel();
        lblname.setBounds(200, 100, 150, 30);
        add(lblname);

        JLabel labelphone = new JLabel("Phone:");
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);

        lblphone = new JLabel();
        lblphone.setBounds(200, 150, 150, 30);
        add(lblphone);

        JLabel labelemail = new JLabel("Email:");
        labelemail.setBounds(50, 200, 100, 30);
        add(labelemail);

        lblemail = new JLabel();
        lblemail.setBounds(200, 200, 150, 30);
        add(lblemail);

        cEmpid.addItemListener(e -> {
            try {
                Conn c = new Conn();
                String query = "select name, phone, email from employee where empid = '" + cEmpid.getSelectedItem() + "'";
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    lblname.setText(rs.getString("name"));
                    lblphone.setText(rs.getString("phone"));
                    lblemail.setText(rs.getString("email"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        remove = new JButton("Remove");
        remove.setBounds(50, 250, 100, 30);
        remove.addActionListener(this);
        add(remove);

        back = new JButton("Back");
        back.setBounds(200, 250, 100, 30);
        back.addActionListener(this);
        add(back);

        setSize(400, 400);
        setLocation(600, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == remove) {
            String empid = cEmpid.getSelectedItem();
            try {
                Conn c = new Conn();
                String query = "delete from employee where empid = '" + empid + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Removed Successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee().setVisible(true);
    }
}