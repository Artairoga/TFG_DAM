/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.artairoga.tfg.Interfaces.Clientes;

import com.artairoga.tfg.GestionBBDD.AnimalesDAO;
import com.artairoga.tfg.GestionBBDD.ClientesDAO;
import com.artairoga.tfg.GestionBBDD.ConexionBD;
import com.artairoga.tfg.GestionBBDD.Observers.ClientesObserver;
import com.artairoga.tfg.Interfaces.Animales.AnimalesAlta;
import com.artairoga.tfg.Interfaces.Animales.AnimalesModificar;
import com.artairoga.tfg.Modelos.Animal;
import com.artairoga.tfg.Modelos.Cliente;
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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author artai
 */
public class ClientesModificar extends javax.swing.JDialog implements ClientesObserver {

    /**
     * Creates new form ClientesModificar
     */
    public ClientesModificar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Inicializamos la conexion a la BBDD
        conexionBD = ConexionBD.getInstancia().getConexion();
        //Inicializamos el DAO de Clientes
        clientesDAO = ClientesDAO.getInstance(conexionBD);
        //Lo añadimos como observador
        clientesDAO.agregarObservador(this);
        //Cargamos el combo
        jComboBoxCliente.setModel(modelCliente);
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

        btnAñadirImagen2 = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jComboBoxCliente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblFechaDeLanzamiento = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        lblImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAñadirImagen2.setText("Añadir Imagen");
        btnAñadirImagen2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirImagen2ActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jComboBoxCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente a modificar");

        lblTitulo.setText("Nombre Completo");

        lblDuracion.setText("DNI");

        lblFechaDeLanzamiento.setText("Telefono");

        lblImagen.setBackground(new java.awt.Color(51, 51, 255));
        lblImagen.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblFechaDeLanzamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(6, 6, 6)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(15, 15, 15)
                                                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(132, 132, 132)
                                                        .addComponent(btnModificar)
                                                        .addGap(143, 143, 143)
                                                        .addComponent(btnAñadirImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jComboBoxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 18, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(lblTitulo)
                                                .addGap(9, 9, 9)
                                                .addComponent(lblDuracion)
                                                .addGap(12, 12, 12)
                                                .addComponent(lblFechaDeLanzamiento))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(3, 3, 3)
                                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnModificar)
                                        .addComponent(btnAñadirImagen2))
                                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAñadirImagen2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirImagen2ActionPerformed
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
    }//GEN-LAST:event_btnAñadirImagen2ActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int id_combo_clientes = jComboBoxCliente.getSelectedIndex();
        String uuidImagenAntigua;
        //Compruebo que hay un cliente seleccionado
        if (id_combo_clientes != -1) {
            //Gestiono el cliente base
            Cliente clienteModificar = listaClientes.get(id_combo_clientes);
            clienteModificar
                    .setDni(txtDNI.getText())
                    .setNombre_completo(txtNombreCompleto.getText())
                    .setTelefono(txtTelefono.getText());
            //Gestiono la imagen solo la subo si la imagen actual es diferente de la de la bbdd
            if (imagenFile != null) {
                if (imagenFile.getName() != clienteModificar.getImagen()) {
                    uuidImagenAntigua = clienteModificar.getImagen();
                    UUID uuid = UUID.randomUUID();
                    FTPController ftpController = new FTPController();
                    String extension = "";
                    int i = imagenFile.getName().lastIndexOf('.');
                    if (i > 0) {
                        extension = imagenFile.getName().substring(i + 1);
                    }
                    //Primero subo la imagen al servidor,si falla no subo los cambios a la base de datos
                    ftpController.uploadFile(imagenFile, uuid.toString() + "." + extension);
                    clienteModificar.setImagen(uuid.toString() + "." + extension);
                    //Si todo ha ido bien borro la imagen antigua del servidor
                    ftpController.deleteFile(uuidImagenAntigua);
                }
            }
            //Por ultimo subo los cambios a la base de datos
            try {
                clientesDAO.actualizarCliente(clienteModificar);
                JOptionPane.showMessageDialog(this, "Cliente modificado correctamente");
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(AnimalesModificar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jComboBoxClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClienteActionPerformed
        actualizarDatos();
    }//GEN-LAST:event_jComboBoxClienteActionPerformed

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
            java.util.logging.Logger.getLogger(ClientesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClientesModificar dialog = new ClientesModificar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAñadirImagen2;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> jComboBoxCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblFechaDeLanzamiento;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
    private File imagenFile;
    private List<Cliente> listaClientes;
    //Modelos Combo
    private DefaultComboBoxModel<String> modelCliente = new DefaultComboBoxModel<>();
    //COnexion BD
    private Connection conexionBD;
    //DAO
    private ClientesDAO clientesDAO;

    private void cargarComboClientes() {
        modelCliente.removeAllElements();
        try {
            listaClientes = clientesDAO.listarClientes();
            for (Cliente cliente : listaClientes) {
                modelCliente.addElement(cliente.getDni());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalesAlta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Acutaliza la informacion de la pantalla en base al animal seleccionado
     */
    private void actualizarDatos() {
        if (jComboBoxCliente.getSelectedIndex() != -1) {
            Cliente cliente = listaClientes.get(jComboBoxCliente.getSelectedIndex());
            //Relleno la imagen
            if (cliente.getImagen() != null) {
                FTPController ftpControler = new FTPController();
                imagenFile = ftpControler.downloadFile(cliente.getImagen(), "./Resources/Cache/" + cliente.getImagen());
                String rutaImagen = imagenFile.getAbsolutePath();
                System.out.println("Ruta de la imagen seleccionada: " + rutaImagen);
                ImageIcon icono = new ImageIcon(rutaImagen);
                lblImagen.setIcon(new ImageIcon(icono.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH)));
            } else {
                lblImagen.setIcon(new ImageIcon(new byte[0]));
            }
            //Relleno las informacion
            txtDNI.setText(cliente.getDni());
            txtNombreCompleto.setText(cliente.getNombre_completo());
            txtTelefono.setText(cliente.getTelefono());
        }
    }

    @Override
    public void actualizarClientes() {
        cargarComboClientes();
    }
}