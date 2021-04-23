package vendingmachine.GraphicalUserInterface.owner;

import vendingmachine.GraphicalUserInterface.ChooseItem;
import vendingmachine.GraphicalUserInterface.StaffLogIn;
import vendingmachine.GraphicalUserInterface.cashier.*;

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

public class CashManagement extends JFrame {
    static CashManagement frame;
    private JPanel contentPane;
    private final JButton modify;
    private final JButton display;
    private final JButton report;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new CashManagement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public CashManagement() {
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginLabel = new JLabel("Cash Management");
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        modify = new JButton("Modify Cash");
        display = new JButton("Display Cash");
        report = new JButton("Transaction Report");

        modify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CashModificationInterface.main(new String[2]);
                frame.dispose();
            }
        });

        display.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CashDisplay.main( new String[2]);
                frame.dispose();
            }
        });

        report.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userinfo = new String[2];
                userinfo[0] = "anonymous";
                userinfo[1] = "";
                TransactionReport.main(userinfo);
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
                                                .addGap(150)
                                                .addComponent(loginLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(modify, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(display, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(report, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))


                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addGap(30)
                                .addComponent(modify, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(display, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(report, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
