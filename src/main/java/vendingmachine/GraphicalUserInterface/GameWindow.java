package vendingmachine.GraphicalUserInterface;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWindow extends JFrame {
    static GameWindow frame;
    private JPanel contentPane;
    private final JButton staffButton;
    private final JButton loginButton;
    private final JButton anonymousButton;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new GameWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public GameWindow() {
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginLabel = new JLabel("Vending Machine!");
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JLabel nameLabel = new JLabel("You are logging in as a:");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 18));

        staffButton = new JButton("Staff");
        loginButton = new JButton("Customer");
        anonymousButton = new JButton("Anonymous");

        staffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StaffLogIn.main(new String[2]);
                frame.dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerLogIn.main( new String[2]);
                frame.dispose();
            }
        });

        anonymousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userinfo = new String[2];
                userinfo[0] = "anonymous";
                userinfo[1] = "";
                ChooseItem.main(userinfo);
                frame.dispose();
            }
        });

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(loginLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(40)
                                                .addComponent(nameLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(staffButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(anonymousButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))


                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addGap(30)
                                .addComponent(nameLabel)
                                .addGap(10)
                                .addComponent(staffButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(anonymousButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
