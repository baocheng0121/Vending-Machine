package vendingmachine.GraphicalUserInterface.owner;

import vendingmachine.GraphicalUserInterface.StaffLogIn;
import vendingmachine.MachineEngine;
import vendingmachine.Role.Owner;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CancelHistory extends JFrame {
    static CancelHistory frame;
    private JPanel contentPane;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new CancelHistory();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public CancelHistory() {
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel nameLabel = new JLabel("Cancelled Transaction History");
        nameLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 25));

        MachineEngine engine = new MachineEngine();

        Owner owner = new Owner("owner", "owner", engine);

        List<String> cancelData = owner.showCancelTran("src/main/java/vendingmachine/Log/CancelLog.txt");

        String[] columnName = new String[] {"user name", "reason", "time"};

        int counter = 0;
        String[][] cancelContent = new String[cancelData.size()][3];
        for (String name : cancelData){
            cancelContent[counter][0] = name.split(",")[0];
            cancelContent[counter][1] = name.split(",")[1];
            cancelContent[counter][2] = name.split(",")[2];
            counter ++;
        }

        JTable table = new JTable(cancelContent,columnName);
        JScrollPane show = new JScrollPane(table);

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
                                                .addGap(40)
                                                .addComponent(nameLabel))
                                        .addComponent(show)
                                )

                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30)
                                .addComponent(nameLabel)
                                .addGap(10)
                                .addComponent(show)
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
