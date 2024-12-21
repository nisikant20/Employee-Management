import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame implements ActionListener {

    Splash(){
        getContentPane().setBackground(Color.white);
        setLayout(null);
        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(80,50,1200,60);
        heading.setFont(new Font("serif",Font.PLAIN,60));
        heading.setForeground(Color.white);
        add(heading);


ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
Image i2 = i1.getImage().getScaledInstance(1170, 1170,Image.SCALE_DEFAULT);
ImageIcon i3 = new ImageIcon(i2);
JLabel  image = new JLabel(i3);
image.setBounds(0,0,1170,1170);
add(image);

//button

JButton clickhere = new JButton("CLICK HERE TO CONTINUE");
clickhere.setFont(new Font("TAHOMA",Font.PLAIN,15));
clickhere.setBounds(250,300,230,50);
clickhere.addActionListener(this);
image.add(clickhere);

//HEADING
setSize(1170, 800);
setLocation(200,50);
setVisible(true);
while (true) {
    heading.setVisible(false);
    try {
        Thread.sleep(600);
    } catch ( Exception e) {
    
    }
    heading.setVisible(true);
    try {
        Thread.sleep(600);
    } catch ( Exception e) {
    }
    
}

}

@Override
public void actionPerformed(ActionEvent e) {
setVisible(false);
new Login();
    
}
    public static void main(String args[]){
new Splash();
}

}
