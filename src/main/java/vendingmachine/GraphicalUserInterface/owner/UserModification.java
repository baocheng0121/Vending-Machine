package vendingmachine.GraphicalUserInterface.owner;

import vendingmachine.Cash;
import vendingmachine.GraphicalUserInterface.CashPaymentInterface;
import vendingmachine.GraphicalUserInterface.ChooseItem;
import vendingmachine.GraphicalUserInterface.StaffLogIn;
import vendingmachine.MachineEngine;
import vendingmachine.Role.Owner;
import vendingmachine.Role.*;
import vendingmachine.Log.*;
import vendingmachine.tools.Counter;

import java.awt.*;
import javax.crypto.Mac;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserModification extends JFrame {
    static UserModification frame;
    private JPanel contentPane;
    private JButton add;
    private JButton remove;
    private MachineEngine engine;


    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new UserModification();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public UserModification() {
        this.engine = new MachineEngine();
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        Owner owner=  new Owner("owner", "owner", engine);

        JLabel statusLabel = new JLabel("User Modification");
        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 25));

        JLabel nameLabel = new JLabel("username");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        JLabel pwdLabel = new JLabel("username");
        pwdLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        JLabel priceLabel = new JLabel("user type");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        JTextField names = new JTextField();
        JTextField pwd = new JTextField();

        String[] userTypes = new String[] {"cashier", "owner", "seller", "customer"};
        JComboBox<String> types = new JComboBox<>(userTypes);

        this.add = new JButton("Add");
        this.remove = new JButton("Remove");

        this.add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MachineEngine engine = new MachineEngine();
                Owner owner = new Owner("owner", "owner", engine);
                String userType = (String) types.getSelectedItem();
                if (userType.equalsIgnoreCase("customer")){
                    owner.addUser(new User(userType, names.getText(), pwd.getText()),
                            "src/main/java/vendingmachine/Log/UserInformation.txt"
                    );
                } else if (userType.equalsIgnoreCase("cashier")){
                    owner.addUser(new Cashier1(names.getText(), pwd.getText(), engine),
                            "src/main/java/vendingmachine/Log/StaffInformation.txt"
                    );
                } else if (userType.equalsIgnoreCase("seller")){
                    owner.addUser(new Seller(userType, names.getText(), pwd.getText(), engine),
                            "src/main/java/vendingmachine/Log/StaffInformation.txt"
                    );
                } else if (userType.equalsIgnoreCase("owner")){
                    owner.addUser(new Owner(names.getText(), pwd.getText(), engine),
                            "src/main/java/vendingmachine/Log/StaffInformation.txt"
                    );
                }
                JOptionPane.showMessageDialog(UserModification.this,"User is added!");

            }
        });

        this.remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {MachineEngine engine = new MachineEngine();
                Owner owner = new Owner("owner", "owner", engine);
                String userType = (String) types.getSelectedItem();
                List<User> users = FileManager.readUserList("src/main/java/vendingmachine/Log/UserInformation.txt");
                List<User> staffs = FileManager.readUserList("src/main/java/vendingmachine/Log/StaffInformation.txt");
                for (int i = 0 ; i<users.size(); i++){
                    if (users.get(i).getUserName().equalsIgnoreCase(names.getText()) && users.get(i).getType().equalsIgnoreCase(userType)){
                        try{
                            owner.deleteUser(users.get(i), "src/main/java/vendingmachine/Log/UserInformation.txt");
                            JOptionPane.showMessageDialog(UserModification.this,"User is deleted!");
                            return;
                        } catch (IOException exception){
                            JOptionPane.showMessageDialog(UserModification.this, "No such user");
                        }
                    }
                }
                for (int i = 0 ; i<staffs.size(); i++){
                    if (staffs.get(i).getUserName().equalsIgnoreCase(names.getText()) && staffs.get(i).getType().equalsIgnoreCase(userType)){
                        try{
                            owner.deleteUser(staffs.get(i), "src/main/java/vendingmachine/Log/StaffInformation.txt");
                            JOptionPane.showMessageDialog(UserModification.this,"User is deleted!");
                            return;
                        } catch (IOException exception){
                            JOptionPane.showMessageDialog(UserModification.this, "No such user");
                        }
                    }
                }

            }
        });

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userInfo = new String[2];
                OwnerInterface.main(userInfo);
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
                                        .addComponent(names)
                                        .addGap(50)
                                        .addComponent(priceLabel)
                                        .addComponent(types)
                                        .addGap(100)
                                        .addComponent(add)
                                        .addComponent(remove))

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
                                .addComponent(names)
                                .addGap(20)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(priceLabel)
                                )
                                .addComponent(types)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(add)
                                        .addComponent(remove)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
        contentPane.setLayout(layout);
    }
}
