/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.artairoga.tfg.Interfaces;

import com.artairoga.tfg.GestionBBDD.AnimalesDAO;
import com.artairoga.tfg.GestionBBDD.CitasDAO;
import com.artairoga.tfg.GestionBBDD.ClientesDAO;
import com.artairoga.tfg.GestionBBDD.ConexionBD;
import com.artairoga.tfg.GestionBBDD.Observers.AnimalesObserver;
import com.artairoga.tfg.GestionBBDD.Observers.CitasObserver;
import com.artairoga.tfg.GestionBBDD.Observers.ClientesObserver;
import com.artairoga.tfg.Interfaces.Animales.*;
import com.artairoga.tfg.Interfaces.Citas.CitaAlta;
import com.artairoga.tfg.Interfaces.Citas.CitaBaja;
import com.artairoga.tfg.Interfaces.Citas.CitaModificar;
import com.artairoga.tfg.Interfaces.Clientes.*;
import com.artairoga.tfg.Modelos.Animal;
import com.artairoga.tfg.Modelos.Cita;
import com.artairoga.tfg.Modelos.Cliente;

import java.net.ConnectException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author artai
 */
public class Principal extends javax.swing.JFrame implements CitasObserver, AnimalesObserver, ClientesObserver {

    /**
     * Creates new form Test
     */
    public Principal() {
        initComponents();
        //Inicializar la conexion
        conexion = ConexionBD.getInstancia().getConexion();
        //Inicializar los DAO
        tableModel = (DefaultTableModel) jTableCitas.getModel();
        try {
            URL helpURL = this.getClass().getResource("/help/help_set.hs");
            HelpSet helpSet = new HelpSet(null, helpURL);
            HelpBroker helpBroker = helpSet.createHelpBroker();
            helpBroker.enableHelpOnButton(jMenuItemAyuda, "principal", helpSet);
            helpBroker.enableHelpKey(getRootPane(), "principal", helpSet);
        } catch (HelpSetException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (conexion != null) {
            inicializarDAO();
            cargarTabla();
        }
    }

    private void inicializarDAO() {
        animalesDAO = AnimalesDAO.getInstance(conexion);
        clientesDAO = ClientesDAO.getInstance(conexion);
        citasDAO = CitasDAO.getInstance(conexion);
        animalesDAO.agregarObservador(this);
        clientesDAO.agregarObservador(this);
        citasDAO.agregarObservador(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCitas = new javax.swing.JTable();
        jLabelCitas = new javax.swing.JLabel();
        jButtonNuevaCita = new javax.swing.JButton();
        jButtonBorrarCita = new javax.swing.JButton();
        jButtonEditarCita = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuAnimales = new javax.swing.JMenu();
        jMenuItemAnimalesAlta = new javax.swing.JMenuItem();
        jMenuItemAnimalesBaja = new javax.swing.JMenuItem();
        jMenuItemAnimalesLista = new javax.swing.JMenuItem();
        jMenuItemAnimalesModificacion = new javax.swing.JMenuItem();
        jMenuClientes = new javax.swing.JMenu();
        jMenuItemClientesAlta = new javax.swing.JMenuItem();
        jMenuItemClientesBaja = new javax.swing.JMenuItem();
        jMenuItemClientesLista = new javax.swing.JMenuItem();
        jMenuItemClientesModificar = new javax.swing.JMenuItem();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuItemAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Animal", "Cliente", "Descripcion", "Pendiente"
            }
        ));
        jScrollPane1.setViewportView(jTableCitas);

        jLabelCitas.setText("Citas:");

