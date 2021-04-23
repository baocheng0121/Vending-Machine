
package vendingmachine.GraphicalUserInterface.cashier;

import vendingmachine.GraphicalUserInterface.StaffLogIn;
import vendingmachine.MachineEngine;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashDisplay extends JFrame {
    static CashDisplay frame;
    private JPanel contentPane;


    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new CashDisplay();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public CashDisplay() {
        /* setting the default value */
        MachineEngine engine = new MachineEngine();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginLabel = new JLabel("Cash Display");
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JLabel nameLabel = new JLabel("The Current Cash:");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 18));


        String[] columnName = {"Cash Type", "Quantity"};
        String[][] itemData = engine.getCashData();

        JTable cashTable = new JTable(itemData, columnName);
        JScrollPane cash = new JScrollPane(cashTable);

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userInfo = new String[2];
                CashierInterface.main(userInfo);
                frame.dispose();
            }
        });

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(100)
                                .addComponent(loginLabel)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addGap(100)
                                .addComponent(nameLabel)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(cash, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addComponent(nameLabel)
                                .addComponent(cash)
                                .addGroup(layout.createSequentialGroup())
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}