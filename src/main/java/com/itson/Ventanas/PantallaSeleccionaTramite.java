/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.itson.Ventanas;

import com.itson.Exceptions.AlreadyPaidException;
import com.itson.Utilidades.EncriptadorSecreto;
import static com.itson.Ventanas.Proyecto02BasesDeDatosAvanzadas.entityManager;
import static com.itson.Ventanas.Proyecto02BasesDeDatosAvanzadas.mainScreen;
import com.itson.daos.LicenciaDAO;
import com.itson.daos.PagoDAO;
import com.itson.daos.PlacaDAO;
import com.itson.daos.PrecioLicenciaDAO;
import com.itson.daos.PrecioPlacaDAO;
import com.itson.daos.TramiteDAO;
import com.itson.dominio.Licencia;
import com.itson.dominio.Persona;
import com.itson.dominio.Placa;
import com.itson.dominio.Tramite;
import com.itson.dominio.Vigencia;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.EntityNotFoundException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Erick
 */
public class PantallaSeleccionaTramite extends javax.swing.JFrame {

    private ArrayList<Tramite> listaTramites;
    private ArrayList<Licencia> listaLicencias;
    private ArrayList<Placa> listaPlacas;
    private Tramite selectedTramite;
    private Persona selectedPersona;
    private int indexTipo;
    private String accion;
    private EncriptadorSecreto encriptador = new EncriptadorSecreto();
    private PrecioLicenciaDAO precioLicenciaDAO= new PrecioLicenciaDAO();
    private PrecioPlacaDAO precioPlacaDAO= new PrecioPlacaDAO();
    
