
package vendingmachine.GraphicalUserInterface.cashier;

import vendingmachine.MachineEngine;
import vendingmachine.Role.Cashier1;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionReport extends JFrame {
    static TransactionReport frame;
    private JPanel contentPane;


    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new TransactionReport();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    /** setting up the interface frame **/
    public TransactionReport() {
        /* setting the default value */
        MachineEngine engine = new MachineEngine();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginLabel = new JLabel("Transaction Report");
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        Cashier1 cashier = new Cashier1("cashier", "cahiser", engine);

        String[] columnName = {"Time", "Items", "Paid", "Changes", "Payment Method"};
        String[][] itemData = cashier.showTransactionList();

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
                                .addGap(150)
                                .addComponent(loginLabel))
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(cash, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)))

        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addGap(20)
                                .addComponent(cash, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup())
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}