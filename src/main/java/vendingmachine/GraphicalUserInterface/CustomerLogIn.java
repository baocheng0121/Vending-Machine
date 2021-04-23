package vendingmachine.GraphicalUserInterface;

import vendingmachine.Log.LogIn;
import vendingmachine.Log.User;
import vendingmachine.MachineEngine;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerLogIn extends JFrame {
    static CustomerLogIn frame;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton newButton;
    private MachineEngine engine;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new CustomerLogIn();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public CustomerLogIn() {
        this.engine = new MachineEngine();
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginLabel = new JLabel("Customer Logging In");
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JLabel nameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        textField = new JTextField();
        textField.setColumns(10);

        passwordField = new JPasswordField();

        loginButton = new JButton("Log In");
        newButton = new JButton("create new account");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name=textField.getText();
                char ch[]=passwordField.getPassword();
                String password=String.valueOf(ch);
                User user = vendingmachine.Log.LogIn.checkUser(name, password, "src/main/java/vendingmachine/Log/UserInformation.txt");
                if(user != null){
                    String[] userinfo = new String[2];
                    userinfo[0] = name;
                    userinfo[1] = password;
                    ChooseItem.main(userinfo);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(CustomerLogIn.this,"No such user!");
                    textField.setText("");passwordField.setText("");
                }
            }
        });

        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateAccount.main(new String[2]);
                frame.dispose();
            }
        });

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userInfo = new String[2];
                GameWindow.main(userInfo);
                frame.dispose();
            }
        });

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(140)
                                                .addComponent(loginLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25)
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(nameLabel)
                                                        .addComponent(passwordLabel))
                                                .addGap(58)
                                                .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(passwordField)
                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(180)
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(newButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addGap(40)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(nameLabel)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(10)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordLabel))
                                .addGap(36)
                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(50)
                                .addComponent(newButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
