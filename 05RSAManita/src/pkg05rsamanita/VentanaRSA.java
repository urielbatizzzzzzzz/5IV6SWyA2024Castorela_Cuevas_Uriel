/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg05rsamanita;
import java.math.BigInteger;
import java.util.Arrays;
 import pkg05rsamanita.Main;
/**
 *
 * @author 52736
 */
public class VentanaRSA extends javax.swing.JFrame {
    Main mainObj= new Main(SOMEBITS);
    String mensaje,clavePublica,clavePrivada,texto,cifradoString;
    String[] cifradoArray;
    public VentanaRSA() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextValor1 = new javax.swing.JTextField();
        encriptarBtn = new javax.swing.JButton();
        desencryptarBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        clavesBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        copiarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextValor1.setText("Escribe tu mensaje");

        encriptarBtn.setText("Encryptar");
        encriptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encriptarBtnActionPerformed(evt);
            }
        });

        desencryptarBtn.setText("Desencriptar");
        desencryptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desencryptarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(encriptarBtn)
                        .addGap(44, 44, 44)
                        .addComponent(desencryptarBtn))
                    .addComponent(jTextValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jTextValor1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encriptarBtn)
                    .addComponent(desencryptarBtn))
                .addGap(47, 47, 47))
        );

        jLabel1.setText("Claves");

        clavesBtn.setText("Generar Claves");
        clavesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavesBtnActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        copiarBtn.setText("Copiar Texto");
        copiarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(copiarBtn))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(clavesBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(copiarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clavesBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void desencryptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desencryptarBtnActionPerformed
         // Validar si las claves han sido generadas antes de descifrar
    if (mainObj.n == null || mainObj.d == null) {
        jTextValor1.setText("Genera las claves antes de descifrar.");
        return; // Salir del método si las claves no están generadas
    }

    cifradoString = jTextValor1.getText();
    cifradoArray = cifradoString.replaceAll("\\[|\\]", "").split(", ");
    BigInteger[] cifrado = new BigInteger[cifradoArray.length];

    try {
        for (int i = 0; i < cifradoArray.length; i++) {
            cifrado[i] = new BigInteger(cifradoArray[i]);
        }

        String mensajeDescifrado = mainObj.descifrar(cifrado);
        jTextArea1.setText(mensajeDescifrado);
    } catch (NumberFormatException e) {
        // Manejar la excepción de formato numérico (por ejemplo, mostrar un mensaje de error)
        jTextValor1.setText("Error: El texto cifrado contiene valores no válidos.");
    }
    }//GEN-LAST:event_desencryptarBtnActionPerformed

    private void clavesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clavesBtnActionPerformed
        mainObj.generarPrimos();
        mainObj.generarClaves();
         clavePublica = "e: " + mainObj.e.toString() + ", n: " + mainObj.n.toString();
         clavePrivada = "d: " + mainObj.d.toString() + ", n: " + mainObj.n.toString();
         jTextValor1.setText("");
        jLabel1.setText("Se generaron las claves");
    }//GEN-LAST:event_clavesBtnActionPerformed

    private void encriptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encriptarBtnActionPerformed
       // Validar si las claves han sido generadas antes de cifrar
    if (mainObj.n == null || mainObj.e == null) {
        jTextValor1.setText("Genera las claves antes de cifrar.");
        return; // Salir del método si las claves no están generadas
    }
    // Cifrar y mostrar información en la interfaz
    mensaje = jTextValor1.getText();
    if(mensaje.equals("")){
        jTextValor1.setText("Debe de existir texto.");
        return;
    }
    BigInteger[] cifrado = mainObj.cifrar(mensaje);
    jTextArea1.setText(Arrays.toString(cifrado));
    }//GEN-LAST:event_encriptarBtnActionPerformed

    private void copiarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarBtnActionPerformed
        texto = jTextArea1.getText();
    if (texto.length() == 0) {
        jTextValor1.setText("No existe texto");
        return; // Salir del método si no hay texto para copiar
    }
    jTextValor1.setText("");

    java.awt.datatransfer.StringSelection stringSelection = new java.awt.datatransfer.StringSelection(texto);
    java.awt.datatransfer.Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
    }//GEN-LAST:event_copiarBtnActionPerformed
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaRSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRSA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaRSA().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clavesBtn;
    private javax.swing.JButton copiarBtn;
    private javax.swing.JButton desencryptarBtn;
    private javax.swing.JButton encriptarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextValor1;
    // End of variables declaration//GEN-END:variables
}