package vendingmachine.GraphicalUserInterface.owner;

import vendingmachine.GraphicalUserInterface.StaffLogIn;
import vendingmachine.MachineEngine;
import vendingmachine.Role.Owner;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class UserDisplay extends JFrame {
    static UserDisplay frame;
    private JPanel contentPane;


    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new UserDisplay();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public UserDisplay() {
        /* setting the default value */
        MachineEngine engine = new MachineEngine();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginLabel = new JLabel("User Account Display");
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        Owner owner = new Owner("owner", "owner", engine);

        String[] columnName = {"User Name", "User Type"};
        Map<String, String> users = owner.showUserList("src/main/java/vendingmachine/Log/UserInformation.txt");

        String[][] itemData = new String[users.size()][2];
        int counter = 0;
        for (String name : users.keySet()){
            itemData[counter][0] = name;
            itemData[counter][1] = users.get(name);
            counter ++;
        }

        JTable cashTable = new JTable(itemData, columnName);
        JScrollPane cash = new JScrollPane(cashTable);



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
                                .addGap(100)
                                .addComponent(loginLabel)
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
                                .addComponent(cash)
                                .addGroup(layout.createSequentialGroup())
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}