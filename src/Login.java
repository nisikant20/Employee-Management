
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

    JTextField tfusername , tfpassword;

Login(){

    getContentPane().setBackground(Color.white);
    setLayout(null);
 
//>>Username
JLabel lblusername = new JLabel("Username");
lblusername.setBounds(40,20,100,30);
add(lblusername);
 tfusername = new JTextField();
tfusername.setBounds(150,20,150,30);
add(tfusername);

//>>Password
JLabel lblpassword = new JLabel("Password");
lblpassword.setBounds(40,70,100,30);
add(lblpassword);
tfpassword = new JTextField();
tfpassword.setBounds(150,70,150,30);
add(tfpassword);

//>>Login
JButton login = new JButton("LOGIN");
login.setBounds(150,140,150,30);
login.addActionListener(this);
add(login);


ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
Image i2 = i1.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
ImageIcon i3 = new ImageIcon(i2);
JLabel  image = new JLabel(i3);
image.setBounds(350,0,150,150);
add(image);
    setSize(600,300);
    setLocation(450,200);
    setVisible(true);

}
@Override
public void actionPerformed(ActionEvent e) {
    try {
        String username = tfusername.getText(); // Get the username from the text field
        String password = tfpassword.getText(); // Get the password from the text field
        Conn c = new Conn();
        
        // Corrected SQL query with single quotes around username and password
        String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";
        
        ResultSet rs = c.s.executeQuery(query);
        if (rs.next()) {
            setVisible(false);
            new Home();
            // Proceed to the next step (e.g., open a new window)
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password");
            setVisible(false);
        }
    } catch (Exception exception) {
        exception.printStackTrace();
    }
}
public static void main(String[] args) {
    new Login();
    
}

    

}
