package vendingmachine.GraphicalUserInterface;

import vendingmachine.MachineEngine;
import vendingmachine.Log.*;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseItem extends JFrame {
    static ChooseItem frame;
    private JPanel contentPane;
    private JButton cancel;
    private JButton confirm;
    private JButton addToCart;
    private MachineEngine engine;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ChooseItem(args[0], args[1]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public ChooseItem(String username, String password) {
        MachineEngine.refreshShoppingList();
        this.engine = new MachineEngine();
        User user = LogIn.checkUser(username, password, "src/main/java/vendingmachine/Log/UserInformation.txt");

        if (username.equalsIgnoreCase("anonymous")){
            List<String> ret = FileManager.checkAnonymousItem("src/main/java/vendingmachine/Log/AnonymousItem.txt");
            String lastBought = "Five Items you last bought:\n";
            for (int i =0; i<ret.size(); i++) {
                lastBought+= "      - ";
                lastBought+=ret.get(i);
                lastBought+="\n";
            }
            JOptionPane.showMessageDialog(ChooseItem.this, lastBought);
        } else{
            String[] ret = FileManager.checkUserItem(user, "src/main/java/vendingmachine/Log/UserInformation.txt");
            String lastBought = "Five Items you last bought:\n";
            for (int i =0; i<ret.length; i++) {
                lastBought+= "      - ";
                lastBought+=ret[i];
                lastBought+="\n";
            }
            JOptionPane.showMessageDialog(ChooseItem.this, lastBought);
        }

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Please enter the name and quantity of the items you want");

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        String[] columnName = {"Name", "Price", "Amount"};

        String[][] itemData = new String[this.engine.getItemName().length][3];
        for (int i = 0; i<this.engine.getItemName().length; i++){
            itemData[i][0] = this.engine.getItemName()[i];
        }

        for (int i = 0; i<this.engine.getItemPrice().length; i++){
            itemData[i][1] = this.engine.getItemPrice()[i];
        }

        for (int i = 0; i<this.engine.getItemStock().length; i++){
            itemData[i][2] = this.engine.getItemStock()[i];
        }

        JTable itemsTable = new JTable(itemData, columnName);
        JScrollPane items = new JScrollPane(itemsTable);

        JLabel nameLabel = new JLabel("item name:");
        JLabel quantityLabel = new JLabel("quantity:");

        JTextField name = new JTextField();
        name.setColumns(10);

        JTextField quantity = new JTextField();
        quantity.setColumns(5);


        this.cancel = new JButton("Cancel");
        this.confirm = new JButton("Check Out");
        this.addToCart = new JButton("Add To Cart");

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userInfo = new String[2];
                userInfo[0] = username;
                userInfo[1] = password;
                GameWindow.main(userInfo);
                frame.dispose();
            }
        });

        this.confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userinfo = new String[2];
                userinfo[0] = username;
                userinfo[1] = password;
                CheckOutInterface.main(userinfo);
                frame.dispose();
            }
        });

        this.cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MachineEngine engine = new MachineEngine();
                engine.cancel();
                statusLabel.setText("Transaction is Canceled");
            }
        });

        this.addToCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MachineEngine engine = new MachineEngine();
                String item= name.getText();
                String quantityText =quantity.getText();
                int amount = Integer.parseInt(quantityText);
                if (engine.add_cart(item, amount)) {
                    statusLabel.setText("Add to cart successfully");
                } else {
                    statusLabel.setText("Failed to add to cart");
                }
                engine.update();
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
                                                .addGap(100)
                                        .addComponent(nameLabel)
                                        .addComponent(quantityLabel)
                                        .addComponent(quantity, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addToCart)
                                        .addComponent(cancel)
                                        .addGap(60)
                                        .addComponent(confirm))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(statusLabel)
                                .addGap(50)
                                .addComponent(items, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                .addGap(50)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(nameLabel)
                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(quantityLabel)
                                        .addComponent(quantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                            .addComponent(addToCart)
                                            .addComponent(cancel)
                                        .addComponent(confirm)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
