
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tfname, tfeducation, tffname, tfaddress, tfphone, tfemail, tfsalary;
    JDateChooser dcdob;
    JLabel labelempid;
    JButton update, back;
    String empid;

    UpdateEmployee(String empid) {
        this.empid = empid;
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labelname);

        JLabel lblname = new JLabel();
        lblname.setBounds(200, 150, 150, 30);
        add(lblname);

        JLabel labelfname = new JLabel("Father's Name");
        labelfname.setBounds(400, 150, 150, 30);
        labelfname.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(600, 150, 150, 30);
        add(tffname);

        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(50, 200, 150, 30);
        labeldob.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labeldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(200, 200, 150, 30);
        add(dcdob);

        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(400, 200, 150, 30);
        labelsalary.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(600, 200, 150, 30);
        add(tfsalary);

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(50, 250, 150, 30);
        labeladdress.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(400, 250, 150, 30);
        labelphone.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 300, 150, 30);
        labelemail.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel labeleducation = new JLabel("Education");
        labeleducation.setBounds(400, 300, 150, 30);
        labeleducation.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labeleducation);

        tfeducation = new JTextField();
        tfeducation.setBounds(600, 300, 150, 30);
        add(tfeducation);

        // Fetch employee details from the database
        try {
            Conn conn = new Conn();
            String query = "select * from employee where empid = '" + empid + "'";
            ResultSet rs = conn.s.executeQuery(query);
            if (rs.next()) {
                lblname.setText(rs.getString("name"));
                tffname.setText(rs.getString("fname"));
                dcdob.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("dob")));
                tfsalary.setText(rs.getString("salary"));
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfeducation.setText(rs.getString("education"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        update = new JButton("Update");
        update.setBounds(250, 550, 150, 40);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String fname = tffname.getText();
            String dob = new SimpleDateFormat("yyyy-MM-dd").format(dcdob.getDate());
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = tfeducation.getText();

            try {
                Conn conn = new Conn();
                String query = "update employee set fname='" + fname + "', dob='" + dob + "', salary='" + salary + "', address='" + address + "', phone='" + phone + "', email='" + email + "', education='" + education + "' where empid='" + empid + "'";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating details: " + e.getMessage());
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("12345").setVisible(true); // Example employee ID
    }
}