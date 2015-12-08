/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Pikachu
 */
public class SaleGui extends javax.swing.JFrame {

    /**
     * Creates new form SaleGui
     */
    MainGui previous;
    SaleController sale;
    public SaleGui(MainGui prev) {
        previous=prev;
        sale = (SaleController)previous.getRegisterController().getCurrentTransaction();
        initComponents();
        console.setText(sale.display());
        cashButton.setEnabled(false);
        cashButton.setVisible(false);
        
        creditButton.setEnabled(false);
        creditButton.setVisible(false);
        
        debitButton.setEnabled(false);
        debitButton.setVisible(false);
        
        paymentLabel1.setEnabled(false);
        paymentLabel1.setVisible(false);
        paymentInput1.setEnabled(false);
        paymentInput1.setVisible(false);
        
        paymentLabel2.setEnabled(false);
        paymentLabel2.setVisible(false);
        paymentInput2.setEnabled(false);
        paymentInput2.setVisible(false);
        
        paymentLabel3.setEnabled(false);
        paymentLabel3.setVisible(false);
        paymentInput3.setEnabled(false);
        paymentInput3.setVisible(false);
        
        enterPaymentButton.setEnabled(false);
        enterPaymentButton.setVisible(false);
        MainGui.centreWindow(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        productCodeInput = new javax.swing.JTextField();
        quantityInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        voidButton = new javax.swing.JButton();
        couponLabel = new javax.swing.JLabel();
        couponCodeInput = new javax.swing.JTextField();
        closeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        couponButton = new javax.swing.JButton();
        overrideButton = new javax.swing.JButton();
        cashButton = new javax.swing.JRadioButton();
        creditButton = new javax.swing.JRadioButton();
        debitButton = new javax.swing.JRadioButton();
        paymentInput2 = new javax.swing.JTextField();
        paymentInput1 = new javax.swing.JTextField();
        paymentInput3 = new javax.swing.JTextField();
        paymentLabel1 = new javax.swing.JLabel();
        paymentLabel2 = new javax.swing.JLabel();
        paymentLabel3 = new javax.swing.JLabel();
        enterPaymentButton = new javax.swing.JButton();
        suspendButton = new javax.swing.JButton();

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Code");

        jLabel5.setText("Quantity");

        voidButton.setText("Void");
        voidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voidButtonActionPerformed(evt);
            }
        });

        couponLabel.setText("Coupon Code");

        closeButton.setText("Pay");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        console.setEditable(false);
        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        couponButton.setText("Add Coupon");
        couponButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                couponButtonActionPerformed(evt);
            }
        });

        overrideButton.setText("Override");
        overrideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overrideButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(cashButton);
        cashButton.setSelected(true);
        cashButton.setText("Cash");
        cashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(creditButton);
        creditButton.setText("Credit");
        creditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(debitButton);
        debitButton.setText("Debit");
        debitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debitButtonActionPerformed(evt);
            }
        });

        paymentLabel1.setText("Total");

        paymentLabel2.setText("CardNum");

        paymentLabel3.setText("Pin/SecurityCode");

        enterPaymentButton.setText("Enter");
        enterPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterPaymentButtonActionPerformed(evt);
            }
        });

        suspendButton.setText("Suspend");
        suspendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suspendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(voidButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(productCodeInput)
                                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                    .addComponent(quantityInput, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(couponLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(couponCodeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(couponButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(creditButton)
                            .addComponent(cashButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paymentLabel1)
                            .addComponent(paymentInput1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paymentLabel2)
                            .addComponent(paymentInput2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(paymentLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(paymentInput3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(enterPaymentButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(suspendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(debitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(overrideButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(productCodeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(quantityInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addButton)
                                    .addComponent(voidButton))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(couponLabel)
                                    .addComponent(couponCodeInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(couponButton))
                            .addComponent(jScrollPane1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cashButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(creditButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 29, Short.MAX_VALUE)
                                .addComponent(suspendButton))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paymentLabel1)
                            .addComponent(paymentLabel2)
                            .addComponent(paymentLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paymentInput1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paymentInput3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paymentInput2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enterPaymentButton))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(debitButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(overrideButton)
                            .addComponent(closeButton))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int productCode= 0;
        int quantity = 0;
        try{
            productCode = Integer.parseInt(productCodeInput.getText());
            quantity = Integer.parseInt(quantityInput.getText());
            sale.processProduct(productCode, quantity);
        }catch(Exception e){
            if(productCode<=0){
                JOptionPane.showMessageDialog (null, "Please enter a valid product code", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }else if (quantity==0)
                JOptionPane.showMessageDialog (null, "Please enter a valid quantity", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        productCodeInput.setText("");
        quantityInput.setText("");
        console.setText(sale.display());
    }//GEN-LAST:event_addButtonActionPerformed

    private void voidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voidButtonActionPerformed
        int productCode = 0;
        int quantity = 0;
        try{
            productCode = Integer.parseInt(productCodeInput.getText());
            quantity = Integer.parseInt(quantityInput.getText());
            if(productCode<=0){
                JOptionPane.showMessageDialog (null, "Please enter a valid product code", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }else if (quantity==0)
                JOptionPane.showMessageDialog (null, "Please enter a valid quantity", "Invalid Input", JOptionPane.ERROR_MESSAGE);

            sale.processVoid(productCode, quantity);
        }catch(Exception e){
            if(productCode<=0){
                JOptionPane.showMessageDialog (null, "Please enter a valid product code", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }else if (quantity==0)
                JOptionPane.showMessageDialog (null, "Please enter a valid quantity", "Invalid Input", JOptionPane.ERROR_MESSAGE);

        }
        productCodeInput.setText("");
        quantityInput.setText("");
        console.setText(sale.display());
    }//GEN-LAST:event_voidButtonActionPerformed
    public void update(){
        console.setText(sale.display());
    }
    private void couponButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_couponButtonActionPerformed
        try{
            int code = Integer.parseInt(couponCodeInput.getText());
            sale.processCoupon(code);
        }catch(Exception e){
            JOptionPane.showMessageDialog (null, "Please enter a valid coupon code", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        couponCodeInput.setText(""); console.setText(sale.display());
       
    }//GEN-LAST:event_couponButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed

        couponCodeInput.setEnabled(false);
        productCodeInput.setEnabled(false);
        quantityInput.setEnabled(false);
        
        addButton.setEnabled(false);
        voidButton.setEnabled(false);
        couponButton.setEnabled(false);
        overrideButton.setEnabled(false);
        closeButton.setEnabled(false);
        
        cashButton.setEnabled(true);
        cashButton.setVisible(true);
        
        creditButton.setEnabled(true);
        creditButton.setVisible(true);
        
        debitButton.setEnabled(true);
        debitButton.setVisible(true);
        
        paymentLabel1.setEnabled(true);
        paymentLabel1.setVisible(true);
        paymentInput1.setEnabled(true);
        paymentInput1.setVisible(true);
        
        paymentLabel2.setVisible(true);
        paymentInput2.setVisible(true);
        
        paymentLabel3.setVisible(true);
        paymentInput3.setVisible(true);
        
        enterPaymentButton.setEnabled(true);
        enterPaymentButton.setVisible(true);
        
        // Give total price (subtotal, tax, and total)
        console.setText(sale.getTotals());
            
    }//GEN-LAST:event_closeButtonActionPerformed

    private void cashButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashButtonActionPerformed
        paymentLabel2.setEnabled(false);
        paymentInput2.setEnabled(false);
        
        paymentLabel3.setEnabled(false);
        paymentInput3.setEnabled(false);
    }//GEN-LAST:event_cashButtonActionPerformed

    private void enterPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterPaymentButtonActionPerformed
        Payment payment = null;
        Float pay=Float.parseFloat(paymentInput1.getText());
        float payCheck=(float) ((int)(pay*100)/100.0);
        if(!(payCheck==pay)){
           JOptionPane.showMessageDialog (null, "Invalid payment amount", "Invalid Input", JOptionPane.ERROR_MESSAGE);
           return;
        }
        try{    
            if(cashButton.isSelected()){
                payment = sale.processCashPayment(pay);
            }else if(creditButton.isSelected()){
                payment = sale.processCreditPayment(paymentInput2.getText(), paymentInput3.getText(),pay);
            }else if(debitButton.isSelected()){
                payment = sale.processDebitPayment(paymentInput2.getText(), Integer.parseInt(paymentInput3.getText()),pay);
            }else return;
        }catch(Exception e){
            
        }
        if(payment != null){
            DecimalFormat myFormatter = new DecimalFormat("0.00");
            float amount = Float.parseFloat(myFormatter.format(sale.getLeftToPay()));
            String output=String.format("Total: $%.2f", amount);
            console.setText(console.getText() + payment.toString() + output);
        }
        paymentInput1.setText("");
        paymentInput2.setText("");
        paymentInput3.setText("");
        if(sale.getLeftToPay() < .009){
            String receipt = sale.close();
            Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            JOptionPane.showMessageDialog(this, receipt, "Receipt", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image));
            previous.setVisible(true);
            this.dispose();
        }
            
    }//GEN-LAST:event_enterPaymentButtonActionPerformed

    private void overrideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overrideButtonActionPerformed
        if(previous.getRegisterController().getRegister().getCashier().getAccess() == Cashier.Access.Cashier)
            new LoginGui(true, this).setVisible(true);
        else
            new OverrideGUI(this).setVisible(true);
    }//GEN-LAST:event_overrideButtonActionPerformed

    private void creditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditButtonActionPerformed
        paymentLabel2.setEnabled(true);
        paymentInput2.setEnabled(true);
        
        paymentLabel3.setEnabled(true);
        paymentInput3.setEnabled(true);
    }//GEN-LAST:event_creditButtonActionPerformed

    private void debitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debitButtonActionPerformed
        paymentLabel2.setEnabled(true);
        paymentInput2.setEnabled(true);
        
        paymentLabel3.setEnabled(true);
        paymentInput3.setEnabled(true);
    }//GEN-LAST:event_debitButtonActionPerformed

    private void suspendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suspendButtonActionPerformed
        sale.processSuspend();
        previous.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_suspendButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JRadioButton cashButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextArea console;
    private javax.swing.JButton couponButton;
    private javax.swing.JTextField couponCodeInput;
    private javax.swing.JLabel couponLabel;
    private javax.swing.JRadioButton creditButton;
    private javax.swing.JRadioButton debitButton;
    private javax.swing.JButton enterPaymentButton;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton overrideButton;
    private javax.swing.JTextField paymentInput1;
    private javax.swing.JTextField paymentInput2;
    private javax.swing.JTextField paymentInput3;
    private javax.swing.JLabel paymentLabel1;
    private javax.swing.JLabel paymentLabel2;
    private javax.swing.JLabel paymentLabel3;
    private javax.swing.JTextField productCodeInput;
    private javax.swing.JTextField quantityInput;
    private javax.swing.JButton suspendButton;
    private javax.swing.JButton voidButton;
    // End of variables declaration//GEN-END:variables
}
