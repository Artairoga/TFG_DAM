/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.artaioga.tfg.Interfaces.Animales;

import com.artaioga.tfg.GestionBBDD.AnimalesDAO;
import com.artaioga.tfg.GestionBBDD.Observers.AnimalesObserver;
import com.artaioga.tfg.GestionBBDD.ConexionBD;
import com.artaioga.tfg.Interfaces.Citas.CitaModificar;
import com.artaioga.tfg.Modelos.Animal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author artai
 */
public class AnimalesBaja extends javax.swing.JDialog implements AnimalesObserver {

    /**
     * Creates new form AnimalesBaja
     */
    public AnimalesBaja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Se crea la conexion a la base de datos
        conexionBD = ConexionBD.getInstancia().getConexion();
        //Se inicializa el DAO de animales
        animalesDAO = AnimalesDAO.getInstance(conexionBD);
        //Se agrega como observador de la lista de animales
        animalesDAO.agregarObservador(this);
        //Se cargan los animales en la lista
        jComboBoxAnimales.setModel(modelAnimal);
        cargarComboAnimales();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelAnimal = new javax.swing.JLabel();
        jComboBoxAnimales = new javax.swing.JComboBox<>();
        jButtonBaja = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelAnimal.setText("Animal");

        jComboBoxAnimales.setMinimumSize(new java.awt.Dimension(300, 22));
        jComboBoxAnimales.setPreferredSize(new java.awt.Dimension(300, 22));

        jButtonBaja.setText("Baja");
        jButtonBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelAnimal)
                .addGap(5, 5, 5)
                .addComponent(jComboBoxAnimales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jButtonBaja)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabelAnimal))
                    .addComponent(jComboBoxAnimales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBaja))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBajaActionPerformed
        int id_animal;
        //Compruebo que hay  un animal seleccionado
        if((id_animal=jComboBoxAnimales.getSelectedIndex())==-1){
            JOptionPane.showMessageDialog(this, "No hay animal seleccionado");
            return;
        }
        //Pillo ese animal
        Animal animalBorrar = listarAnimales.get(id_animal);
        //Lo doy de baja en la bbdd
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            AnimalesDAO animalesDAO =AnimalesDAO.getInstance(conexion);
            animalesDAO.eliminarAnimal(animalBorrar.getIdAnimal());
            cargarComboAnimales();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonBajaActionPerformed

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
            java.util.logging.Logger.getLogger(AnimalesBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnimalesBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnimalesBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnimalesBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AnimalesBaja dialog = new AnimalesBaja(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBaja;
    private javax.swing.JComboBox<String> jComboBoxAnimales;
    private javax.swing.JLabel jLabelAnimal;
    // End of variables declaration//GEN-END:variables
    //Lista de animales
    private List<Animal> listarAnimales;
    //Modelo ComboBox
    private DefaultComboBoxModel<String> modelAnimal = new DefaultComboBoxModel<>();
    //Conexion
    private Connection conexionBD;
    //DAO
    private AnimalesDAO animalesDAO;
    /**
     * Rellena el combo de animales con la informacion correspondiente
     */
    private void cargarComboAnimales() {
        modelAnimal.removeAllElements();
        try {
            listarAnimales = animalesDAO.listar(new HashMap<>());
            for (Animal animal : listarAnimales) {
                modelAnimal.addElement(animal.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(CitaModificar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void actualizarAnimales() {
        cargarComboAnimales();
    }
}
