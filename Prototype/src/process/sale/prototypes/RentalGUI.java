/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package process.sale.prototypes;

import javax.swing.JOptionPane;

/**
 *
 * @author Pikachu
 */
public class RentalGUI extends javax.swing.JFrame {

    /**
     * Creates new form SaleGUI
     */
    MainGui previous;
    private RentalController rental;
    public RentalGUI(MainGui prev) {
        previous=prev;
        rental = (RentalController)previous.getRegisterController().getCurrentTransaction();
        initComponents();
        console.setText(rental.display());
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        cashButton = new javax.swing.JRadioButton();
        creditButton = new javax.swing.JRadioButton();
        debitButton = new javax.swing.JRadioButton();
        dayslabel = new javax.swing.JLabel();
        daysInput = new javax.swing.JTextField();
        paymentInput2 = new javax.swing.JTextField();
        paymentLabel3 = new javax.swing.JLabel();
        paymentInput3 = new javax.swing.JTextField();
        enterPaymentButton = new javax.swing.JButton();
        paymentInput1 = new javax.swing.JTextField();
        paymentLabel1 = new javax.swing.JLabel();
        paymentLabel2 = new javax.swing.JLabel();
        quitButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        overrideButton = new javax.swing.JButton();
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

        console.setEditable(false);
        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

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

        dayslabel.setText("Days");

        paymentLabel3.setText("Pin/SecurityCode");

        enterPaymentButton.setText("Enter");
        enterPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterPaymentButtonActionPerformed(evt);
            }
        });

        paymentLabel1.setText("Total");

        paymentLabel2.setText("CardNum");

        quitButton.setText("Quit");

        closeButton.setText("Pay");

        overrideButton.setText("Override");

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
                        .addGap(18, 30, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(voidButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dayslabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(productCodeInput)
                                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                .addComponent(quantityInput, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(daysInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(debitButton)
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
                                    .addComponent(paymentLabel3)
                                    .addComponent(paymentInput3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enterPaymentButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(quitButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(suspendButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(closeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(overrideButton)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(dayslabel)
                                    .addComponent(daysInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(voidButton)
                                    .addComponent(addButton)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cashButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(creditButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(suspendButton)
                                    .addComponent(quitButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(overrideButton)
                                    .addComponent(closeButton)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(paymentLabel1)
                                    .addComponent(paymentLabel2)
                                    .addComponent(paymentLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(paymentInput1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(paymentInput3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(paymentInput2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(enterPaymentButton))
                        .addGap(1, 1, 1)
                        .addComponent(debitButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void debitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debitButtonActionPerformed
        paymentLabel2.setEnabled(true);
        paymentInput2.setEnabled(true);

        paymentLabel3.setEnabled(true);
        paymentInput3.setEnabled(true);
    }//GEN-LAST:event_debitButtonActionPerformed

    private void creditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditButtonActionPerformed
        paymentLabel2.setEnabled(true);
        paymentInput2.setEnabled(true);

        paymentLabel3.setEnabled(true);
        paymentInput3.setEnabled(true);
    }//GEN-LAST:event_creditButtonActionPerformed

    private void cashButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashButtonActionPerformed
        paymentLabel2.setEnabled(false);
        paymentInput2.setEnabled(false);

        paymentLabel3.setEnabled(false);
        paymentInput3.setEnabled(false);
    }//GEN-LAST:event_cashButtonActionPerformed

    private void voidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voidButtonActionPerformed
        try{
            int productCode = Integer.parseInt(productCodeInput.getText());
            int quantity = Integer.parseInt(quantityInput.getText());
            int days = Integer.parseInt(daysInput.getText());
            rental.processVoid(productCode, quantity, days);
        }catch(Exception e){
        }
        productCodeInput.setText("");
        quantityInput.setText("");
        console.setText(rental.display());
    }//GEN-LAST:event_voidButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try{
            int productCode = Integer.parseInt(productCodeInput.getText());
            int quantity = Integer.parseInt(quantityInput.getText());
            int days = Integer.parseInt(daysInput.getText());
            rental.processProduct(productCode, quantity, days);
        }catch(Exception e){
        }
        productCodeInput.setText("");
        quantityInput.setText("");
        console.setText(rental.display());
    }//GEN-LAST:event_addButtonActionPerformed

    private void enterPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterPaymentButtonActionPerformed
        Payment payment = null;
        try{
            if(cashButton.isSelected()){
                payment = rental.processCashPayment(Integer.parseInt(paymentInput1.getText()));
            }else if(creditButton.isSelected()){
                payment = rental.processCreditPayment(paymentInput2.getText(), paymentInput3.getText(),Float.parseFloat(paymentInput1.getText()));
            }else if(debitButton.isSelected()){
                payment = rental.processDebitPayment(paymentInput2.getText(), Integer.parseInt(paymentInput3.getText()),Float.parseFloat(paymentInput1.getText()));
            }else return;
        }catch(Exception e){

        }
        if(payment != null)
        console.setText(console.getText() + payment.toString() + "Total: " + rental.getLeftToPay());
        paymentInput1.setText("");
        paymentInput2.setText("");
        paymentInput3.setText("");
        if(rental.getLeftToPay() < .01){
            String receipt = rental.close();
            JOptionPane.showMessageDialog(this, receipt, "Receipt", JOptionPane.INFORMATION_MESSAGE);
            previous.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_enterPaymentButtonActionPerformed

    private void suspendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suspendButtonActionPerformed
        rental.processSuspend();
        previous.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_suspendButtonActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SaleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SaleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SaleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SaleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SaleGUI().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JRadioButton cashButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextArea console;
    private javax.swing.JRadioButton creditButton;
    private javax.swing.JTextField daysInput;
    private javax.swing.JLabel dayslabel;
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
    private javax.swing.JButton quitButton;
    private javax.swing.JButton suspendButton;
    private javax.swing.JButton voidButton;
    // End of variables declaration//GEN-END:variables
}
