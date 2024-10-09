/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ejemplo6;

import java.sql.*;
import javax.swing.DefaultListModel;

public class MostrarPrivilegios extends javax.swing.JFrame {

    Connection conexion = Metodos.obtenerConexion();
    DefaultListModel dlm1 = new DefaultListModel();
    DefaultListModel dlm2 = new DefaultListModel();

    public MostrarPrivilegios() {
        initComponents();
        lstMostrarUsuarios.setModel(dlm1);
        lstMostrarPrivilegios.setModel(dlm2);
        cargarListaMostrarUsuarios();
    }

    public void cargarListaMostrarUsuarios() {
        Metodos.showUsers(conexion, dlm1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstMostrarUsuarios = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstMostrarPrivilegios = new javax.swing.JList<>();
        cmdRefrescarUsuarios = new javax.swing.JButton();
        cmdMostrarPrivilegios = new javax.swing.JButton();
        cmdRefrescarPrivilegios = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MOSTRAR  LOS PRIVILEGIOS");

        jScrollPane1.setViewportView(lstMostrarUsuarios);

        jScrollPane2.setViewportView(lstMostrarPrivilegios);

        cmdRefrescarUsuarios.setText("REFRESCAR USUARIOS");
        cmdRefrescarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefrescarUsuariosActionPerformed(evt);
            }
        });

        cmdMostrarPrivilegios.setText("MOSTRAR PRIVILEGIOS");
        cmdMostrarPrivilegios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMostrarPrivilegiosActionPerformed(evt);
            }
        });

        cmdRefrescarPrivilegios.setText("REFRESCAR PRIVILEGIOS");
        cmdRefrescarPrivilegios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefrescarPrivilegiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdRefrescarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmdRefrescarPrivilegios, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmdMostrarPrivilegios, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdRefrescarUsuarios)
                    .addComponent(cmdMostrarPrivilegios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(cmdRefrescarPrivilegios)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdRefrescarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefrescarUsuariosActionPerformed
        dlm1.clear();
        Metodos.showUsers(conexion, dlm1);

    }//GEN-LAST:event_cmdRefrescarUsuariosActionPerformed

    private void cmdMostrarPrivilegiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMostrarPrivilegiosActionPerformed
        int i = lstMostrarUsuarios.getSelectedIndex(); // Obtener el índice del usuario seleccionado
        if (i == -1) {
            System.out.println("No se ha seleccionado ningún usuario.");
            return; // Salir si no hay usuario seleccionado
        }

        String usuario = (String) dlm1.getElementAt(i); // Obtener el usuario seleccionado
        String query = "SHOW GRANTS FOR '" + usuario + "'@'localhost'"; // Corregido para usar comillas

        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery(); // Ejecutar la consulta

            while (rs.next()) {
                String privilegioCompleto = rs.getString(1); // Obtener el privilegio completo

                // Extraer solo la parte entre "GRANT" y "ON"
                String[] partes = privilegioCompleto.split(" ");
                int grantIndex = -1;
                int onIndex = -1;

                // Buscar índices de "GRANT" y "ON"
                for (int j = 0; j < partes.length; j++) {
                    if (partes[j].equalsIgnoreCase("GRANT")) {
                        grantIndex = j;
                    } else if (partes[j].equalsIgnoreCase("ON")) {
                        onIndex = j;
                        break; // Salir del bucle una vez que encontramos "ON"
                    }
                }

                // Si encontramos "GRANT" y "ON", extraer el texto entre ellos
                if (grantIndex != -1 && onIndex != -1 && onIndex > grantIndex + 1) {
                    StringBuilder privilegioExtraido = new StringBuilder();
                    for (int k = grantIndex + 1; k < onIndex; k++) {
                        privilegioExtraido.append(partes[k]).append(" ");
                    }
                    dlm2.clear();
                    dlm2.addElement(privilegioExtraido.toString().trim()); // Agregar al modelo
                }
            }

            // Actualizar la lista con los privilegios extraídos
            //lstMostrarPrivilegios.setModel(modeloPrivilegios);
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }//GEN-LAST:event_cmdMostrarPrivilegiosActionPerformed

    private void cmdRefrescarPrivilegiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefrescarPrivilegiosActionPerformed

        System.out.println("entro");
        dlm2.clear();


    }//GEN-LAST:event_cmdRefrescarPrivilegiosActionPerformed

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
            java.util.logging.Logger.getLogger(MostrarPrivilegios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MostrarPrivilegios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MostrarPrivilegios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MostrarPrivilegios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MostrarPrivilegios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdMostrarPrivilegios;
    private javax.swing.JButton cmdRefrescarPrivilegios;
    private javax.swing.JButton cmdRefrescarUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstMostrarPrivilegios;
    private javax.swing.JList<String> lstMostrarUsuarios;
    // End of variables declaration//GEN-END:variables
}
