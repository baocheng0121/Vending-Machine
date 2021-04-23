package vendingmachine.GraphicalUserInterface.owner;

import vendingmachine.GraphicalUserInterface.seller.*;
import vendingmachine.GraphicalUserInterface.ChooseItem;
import vendingmachine.GraphicalUserInterface.CustomerLogIn;
import vendingmachine.GraphicalUserInterface.StaffLogIn;
import vendingmachine.MachineEngine;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemManagement extends JFrame {
    static ItemManagement frame;
    private JPanel contentPane;
    private final JButton add;
    private final JButton refill;
    private final JButton price;
    private final JButton display;
    private final JButton report;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ItemManagement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public ItemManagement() {
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginLabel = new JLabel("Items Management");
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        add = new JButton("Add Product");
        refill = new JButton("Refill");
        price = new JButton("Modify Price");
        display = new JButton("Display Item");
        report = new JButton("Generate Report");

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddItem.main(new String[2]);
                frame.dispose();
            }
        });

        refill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Refill.main(new String[2]);
                frame.dispose();
            }
        });

        price.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePrice.main(new String[2]);
                frame.dispose();
            }
        });

        display.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemDisplay.main(new String[2]);
                frame.dispose();
            }
        });

        report.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MachineEngine engine = new MachineEngine();
                engine.generateItemReport();
                JOptionPane.showMessageDialog(ItemManagement.this,"Report is successfully generated!");
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
                                                .addComponent(add, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(refill, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(price, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(display, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(report, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                )


                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addGap(30)
                                .addComponent(add, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(refill, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(price, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(display, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(15)
                                .addComponent(report, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
