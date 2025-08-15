/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author papot
 */
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.DriverManager;
import model.Puesto;
import view.Comboitem;
import java.util.logging.Logger;
import java.util.logging.Level;

   public class CandidatosForm extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(CandidatosForm.class.getName());

    /**
     * Creates new form CandidatosForm
     */
    public CandidatosForm() {
        initComponents();
     
        cargarPuestos();
        
        Seleccionarbtn.setEnabled(false);
Masbtn.setEnabled(false);



Aplicantestbl.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        boolean filaSeleccionada = Aplicantestbl.getSelectedRow() != -1;
        Seleccionarbtn.setEnabled(filaSeleccionada);
        Masbtn.setEnabled(filaSeleccionada);
    }
});
    }

    // ------------------- Cargar puestos en el combo -------------------
    private void cargarPuestos() {
        // Limpiar el combo antes de cargar
        Puestosbox.removeAllItems();
        
        // Agregar opción por defecto
        Puestosbox.addItem(new Comboitem(0, "-- Seleccione un puesto --"));
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sistema_rrhh", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT id_puesto, nombre_puesto FROM puestos ORDER BY nombre_puesto");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Puestosbox.addItem(new Comboitem(rs.getInt("id_puesto"), rs.getString("nombre_puesto")));
            }
            
            System.out.println("Puestos cargados exitosamente. Total: " + (Puestosbox.getItemCount() - 1));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error cargando puestos: " + e.getMessage());
            System.out.println("Error en cargarPuestos: " + e.getMessage());
        }
    }

    // ------------------- Cargar aplicantes por puesto -------------------
    private void cargarAplicantesPorPuesto(int puestoId) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Cédula", "Nombre", "Apellido", "email", "Salario"});

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sistema_rrhh", "root", "");
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id_candidato, cedula, nombre, apellido, email, salario " +
                             "FROM candidatos WHERE puesto_id = ? AND (eliminado IS NULL OR eliminado = 0)")) {

            stmt.setInt(1, puestoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_candidato"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getBigDecimal("salario")
                });
            }

            Aplicantestbl.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error cargando aplicantes: " + e.getMessage());
        }
    }

    // ------------------- Pasar candidato a empleado -------------------
    private void convertirAEmpleado() {
        int fila = Aplicantestbl.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un candidato primero.");
            return;
        }

        int idCandidato = (int) Aplicantestbl.getValueAt(fila, 0);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sistema_rrhh", "root", "")) {
            conn.setAutoCommit(false);

            // Insertar en empleados
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO empleados (cedula, nombre_empleado, apellido_empleado, fecha_ingreso, puesto_id) " +
                            "SELECT cedula, nombre, apellido, CURDATE(), puesto_id FROM candidatos WHERE id_candidato = ?")) {
                stmt.setInt(1, idCandidato);
                stmt.executeUpdate();
            }

            // Marcar candidato como eliminado
            try (PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE candidatos SET eliminado = 1 WHERE id_candidato = ?")) {
                stmt.setInt(1, idCandidato);
                stmt.executeUpdate();
            }

            conn.commit();
            JOptionPane.showMessageDialog(this, "Candidato pasado a empleado correctamente.");

            // Refrescar la tabla
            Object selected = Puestosbox.getSelectedItem();
            if (selected instanceof Comboitem) {
                int puestoId = ((Comboitem) selected).getId();
                cargarAplicantesPorPuesto(puestoId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al pasar candidato: " + e.getMessage());
        }
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Aplicantestbl = new javax.swing.JTable();
        Puestosbox = Puestosbox = new javax.swing.JComboBox<Comboitem>();
        jLabel1 = new javax.swing.JLabel();
        Masbtn = new javax.swing.JButton();
        Seleccionarbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gestion de aplicantes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        Aplicantestbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Aplicantestbl);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 400, 200));

        Puestosbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PuestosboxActionPerformed(evt);
            }
        });
        jPanel1.add(Puestosbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 100, 20));

        jLabel1.setText("Puestos:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        Masbtn.setText("Mas info...");
        jPanel1.add(Masbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 90, -1));

        Seleccionarbtn.setText("Seleccionar");
        Seleccionarbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionarbtnActionPerformed(evt);
            }
        });
        jPanel1.add(Seleccionarbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PuestosboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PuestosboxActionPerformed
       Object selected = Puestosbox.getSelectedItem();

        if (selected instanceof Comboitem) {

            Comboitem item = (Comboitem) selected;

            cargarAplicantesPorPuesto(item.getId());

        }// TODO add your handling code here:
    }//GEN-LAST:event_PuestosboxActionPerformed

    private void SeleccionarbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionarbtnActionPerformed
    convertirAEmpleado();
}     

private void cargarCandidatosPorPuesto(String puesto) {
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[]{"ID", "Cédula", "Nombre", "Apellido", "email", "Salario"});

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sistema_rrhh", "root", "");
         PreparedStatement stmt = conn.prepareStatement(
             "SELECT c.id_candidato, c.cedula, c.nombre, c.apellido, c.email, c.salario " +
             "FROM candidatos c " +
             "JOIN puestos p ON c.puesto_id = p.id_puesto " +
             "WHERE p.nombre_puesto = ?"
         )) {

        stmt.setString(1, puesto);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_candidato"),
                rs.getString("cedula"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getBigDecimal("salario")
            });
        }

        Aplicantestbl.setModel(model);

    } catch (SQLException e) {
        logger.log(Level.SEVERE, "Error cargando candidatos", e);
    }    // TODO add your handling code here:
    }//GEN-LAST:event_SeleccionarbtnActionPerformed

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
            java.util.logging.Logger.getLogger(CandidatosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CandidatosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CandidatosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CandidatosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       java.awt.EventQueue.invokeLater(() -> new CandidatosForm().setVisible(true));
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Aplicantestbl;
    private javax.swing.JButton Masbtn;
    private javax.swing.JComboBox<Comboitem> Puestosbox;
    private javax.swing.JButton Seleccionarbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

