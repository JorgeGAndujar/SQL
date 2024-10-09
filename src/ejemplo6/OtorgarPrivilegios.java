package ejemplo6;

import java.sql.Connection;
import javax.swing.DefaultListModel;

public class OtorgarPrivilegios extends javax.swing.JFrame {

    Connection conexion = Metodos.obtenerConexion();
    DefaultListModel dlm1 = new DefaultListModel();
    DefaultListModel dlm2 = new DefaultListModel();

    public OtorgarPrivilegios() {
        initComponents();
        lstMostrarUsuarios.setModel(dlm1);
        lstPrivilegios.setModel(dlm2);
        cargarListaMostrarUsuarios();
        mostrarPrivilegios();
    }

    public void cargarListaMostrarUsuarios() {
        Metodos.showUsers(conexion, dlm1);
    }

    public void mostrarPrivilegios() {
        Metodos.mostrarPrivilegios(conexion, dlm2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstMostrarUsuarios = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstPrivilegios = new javax.swing.JList<>();
        cmdOtorgarPrivilegios = new javax.swing.JButton();
        cmdRefrescarUsuarios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ASIGNARLE PRIVILEGIOS A LOS USUARIOS");

        jScrollPane1.setViewportView(lstMostrarUsuarios);

        lstPrivilegios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "ALL", "SELECT  ", "INSERT ", "UPDATE    ", "DELETE  ", "CREATE", "DROP" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstPrivilegios);

        cmdOtorgarPrivilegios.setText("OTRORGAR PRIVILEGIOS");
        cmdOtorgarPrivilegios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOtorgarPrivilegiosActionPerformed(evt);
            }
        });

        cmdRefrescarUsuarios.setText("REFRESCAR USUARIOS");
        cmdRefrescarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefrescarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(cmdRefrescarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jScrollPane2)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(cmdOtorgarPrivilegios, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdOtorgarPrivilegios)
                    .addComponent(cmdRefrescarUsuarios))
                .addGap(0, 26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdOtorgarPrivilegiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOtorgarPrivilegiosActionPerformed
        int i = lstMostrarUsuarios.getSelectedIndex(); // Obtener el índice del usuario seleccionado
        if (i == -1) {
            System.out.println("No se ha seleccionado ningún usuario.");
            return; // Salir si no hay usuario seleccionado
        }

        String usuario = (String) dlm1.getElementAt(i); // Obtener el usuario seleccionado

        int[] privilegiosIndices = lstPrivilegios.getSelectedIndices(); // Obtener índices de privilegios seleccionados
        if (privilegiosIndices.length == 0) {
            System.out.println("No se han seleccionado privilegios.");
            return; // Salir si no hay privilegios seleccionados
        }

        String[] privilegiosUsuarios = new String[privilegiosIndices.length];
        int j = 0;

        // Recopilar los privilegios seleccionados
        for (int index : privilegiosIndices) {
            String privilegio = (String) dlm2.getElementAt(index); // Obtener privilegio
            privilegiosUsuarios[j] = privilegio; // Almacenar privilegio en el array
            j++;
        }

        // Imprimir los privilegios que se otorgarán
        System.out.println("Otorgando los siguientes privilegios al usuario: " + usuario);
        for (String privilegio : privilegiosUsuarios) {
            System.out.println(privilegio);
            // Aquí podrías agregar la lógica para otorgar el privilegio al usuario en la base de datos
            // por ejemplo: otorgarPrivilegio(usuario, privilegio);
        }
        Metodos.CrearPrivilegiosUsuarioNuevo(conexion, usuario, privilegiosUsuarios);

        // Opcionalmente, puedes mostrar un mensaje de éxito
        System.out.println("Privilegios otorgados con éxito.");
    }//GEN-LAST:event_cmdOtorgarPrivilegiosActionPerformed

    private void cmdRefrescarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefrescarUsuariosActionPerformed
        dlm1.clear();
        Metodos.showUsers(conexion, dlm1);
      
    }//GEN-LAST:event_cmdRefrescarUsuariosActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OtorgarPrivilegios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdOtorgarPrivilegios;
    private javax.swing.JButton cmdRefrescarUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstMostrarUsuarios;
    private javax.swing.JList<String> lstPrivilegios;
    // End of variables declaration//GEN-END:variables

}
