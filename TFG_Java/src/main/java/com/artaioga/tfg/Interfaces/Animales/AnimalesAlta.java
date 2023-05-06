/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.artaioga.tfg.Interfaces.Animales;

import com.artaioga.tfg.GestionBBDD.AnimalesDAO;
import com.artaioga.tfg.GestionBBDD.ClientesDAO;
import com.artaioga.tfg.GestionBBDD.ConexionBD;
import com.artaioga.tfg.Modelos.Animal;
import com.artaioga.tfg.Modelos.Cliente;
import com.artairoga.tfg.GestionFTP.FTPController;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author artai
 */
public class AnimalesAlta extends javax.swing.JDialog {

    /**
     * Creates new form AnimalesAlta1
     */
    public AnimalesAlta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jComboBoxClientes.setModel(modelCliente);
        cargarComboClientes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblImagen = new javax.swing.JLabel();
        btnAñadirImagen = new javax.swing.JButton();
        btnAlta = new javax.swing.JButton();
        jComboBoxClientes = new javax.swing.JComboBox<>();
        lblTitulo = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblFechaDeLanzamiento = new javax.swing.JLabel();
        jTextRaza = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaCaracterisitcas = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblImagen.setBackground(new java.awt.Color(51, 51, 255));
        lblImagen.setForeground(new java.awt.Color(51, 51, 255));

        btnAñadirImagen.setText("Añadir Imagen");
        btnAñadirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirImagenActionPerformed(evt);
            }
        });

        btnAlta.setText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        jComboBoxClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lblTitulo.setText("Dueño");

        lblDuracion.setText("Raza");

        lblFechaDeLanzamiento.setText("Caracteristicas");

        jTextAreaCaracterisitcas.setColumns(20);
        jTextAreaCaracterisitcas.setRows(5);
        jScrollPane1.setViewportView(jTextAreaCaracterisitcas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btnAlta))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDuracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaDeLanzamiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextRaza, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(jComboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAñadirImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitulo))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDuracion))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaDeLanzamiento)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(35, 35, 35)
                .addComponent(btnAñadirImagen)
                .addGap(18, 18, 18)
                .addComponent(btnAlta)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAñadirImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirImagenActionPerformed
        JFrame ventana = new JFrame("Selector de imagen");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser selector = new JFileChooser();
        int resultado = selector.showOpenDialog(ventana);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            imagenFile = selector.getSelectedFile();
            String rutaImagen = imagenFile.getAbsolutePath();
            System.out.println("Ruta de la imagen seleccionada: " + rutaImagen);
            ImageIcon icono = new ImageIcon(rutaImagen);
            lblImagen.setIcon(new ImageIcon(icono.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH)));
        }
        ventana.pack();
        ventana.setVisible(true);
    }//GEN-LAST:event_btnAñadirImagenActionPerformed

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        FTPController ftpController = new FTPController();
        Cliente cliente = listarCliente.get(jComboBoxClientes.getSelectedIndex());
        UUID uuid = UUID.randomUUID();
        if(jTextAreaCaracterisitcas.getText().isBlank()||jTextRaza.getText().isBlank()){
            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacios");
            return;
        }
        
        Animal animal = new Animal()
                .setCaracteristicas(jTextAreaCaracterisitcas.getText())
                .setIdCliente(cliente.getId_cliente())
                .setTipoAnimal(jTextRaza.getText());

        if (imagenFile != null) {
            String extension = "";
            int i = imagenFile.getName().lastIndexOf('.');
            if (i > 0) {
                extension = imagenFile.getName().substring(i + 1);
            }
            animal.setImagen(uuid.toString() + "." + extension);
            ftpController.uploadFile(imagenFile, uuid.toString() + "." + extension);
        }
        
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            AnimalesDAO animalesDAO = new AnimalesDAO(conexion);
            animalesDAO.insertarAnimal(animal);
            JOptionPane.showMessageDialog(this, "Alta correcta!");
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(AnimalesAlta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAltaActionPerformed

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
            java.util.logging.Logger.getLogger(AnimalesAlta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnimalesAlta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnimalesAlta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnimalesAlta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AnimalesAlta dialog = new AnimalesAlta(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnAñadirImagen;
    private javax.swing.JComboBox<String> jComboBoxClientes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaCaracterisitcas;
    private javax.swing.JTextField jTextRaza;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblFechaDeLanzamiento;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
    private File imagenFile;
    private List<Cliente> listarCliente;
    private DefaultComboBoxModel<String> modelCliente = new DefaultComboBoxModel<>();

    private void cargarComboClientes() {
        modelCliente.removeAllElements();
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            ClientesDAO clientesDAO = new ClientesDAO(conexion);
            listarCliente = clientesDAO.listarClientes();
            for (Cliente cliente : listarCliente) {
                modelCliente.addElement(String.valueOf(cliente.getDni()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalesAlta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
