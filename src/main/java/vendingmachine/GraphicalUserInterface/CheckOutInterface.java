package vendingmachine.GraphicalUserInterface;

import vendingmachine.App;
import vendingmachine.Card;
import vendingmachine.MachineEngine;
import vendingmachine.Log.*;
import vendingmachine.tools.Counter;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class CheckOutInterface extends JFrame {
    static CheckOutInterface frame;
    private JPanel contentPane;
    private JButton cancel;
    private JButton complete;
    private JButton cash;
    private MachineEngine engine;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new CheckOutInterface(args[0], args[1]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public CheckOutInterface(String username, String password) {
        Counter timer = new Counter(this, username, password);
        new Thread(timer).start();

        this.engine = new MachineEngine();
        User user = LogIn.checkUser(username, password, "src/main/java/vendingmachine/Log/UserInformation.txt");

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Please complete your shopping list");
        JLabel priceLabel = new JLabel("Your total price is: $" + this.engine.totalAmount());

        JLabel nameLabel = new JLabel("user name:");
        JLabel cardLabel = new JLabel("card number:");
        JLabel instruction = new JLabel("To complete transaction");

        Map<String, Integer> shoppinglist = engine.getShoppingList();
        double total= engine.totalAmount();

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        String[] columnName = {"Name", "Amount"};

        String[][] itemData = new String[shoppinglist.size()][2];
        int counter = 0;
        for (String s : shoppinglist.keySet()){
            itemData[counter][0] = s;
            itemData[counter][1] = shoppinglist.get(s)+"";
            counter ++;
        }

        JTable itemInfo = new JTable(itemData, columnName);
        JScrollPane items = new JScrollPane(itemInfo);

        JTextField name = new JTextField();
        name.setColumns(10);

        JPasswordField card = new JPasswordField();
        card.setColumns(5);

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.cancelTran();
                String[] userInfo = new String[2];
                userInfo[0] = username;
                userInfo[1] = password;
                ChooseItem.main(userInfo);
                frame.dispose();
            }
        });

        this.cancel = new JButton("Cancel");
        this.complete = new JButton("Complete");
        this.cash = new JButton("Pay by cash");

        this.complete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Card cardData = new Card(String.valueOf(card.getPassword()), name.getText());
                if (cardData.isValid()){
                    String itemList = "";
                    for (String name : engine.getShoppingList().keySet()){
                        itemList += name + ", ";
                    }
                    if (username.equalsIgnoreCase("anonymous")){
                        for (String s : engine.getShoppingList().keySet()){
                            try{
                                FileManager.anonymousItem(s, "src/main/java/vendingmachine/Log/AnonymousItem.txt");
                            }catch (FileNotFoundException exception){}
                        }
                    } else {
                        for (String s : engine.getShoppingList().keySet()){
                            try{
                                FileManager.refreshUserItem(user, s, "src/main/java/vendingmachine/Log/UserInformation.txt");
                            } catch (IOException exception){}
                        }
                    }
                    engine.recordTransaction(itemList, "card", total, total);
                    timer.cancelTran();
                    statusLabel.setText("Your transaction has been completed!");
                    engine.checkoutCart();
                    engine.update();
                    JOptionPane.showMessageDialog(CheckOutInterface.this,"Your transaction has been completed!");
                    name.setText("");
                    card.setText("");
                    String[] userInfo = new String[2];
                    userInfo[0] = username;
                    userInfo[1] = password;
                    ChooseItem.main(userInfo);
                    frame.dispose();
                } else {
                    statusLabel.setText("Invalid Card!");
                    JOptionPane.showMessageDialog(CheckOutInterface.this,"Invalid Card!");
                    name.setText("");
                    card.setText("");
                }
            }
        });

        this.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileManager.cancelLog(username, "customer cancel", "src/main/java/vendingmachine/Log/CancelLog.txt");
                timer.cancelTran();
                MachineEngine engine = new MachineEngine();
                engine.cancel();
                statusLabel.setText("Transaction is Canceled");
                String[] userInfo = new String[2];
                userInfo[0] = username;
                userInfo[1] = password;
                frame.dispose();
            }
        });

        this.cash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.cancelTran();
                String[] userInfo = new String[2];
                userInfo[0] = username;
                userInfo[1] = password;
                CashPaymentInterface.main(userInfo);
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
                                        .addComponent(items, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(instruction)
                                        .addComponent(priceLabel)
                                        .addComponent(nameLabel)
                                        .addComponent(cardLabel)
                                        .addComponent(card, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addGap(100)
                                        .addComponent(cash)
                                        .addComponent(cancel)
                                        .addGap(60)
                                        .addComponent(complete))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(statusLabel)
                                .addGap(50)
                                .addComponent(items, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                .addComponent(priceLabel)
                                .addGap(25)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(instruction)
                                        .addComponent(nameLabel)
                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(cardLabel)
                                        .addComponent(card, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(cash)
                                        .addComponent(cancel)
                                        .addComponent(complete)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
