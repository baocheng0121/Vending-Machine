package vendingmachine.GraphicalUserInterface.owner;

import vendingmachine.GraphicalUserInterface.StaffLogIn;

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

public class OwnerInterface extends JFrame {
    static OwnerInterface frame;
    private JPanel contentPane;
    private final JButton user;
    private final JButton item;
    private final JButton cash;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new OwnerInterface();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public OwnerInterface() {
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel nameLabel = new JLabel("Owner Interface");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 25));

        user = new JButton("User Management");
        item = new JButton("Item Management");
        cash = new JButton("Cash Management");

        user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserManagement.main(new String[2]);
                frame.dispose();
            }
        });

        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemManagement.main(new String[2]);
                frame.dispose();
            }
        });

        cash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CashManagement.main(new String[2]);
                frame.dispose();
            }
        });

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userInfo = new String[2];
                StaffLogIn.main(userInfo);
                frame.dispose();
            }
        });

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(40)
                                                .addComponent(nameLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(user, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(item, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(cash, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))


                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30)
                                .addComponent(nameLabel)
                                .addGap(10)
                                .addComponent(user, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(item, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(cash, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
