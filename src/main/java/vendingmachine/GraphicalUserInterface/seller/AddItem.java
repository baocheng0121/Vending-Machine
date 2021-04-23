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

public class AddItem extends JFrame {
    static AddItem frame;
    private JPanel contentPane;
    private JButton confirm;
    private MachineEngine engine;


    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new AddItem();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public AddItem() {
        this.engine = new MachineEngine();
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("AddItem Items");
        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 25));

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        JLabel stockLabel = new JLabel("Stock");
        stockLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        JTextField name = new JTextField();
        name.setColumns(10);

        JComboBox<String> category = new JComboBox<String>(new String[] {"Drink", "Chip", "Chocolate", "Chip"});


        JTextField price = new JTextField();
        price.setColumns(10);

        JTextField stock = new JTextField();
        stock.setColumns(10);

        this.confirm = new JButton("AddItem");

        this.confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double number = 0.0;
                int amount = 0;
                try {
                    number = Double.parseDouble(price.getText());
                    amount = Integer.parseInt(stock.getText());
                } catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(AddItem.this,"Please enter a valid number!");
                    return;
                }
                String itemName = name.getText();
                String itemCategory = (String)category.getSelectedItem();

                if (number <= 0 || amount <= 0){
                    JOptionPane.showMessageDialog(AddItem.this,"Make sure you enter a positive price or quantity!");
                    return;
                }

                MachineEngine engine = new MachineEngine();
                int flag = engine.addItem(itemCategory, itemName, number, amount);
                if (flag == 2){
                    JOptionPane.showMessageDialog(AddItem.this, "The price is negative");
                } else if (flag == 3){
                    JOptionPane.showMessageDialog(AddItem.this, "The quantity must be in range from 0 to 15");
                } else if (flag == 4){
                    JOptionPane.showMessageDialog(AddItem.this, "Invalid Category");
                } else {
                    JOptionPane.showMessageDialog(AddItem.this,"Item is added!");
                }
                engine.update();
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
                                        .addComponent(categoryLabel)
                                        .addComponent(category)
                                        .addGap(50)
                                        .addComponent(nameLabel)
                                        .addComponent(name)
                                        .addGap(50)
                                        .addComponent(priceLabel)
                                        .addComponent(price)
                                        .addGap(50)
                                        .addComponent(stockLabel)
                                        .addComponent(stock)
                                        .addGap(100)
                                        .addComponent(confirm))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(statusLabel)
                                .addGap(30)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(categoryLabel))
                                .addComponent(category)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(nameLabel)
                                )
                                .addComponent(name)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(priceLabel)
                                )
                                .addComponent(price)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(stockLabel)
                                )
                                .addComponent(stock)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(confirm)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
        contentPane.setLayout(layout);
    }
}