    public PantallaSeleccionaTramite() {
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTramites = new javax.swing.JList<>();
        btnEscoger = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        comboBoxTramite = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        datePicker2 = new com.github.lgooddatepicker.components.DatePicker();
        btnEscogerPersona = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Selecciona Tramite");
        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N

        jScrollPane1.setViewportView(jListTramites);

        btnEscoger.setText("Escoger");
        btnEscoger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscogerActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setText("Persona:");
        jLabel2.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N

        jLabel4.setText("Tipo de tramite");
        jLabel4.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N

        comboBoxTramite.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Licencias","Placas", "Todos" }));
        comboBoxTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTramiteActionPerformed(evt);
            }
        });

        jLabel5.setText("Periodo:");
        jLabel5.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N

        jLabel6.setText("Inicio del periodo:");
        jLabel6.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

        jLabel7.setText("Fin del periodo:");
        jLabel7.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N

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
                .addGap(83, 83, 83)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(btnEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(comboBoxTramite, 0, 315, Short.MAX_VALUE)
                            .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(btnEscogerPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(284, 284, 284))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEscogerPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxTramite, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEscoger, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void setAccion(String accion) {
        this.accion = accion;
    }

    private void btnEscogerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscogerActionPerformed

        TramiteDAO tramiteDAO = new TramiteDAO();

        if (!jListTramites.isSelectionEmpty()) {
            String stringTramite = jListTramites.getSelectedValue();
            String[] parts = stringTramite.split("\\|");
            Long index = Long.parseLong(parts[0]);

            selectedTramite = tramiteDAO.query(entityManager, index);

            String msgConfirmación = "";

            if (accion.equals("Pago")) {
                msgConfirmación = "¿Seguro de que quiere realizar el pago?";
            } else if (accion.equals("Cancelación")) {
                msgConfirmación = "¿Seguro de que quiere cancelar el pago?";
            }
            int input = JOptionPane.showConfirmDialog(null, msgConfirmación);
            if (input == 0) {
                double costo = 0.0;
                PagoDAO pagoDAO = new PagoDAO();
                try {
                    if (accion.equals("Pago")) {
                        if (selectedTramite.getClass().equals(Licencia.class)) {
                            Licencia licencia = (Licencia) selectedTramite;
                            if (licencia.getPersona().isDiscapacidad()) {
                                costo = precioLicenciaDAO.getPrecioLicencia(entityManager, licencia.getVigencia()).getPrecioDiscapacidad();
                            } else {
                                costo = precioLicenciaDAO.getPrecioLicencia(entityManager, licencia.getVigencia()).getPrecioNormal();
                            }
                        }
                        if (selectedTramite.getClass().equals(Placa.class)) {
                            Placa placa = (Placa) selectedTramite;
                            if (placa.getVehiculo().getPlacas().size() == 1 && placa.getVehiculo().getPlacas().size() > 0) {
                                costo = precioPlacaDAO.getPrecioPlaca(entityManager).getPrecioVehiculoNuevo();
                            } else if (placa.getVehiculo().getPlacas().size() > 0) {
                                costo = precioPlacaDAO.getPrecioPlaca(entityManager).getPrecioVehiculoViejo();
                            }
                        }
                        pagoDAO.insert(entityManager, pagoDAO.create(costo, LocalDate.now(), selectedTramite), tramiteDAO);
                        showMessageDialog(null, "Pago registrado exitosamente ^^");
                    } else if (accion.equals("Cancelación")) {
                        pagoDAO.delete(entityManager, selectedTramite.getPago().getId());
                        showMessageDialog(null, "Pago cancelado exitosamente :c");
                    }
                } catch (AlreadyPaidException e) {
                    showMessageDialog(null, "El procedimiento ya se había pagado ^^");
                } catch (NullPointerException e) {
                    System.out.println(e.toString());
                    showMessageDialog(null, "El procedimiento no se ha pagado");
                }
                mainScreen.setVisible(true);
                this.dispose();

            }
        } else {

            showMessageDialog(null, "Escoja a un tramite");
        }

    }//GEN-LAST:event_btnEscogerActionPerformed

    public void getPersona(PantallaSeleccionaPersona pantallaSeleccionPersona) {
        selectedPersona = pantallaSeleccionPersona.sendPersona();
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        DefaultListModel<String> model = new DefaultListModel<>();

        LicenciaDAO licenciaDAO = new LicenciaDAO();
        PlacaDAO placaDAO = new PlacaDAO();

        indexTipo = comboBoxTramite.getSelectedIndex();
        try {
            switch (indexTipo) {
                case 0:
                    listaLicencias = licenciaDAO.getListaLicencias(entityManager, null, selectedPersona, null, null, null);
                    for (Licencia licencia : listaLicencias) {
                        if (accion.equals("Pago")) {
                            if (licencia.getPago() == null) {
                                model.addElement(licencia.getId()+"|"+encriptador.desencriptar(licencia.getPersona().getNombre()) + "|" + licencia.getVigencia().toString());
                            }
                        } else if (accion.equals("Cancelación")) {
                            if (licencia.getPago() != null) {
                                model.addElement(licencia.getId()+"|"+encriptador.desencriptar(licencia.getPersona().getNombre()) + "|" + licencia.getVigencia().toString());
                            }
                        }
                    }
                    break;
                case 1:
                    listaTramites = licenciaDAO.getListaTramites(entityManager, null, selectedPersona, null, null, null);
                    for (Tramite tramite : listaTramites) {
                        if (tramite.getClass().equals(Placa.class)) {
                            Placa placa = (Placa) tramite;
                            if (accion.equals("Pago")) {
                                if (tramite.getPago() == null) {
                                    model.addElement(placa.getId() + "|" + placa.getMatricula() + "|" + placa.getFechaRecepcion());
                                }
                            } else if (accion.equals("Cancelación")) {
                                if (placa.getPago() != null) {
                                    model.addElement(placa.getId() + "|" + placa.getMatricula() + "|" + placa.getFechaRecepcion());
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    listaTramites = licenciaDAO.getListaTramites(entityManager, null, selectedPersona, null, null, null);
                    for (Tramite tramite : listaTramites) {
                        if (accion.equals("Pago")) {
                            if (tramite.getPago() == null) {
                                if (tramite.getClass().equals(Placa.class)) {
                                    Placa placa = (Placa) tramite;
                                    model.addElement(placa.getId() + "|" + placa.getMatricula() + "|" + placa.getFechaRecepcion());
                                } else if (tramite.getClass().equals(Licencia.class)) {
                                    Licencia licencia = (Licencia) tramite;
                                    model.addElement(licencia.getId() + "|" + encriptador.desencriptar(licencia.getPersona().getNombre()) + "|" + licencia.getVigencia().toString());
                                }

                            }
                        } else if (accion.equals("Cancelación")) {
                            if (tramite.getPago() != null) {
                                if (tramite.getClass().equals(Placa.class)) {
                                    Placa placa = (Placa) tramite;
                                    model.addElement(placa.getId()+"|"+placa.getMatricula() + "|" + placa.getFechaRecepcion());
                                } else if (tramite.getClass().equals(Licencia.class)) {
                                    Licencia licencia = (Licencia) tramite;
                                    model.addElement(licencia.getId()+"|"+encriptador.desencriptar(licencia.getPersona().getNombre()) + "|" + licencia.getVigencia().toString());
                                }
                            }
                            
                        }
                    }   break;
                default:
                    break;
            }
            jListTramites.setModel(model);
            if (jListTramites.getModel().getSize()==0){
                throw new EntityNotFoundException ("No se encontraron tramites con esos datos");
            }
        } catch (EntityNotFoundException e) {
            showMessageDialog(null, "No se encontraron tramites con esos datos");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEscogerPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscogerPersonaActionPerformed

        PantallaSeleccionaPersona pantallaUsuario = new PantallaSeleccionaPersona(this);
        pantallaUsuario.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_btnEscogerPersonaActionPerformed

    private void comboBoxTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTramiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTramiteActionPerformed

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
            java.util.logging.Logger.getLogger(PantallaSeleccionaTramite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaSeleccionaTramite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaSeleccionaTramite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaSeleccionaTramite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaSeleccionaTramite().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEscoger;
    private javax.swing.JButton btnEscogerPersona;
    private com.github.lgooddatepicker.components.CalendarPanel calendarPanel1;
    private javax.swing.JComboBox<String> comboBoxTramite;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private com.github.lgooddatepicker.components.DatePicker datePicker2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    protected javax.swing.JList<String> jListTramites;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
