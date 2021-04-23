package vendingmachine.GraphicalUserInterface.seller;

import vendingmachine.Cash;
import vendingmachine.GraphicalUserInterface.CashPaymentInterface;
import vendingmachine.GraphicalUserInterface.ChooseItem;
import vendingmachine.GraphicalUserInterface.StaffLogIn;
import vendingmachine.MachineEngine;
import vendingmachine.Role.Seller;
import vendingmachine.Log.*;
import vendingmachine.tools.Counter;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;

public class Refill extends JFrame {
    static Refill frame;
    private JPanel contentPane;
    private JButton refill;
    private MachineEngine engine;


    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new Refill();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public Refill() {
        this.engine = new MachineEngine();
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Refill Items");
        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 25));

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        JComboBox<String> itemNames = new JComboBox<>(engine.getItemName());

        JTextField amount = new JTextField();
        amount.setColumns(10);

        this.refill = new JButton("Refill");

        this.refill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number = 0;
                try {
                    number = Integer.parseInt(amount.getText());
                } catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(Refill.this,"Please enter a valid integer!");
                    return;
                }
                String name = (String) itemNames.getSelectedItem();

                if (number <= 0){
                    JOptionPane.showMessageDialog(Refill.this,"Please enter a positive integer!");
                    return;
                }

                MachineEngine engine = new MachineEngine();
                if (!engine.refill(name, number)){
                    JOptionPane.showMessageDialog(Refill.this,"The maximum capacity of an item is 15!");
                    return;
                }

                engine.update();

                JOptionPane.showMessageDialog(Refill.this,"Refilled!");

            }
        });

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userInfo = new String[2];
                SellerInterface.main(userInfo);
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
                                                .addComponent(statusLabel))
                                        .addGap(50)
                                        .addComponent(nameLabel)
                                        .addComponent(itemNames)
                                        .addGap(50)
                                        .addComponent(amountLabel)
                                        .addComponent(amount)
                                        .addGap(100)
                                        .addComponent(refill))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(statusLabel)
                                .addGap(30)
                                .addComponent(nameLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(nameLabel))
                                .addComponent(itemNames)
                                .addGap(20)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(amountLabel)
                                )
                                .addComponent(amount)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(refill)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
        contentPane.setLayout(layout);
    }
}
