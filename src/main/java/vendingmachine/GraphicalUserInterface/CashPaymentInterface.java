package vendingmachine.GraphicalUserInterface;

import vendingmachine.Cash;
import vendingmachine.MachineEngine;
import vendingmachine.Log.*;
import vendingmachine.tools.Counter;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;

public class CashPaymentInterface extends JFrame {
    static CashPaymentInterface frame;
    private JPanel contentPane;
    private JButton cancel;
    private JButton insert;
    private double total;
    private double current = 0.0;
    private MachineEngine engine;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new CashPaymentInterface(args[0], args[1]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public CashPaymentInterface(String username, String password) {
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

        JLabel statusLabel = new JLabel("Please insert a coin or note!");
        JLabel priceLabel = new JLabel("Your have to pay: $" + this.engine.totalAmount());

        JLabel nameLabel = new JLabel("please insert cash");

        Map<String, Integer> shoppinglist = engine.getShoppingList();
        total= engine.totalAmount();

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

        JComboBox<String> cashTypes = new JComboBox<>(engine.getCashTypes());

        this.cancel = new JButton("Cancel");
        this.insert = new JButton("Insert");

        this.insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current += Double.parseDouble((String)cashTypes.getSelectedItem());
                if (total > current){
                    priceLabel.setText("Your have to pay: $" + (total-current));
                    statusLabel.setText("Please insert cash");
                } else{
                    String itemList = "";
                    for (String name : engine.getShoppingList().keySet()){
                        itemList += name + ", ";
                    }
                    statusLabel.setText("");
                    engine.checkoutCart();
                    engine.update();
                    String change = engine.changes(current, total);
                    if (change.equalsIgnoreCase("fail")){
                        FileManager.cancelLog(username, "out of changes","src/main/java/vendingmachine/Log/CancelLog.txt");
                        JOptionPane.showMessageDialog(CashPaymentInterface.this,"Out of changes!");
                        timer.cancelTran();
                        String[] userInfo = new String[2];
                        userInfo[0] = username;
                        userInfo[1] = password;
                        ChooseItem.main(userInfo);
                        frame.dispose();
                    } else {
                        engine.recordTransaction(itemList, "cash", current, total);
                        JOptionPane.showMessageDialog(CashPaymentInterface.this,"Here is your change:\n"+change);
                        timer.cancelTran();
                        GameWindow.main(new String[2]);
                        frame.dispose();
                    }
                }
            }
        });

        this.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MachineEngine engine = new MachineEngine();
                FileManager.cancelLog(username, "customer cancel", "src/main/java/vendingmachine/Log/CancelLog.txt");
                engine.cancel();
                statusLabel.setText("Transaction is Canceled");
                String[] userInfo = new String[2];
                userInfo[0] = username;
                userInfo[1] = password;
                timer.cancelTran();
                ChooseItem.main(userInfo);
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
                                                .addGap(120)
                                                .addComponent(statusLabel))
                                        .addGap(50)
                                        .addComponent(items, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceLabel)
                                        .addComponent(nameLabel)
                                        .addComponent(cashTypes)
                                        .addGap(100)
                                        .addComponent(cancel)
                                        .addGap(60)
                                        .addComponent(insert))
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
                                        .addComponent(nameLabel))
                                        .addComponent(cashTypes)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(cancel)
                                        .addComponent(insert)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