        jButtonNuevaCita.setText("Nueva Cita");
        jButtonNuevaCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaCitaActionPerformed(evt);
            }
        });

        jButtonBorrarCita.setText("Borrar Cita");
        jButtonBorrarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarCitaActionPerformed(evt);
            }
        });

        jButtonEditarCita.setText("Editar Cita");
        jButtonEditarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarCitaActionPerformed(evt);
            }
        });

        jMenuAnimales.setText("Animales");

        jMenuItemAnimalesAlta.setText("Alta");
        jMenuItemAnimalesAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAnimalesAltaActionPerformed(evt);
            }
        });
        jMenuAnimales.add(jMenuItemAnimalesAlta);

        jMenuItemAnimalesBaja.setText("Baja");
        jMenuItemAnimalesBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAnimalesBajaActionPerformed(evt);
            }
        });
        jMenuAnimales.add(jMenuItemAnimalesBaja);

        jMenuItemAnimalesLista.setText("Lista");
        jMenuItemAnimalesLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAnimalesListaActionPerformed(evt);
            }
        });
        jMenuAnimales.add(jMenuItemAnimalesLista);

        jMenuItemAnimalesModificacion.setText("Modificar");
        jMenuItemAnimalesModificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAnimalesModificacionActionPerformed(evt);
            }
        });
        jMenuAnimales.add(jMenuItemAnimalesModificacion);

        jMenuBar1.add(jMenuAnimales);

        jMenuClientes.setText("Clientes");

        jMenuItemClientesAlta.setText("Alta");
        jMenuItemClientesAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesAltaActionPerformed(evt);
            }
        });
        jMenuClientes.add(jMenuItemClientesAlta);

        jMenuItemClientesBaja.setText("Baja");
        jMenuItemClientesBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesBajaActionPerformed(evt);
            }
        });
        jMenuClientes.add(jMenuItemClientesBaja);

        jMenuItemClientesLista.setText("Lista");
        jMenuItemClientesLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesListaActionPerformed(evt);
            }
        });
        jMenuClientes.add(jMenuItemClientesLista);

        jMenuItemClientesModificar.setText("Modificar");
        jMenuItemClientesModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesModificarActionPerformed(evt);
            }
        });
        jMenuClientes.add(jMenuItemClientesModificar);

        jMenuBar1.add(jMenuClientes);

        jMenuAyuda.setText("Ayuda");

        jMenuItemAyuda.setText("Ayuda");
        jMenuAyuda.add(jMenuItemAyuda);

        jMenuBar1.add(jMenuAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabelCitas)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNuevaCita, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonBorrarCita, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEditarCita, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCitas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNuevaCita)
                    .addComponent(jButtonBorrarCita)
                    .addComponent(jButtonEditarCita))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAnimalesAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAnimalesAltaActionPerformed
        if (animalesAlta != null) {
            animalesAlta.dispose();
        }
        animalesAlta = new AnimalesAlta(this, false);
        animalesAlta.setVisible(true);
    }//GEN-LAST:event_jMenuItemAnimalesAltaActionPerformed

    private void jMenuItemAnimalesBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAnimalesBajaActionPerformed
        if (animalesBaja != null) {
            animalesBaja.dispose();
        }
        animalesBaja = new AnimalesBaja(this, false);
        animalesBaja.setVisible(true);
    }//GEN-LAST:event_jMenuItemAnimalesBajaActionPerformed

    private void jMenuItemAnimalesListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAnimalesListaActionPerformed
        if (animalesLista != null) {
            animalesLista.dispose();
        }
        animalesLista = new AnimalesLista(this, false);
        animalesLista.setVisible(true);
    }//GEN-LAST:event_jMenuItemAnimalesListaActionPerformed

    private void jMenuItemAnimalesModificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAnimalesModificacionActionPerformed
        if (animalesModificar != null) {
            animalesModificar.dispose();
        }
        animalesModificar = new AnimalesModificar(this, false);
        animalesModificar.setVisible(true);
    }//GEN-LAST:event_jMenuItemAnimalesModificacionActionPerformed

    private void jMenuItemClientesAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesAltaActionPerformed
        if (clientesAlta != null) {
            clientesAlta.dispose();
        }
        clientesAlta = new ClientesAlta(this, false);
        clientesAlta.setVisible(true);
    }//GEN-LAST:event_jMenuItemClientesAltaActionPerformed

    private void jMenuItemClientesBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesBajaActionPerformed
        if (clientesBaja != null) {
            clientesBaja.dispose();
        }
        clientesBaja = new ClientesBaja(this, false);
        clientesBaja.setVisible(true);
    }//GEN-LAST:event_jMenuItemClientesBajaActionPerformed

    private void jMenuItemClientesListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesListaActionPerformed
        if (clientesLista != null) {
            clientesLista.dispose();
        }
        clientesLista = new ClientesLista(this, false);
        clientesLista.setVisible(true);
    }//GEN-LAST:event_jMenuItemClientesListaActionPerformed

    private void jMenuItemClientesModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesModificarActionPerformed
        if (clientesModificar != null) {
            clientesModificar.dispose();
        }
        clientesModificar = new ClientesModificar(this, false);
        clientesModificar.setVisible(true);
    }//GEN-LAST:event_jMenuItemClientesModificarActionPerformed

    private void jButtonNuevaCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaCitaActionPerformed
        if (citaAlta != null) {
            citaAlta.dispose();
        }
        citaAlta = new CitaAlta(this, false);
        citaAlta.setVisible(true);
    }//GEN-LAST:event_jButtonNuevaCitaActionPerformed

    private void jButtonBorrarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarCitaActionPerformed
        if (citaBaja != null) {
            citaBaja.dispose();
        }
        citaBaja = new CitaBaja(this, false);
        citaBaja.setVisible(true);
    }//GEN-LAST:event_jButtonBorrarCitaActionPerformed

    private void jButtonEditarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarCitaActionPerformed
        if (citaModificar != null) {
            citaModificar.dispose();
        }
        citaModificar = new CitaModificar(this, false);
        citaModificar.setVisible(true);
    }//GEN-LAST:event_jButtonEditarCitaActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBorrarCita;
    private javax.swing.JButton jButtonEditarCita;
    private javax.swing.JButton jButtonNuevaCita;
    private javax.swing.JLabel jLabelCitas;
    private javax.swing.JMenu jMenuAnimales;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuClientes;
    private javax.swing.JMenuItem jMenuItemAnimalesAlta;
    private javax.swing.JMenuItem jMenuItemAnimalesBaja;
    private javax.swing.JMenuItem jMenuItemAnimalesLista;
    private javax.swing.JMenuItem jMenuItemAnimalesModificacion;
    private javax.swing.JMenuItem jMenuItemAyuda;
    private javax.swing.JMenuItem jMenuItemClientesAlta;
    private javax.swing.JMenuItem jMenuItemClientesBaja;
    private javax.swing.JMenuItem jMenuItemClientesLista;
    private javax.swing.JMenuItem jMenuItemClientesModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCitas;
    // End of variables declaration//GEN-END:variables
    //Animales
    private AnimalesAlta animalesAlta;
    private AnimalesBaja animalesBaja;
    private AnimalesLista animalesLista;
    private AnimalesModificar animalesModificar;
    //Clientes
    private ClientesAlta clientesAlta;
    private ClientesBaja clientesBaja;
    private ClientesLista clientesLista;
    private ClientesModificar clientesModificar;
    //Citas
    private CitaAlta citaAlta;
    private CitaBaja citaBaja;
    private CitaModificar citaModificar;
    //Tabla
    private DefaultTableModel tableModel;
    private Connection conexion;
    //DAOS
    private AnimalesDAO animalesDAO;
    private ClientesDAO clientesDAO;
    private CitasDAO citasDAO;

    public void cargarTabla() {
        tableModel.setRowCount(0);
        try {
            List<Cita> listaCitas = citasDAO.listarCitas(new HashMap<>());
            Animal animal;
            Cliente cliente;
            for (Cita cita : listaCitas) {
                animal = animalesDAO.buscarAnimal(cita.getIdAnimal());
                cliente = clientesDAO.buscarCliente(cita.getIdCliente());
                Object[] fila = {
                        cita.getFecha().toString() + "  -  " + cita.getHoraInicio().toString(),
                        animal.getTipoAnimal(),
                        cliente.getDni(),
                        cita.getDescripcion(),
                        cita.isPendiente() == true ? "SI" : "NO",
                        cita.getIdCita()
                };
                tableModel.addRow(fila);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void actualizarCitas() {
        cargarTabla();
    }

    @Override
    public void actualizarAnimales() {
        cargarTabla();
    }

    @Override
    public void actualizarClientes() {
        cargarTabla();
    }
}
