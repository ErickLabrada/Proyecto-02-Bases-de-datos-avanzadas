/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.itson.Ventanas;

import com.itson.Utilidades.EncriptadorSecreto;
import static com.itson.Ventanas.Proyecto02BasesDeDatosAvanzadas.entityManager;
import static com.itson.Ventanas.Proyecto02BasesDeDatosAvanzadas.mainScreen;
import com.itson.daos.TramiteDAO;
import com.itson.dominio.Persona;
import com.itson.daos.PersonaDAO;
import com.itson.daos.PlacaDAO;
import com.itson.dominio.Licencia;
import com.itson.dominio.Placa;
import com.itson.dominio.Tramite;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Erick
 */
public class PantallaHistorial extends javax.swing.JFrame {

    private Persona persona=null;
    private PersonaDAO personaDAO = new PersonaDAO();
    private TramiteDAO tramiteDAO = new TramiteDAO();
    private EncriptadorSecreto encriptador = new EncriptadorSecreto();
    private PlacaDAO placaDAO = new PlacaDAO();

    /**
     * Creates new form PantallaHistorial
     */
    public PantallaHistorial() {
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

        calendarPanel1 = new com.github.lgooddatepicker.components.CalendarPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTramites = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPersona = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnEscogerPersona = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableTramites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tramite", "Info", "Fecha", "Pago", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableTramites);

        jLabel1.setText("HISTORIAL");
        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N

        jTextFieldPersona.setEditable(false);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel2.setText("Persona:");
        jLabel2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEscogerPersona.setText("Escoger persona");
        btnEscogerPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscogerPersonaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldPersona, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(btnEscogerPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEscogerPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        mainScreen.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnRegresarActionPerformed

    public void getPersona(PantallaSeleccionaPersona pantallaSeleccionPersona) {
        persona = pantallaSeleccionPersona.sendPersona();
        jTextFieldPersona.setText(encriptador.desencriptar(persona.getNombre()));

    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        ArrayList <Tramite> tramites = tramiteDAO.getListaTramites(entityManager, null, persona, null, null, null);
        DefaultTableModel model = (DefaultTableModel) jTableTramites.getModel();
        model.setRowCount(0);
        Object[] row = new Object[45];
        String estado;
        for (Tramite tramite : tramites) {
            if (tramite.getClass().equals(Licencia.class)) {
                Licencia licencia = (Licencia) tramite;
                row[0] = "Licencia";
                row[1] = encriptador.desencriptar(licencia.getPersona().getNombre()) + licencia.getPersona().getRfc();
                row[2] = licencia.getPago().getFechaPago();
                row[3] = licencia.getPago().getMonto();
                if (licencia.isEstado()){
                    estado = "Activa";
                } else{
                    estado = "Inactiva";
                }
                row[5] = estado;
                model.addRow(row);
            } else if (tramite.getClass().equals(Placa.class)) {
                Placa placa = (Placa) tramite;
                row[0] = "Placa";
                row[1] = placa.getMatricula();
                row[2] = tramite.getPago().getFechaPago();
                row[3] = tramite.getPago().getMonto();
                 if (placa.isEstado()){
                    estado = "Activa";
                } else{
                    estado = "Inactiva";
                }
                row[5] = estado;
                model.addRow(row);
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEscogerPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscogerPersonaActionPerformed

        PantallaSeleccionaPersona pantallaSeleccionPersona = new PantallaSeleccionaPersona(this);
         pantallaSeleccionPersona.setVisible(true);
         this.setVisible(false);

    }//GEN-LAST:event_btnEscogerPersonaActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaHistorial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEscogerPersona;
    private javax.swing.JButton btnRegresar;
    private com.github.lgooddatepicker.components.CalendarPanel calendarPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTramites;
    private javax.swing.JTextField jTextFieldPersona;
    // End of variables declaration//GEN-END:variables
}
