package vendingmachine.GraphicalUserInterface.cashier;

import vendingmachine.MachineEngine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashModificationInterface extends JFrame {
    static CashModificationInterface frame;
    private JPanel contentPane;
    private JButton verify;
    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new CashModificationInterface();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public CashModificationInterface(){
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel title = new JLabel("Modify Cash");
        title.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        JLabel typeLabel = new JLabel("cash type:");
        JLabel quantityLabel = new JLabel("quantity:");
        JTextField type = new JTextField();
        type.setColumns(10);

        JTextField quantity = new JTextField();
        quantity.setColumns(5);
        this.verify=new JButton("Verify");
        this.verify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MachineEngine engine = new MachineEngine();
                String cashType=type.getText();
                String quantityText=quantity.getText();

                boolean exists = false;
                for (String s : engine.getCashTypes()){
                    if (s.equalsIgnoreCase(cashType)){
                        exists = true;
                        engine.setCashQuantity(s, Integer.parseInt(quantityText));
                    }
                }
                if (!exists){
                    engine.addCashType(cashType, Integer.parseInt(quantityText));
                }
                title.setText("modify successfully");
                CashierInterface.main(new String[2]);
                frame.dispose();
            }
        });

        JButton back = new JButton("back");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] userInfo = new String[2];
                CashierInterface.main(userInfo);
                frame.dispose();
            }
        });

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGap(50)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(100)
                                                .addComponent(title))
                                        .addComponent(typeLabel)
                                        .addComponent(quantityLabel)
                                        .addComponent(quantity, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(type, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addGap(100)
                                        .addComponent(verify))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(title)
                                .addGap(50)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(typeLabel)
                                        .addComponent(type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(quantityLabel)
                                        .addComponent(quantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addGap(50)
                                        .addComponent(verify)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);

    }
}
