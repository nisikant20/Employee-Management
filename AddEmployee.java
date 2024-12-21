import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
public class AddEmployee extends  JFrame implements  ActionListener{

    Random ran = new Random();
    int number = ran.nextInt(999999);

   JTextField tfname , tffname , tfaddress, tfphone, tfemail, tfsalary;
   JDateChooser dcdob;
   JComboBox cbeducation;
   JLabel labelempid;
   JButton add, back;

    AddEmployee() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labelname);

        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        add(tfname);

        JLabel labelfname = new JLabel("Father's Name");
        labelfname.setBounds(400, 150, 150, 30);
        labelfname.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(600, 150, 150, 30);
        add(tffname);

        JLabel labeldob = new JLabel("D.O.B");
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

        JLabel labelemail = new JLabel("E-Mail");
        labelemail.setBounds(50, 300, 150, 30);
        labelemail.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labelemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel labeleducation = new JLabel("Highest Education");
        labeleducation.setBounds(400, 300, 150, 30);
        labeleducation.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labeleducation);
        
        String Courses[]  ={"BBA","BCA","BSC","BA","B.COM","BTech","MBA","MCA","MA","MTech"};
        cbeducation = new JComboBox(Courses);
        cbeducation.setBounds(600, 300, 150, 30);
        add(cbeducation);

        labelempid = new JLabel("EmployeeID");
        labelempid.setBounds(50, 350, 150, 30);
        labelempid.setFont(new Font("Serif", Font.PLAIN, 20));
        add(labelempid);
        
        JLabel lblempid = new JLabel(""+number);
        lblempid.setBounds(200,350,150,30);
        lblempid.setFont(new Font("Serif" , Font.PLAIN,20));
        add(lblempid);

        add = new JButton("Add Details");
        add.setBounds(200,400,150,40);
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBounds(400,400,150,40);
        back.addActionListener(this);
        add(back);



        setSize(1000, 600);
        setLocation(300, 50);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddEmployee();
    }

   @Override
public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
        String name = tfname.getText();
        String fname = tffname.getText();
        

        String dob = null;
        if (dcdob.getDate() != null) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dob = dateFormat.format(dcdob.getDate());
        } else {
            JOptionPane.showMessageDialog(null, "Please select a date of birth.");
            return;
        }

        String salary = tfsalary.getText();
        String address = tfaddress.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String education = (String) cbeducation.getSelectedItem();
        String empid = String.valueOf(number); // Use the generated employee ID

        try {
            Conn Conn = new Conn();
            String query = "insert into employee values('" + name + "','" + fname + "','" + dob + "','" + salary + "','" + address + "','" + phone + "','" + email + "','" + education + "','" + empid + "')";
            Conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Details added successfully");
            tfname.setText("");
            tffname.setText("");
            dcdob.setDate(null); // Clear the date chooser
            tfsalary.setText("");
            tfaddress.setText("");
            tfphone.setText("");
            tfemail.setText("");
            cbeducation.setSelectedIndex(0);
            setVisible(false);
            new Home();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding details: " + e.getMessage());
        }
    } else {
        setVisible(false);
        new Home();
    }
}
}


