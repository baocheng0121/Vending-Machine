package vendingmachine.GraphicalUserInterface.seller;

import vendingmachine.GraphicalUserInterface.ChooseItem;
import vendingmachine.GraphicalUserInterface.StaffLogIn;
import vendingmachine.MachineEngine;
import vendingmachine.Log.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemDisplay extends JFrame {
    static ItemDisplay frame;
    private JPanel contentPane;
    private MachineEngine engine;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ItemDisplay();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public ItemDisplay() {
        MachineEngine.refreshShoppingList();
        this.engine = new MachineEngine();

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("Item Display");

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 25));

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
                                        .addComponent(items, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                                        .addGap(100)
                                )
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

                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
