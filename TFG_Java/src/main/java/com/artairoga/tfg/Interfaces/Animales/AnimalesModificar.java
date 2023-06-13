/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.artairoga.tfg.Interfaces.Animales;

import com.artairoga.tfg.GestionBBDD.AnimalesDAO;
import com.artairoga.tfg.GestionBBDD.ClientesDAO;
import com.artairoga.tfg.GestionBBDD.ConexionBD;
import com.artairoga.tfg.GestionBBDD.Observers.AnimalesObserver;
import com.artairoga.tfg.GestionBBDD.Observers.ClientesObserver;
import com.artairoga.tfg.GestionFTP.FTPController;
import com.artairoga.tfg.Interfaces.Citas.CitaModificar;
import com.artairoga.tfg.Modelos.Animal;
import com.artairoga.tfg.Modelos.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author artai
 */
public class AnimalesModificar extends javax.swing.JDialog implements AnimalesObserver, ClientesObserver {

    /**
     * Creates new form AnimalesModificar
     */
    public AnimalesModificar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Se crea la conexion con la base de datos
        conexionBD = ConexionBD.getInstancia().getConexion();
        //Se crea el objeto DAO para poder realizar las operaciones con la base de datos
        animalesDAO = AnimalesDAO.getInstance(conexionBD);
        clientesDAO = ClientesDAO.getInstance(conexionBD);
        //Se agrega como observador de la lista de animales
        animalesDAO.agregarObservador(this);
        clientesDAO.agregarObservador(this);
        //Se inicializan los datos
        jComboBoxAnimales.setModel(modelAnimal);
        jComboBoxCliente.setModel(modelCliente);
        cargarComboAnimales();
        cargarComboClientes();
        actualizarDatos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAlta = new javax.swing.JButton();
        jComboBoxCliente = new javax.swing.JComboBox<>();
        jComboBoxAnimales = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblFechaDeLanzamiento = new javax.swing.JLabel();
        txtRaza = new javax.swing.JTextField();
        lblImagen = new javax.swing.JLabel();
        btnAñadirImagen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaCaracteristicas = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAlta.setText("Modificar");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        jComboBoxCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        jComboBoxAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAnimalesActionPerformed(evt);
            }
        });

        jLabel1.setText("Animal a modificar");

        lblTitulo.setText("Dueño");

        lblDuracion.setText("Nombre ");

        lblFechaDeLanzamiento.setText("Descripcion");

        lblImagen.setBackground(new java.awt.Color(51, 51, 255));
        lblImagen.setForeground(new java.awt.Color(51, 51, 255));

        btnAñadirImagen.setText("Añadir Imagen");
        btnAñadirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirImagenActionPerformed(evt);
            }
        });

        jTextAreaCaracteristicas.setColumns(20);
        jTextAreaCaracteristicas.setRows(5);
        jScrollPane1.setViewportView(jTextAreaCaracteristicas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxAnimales, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(132, 132, 132)
                                                .addComponent(btnAlta))
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblDuracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblFechaDeLanzamiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1)
                                                        .addComponent(txtRaza)
                                                        .addComponent(jComboBoxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnAñadirImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxAnimales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblTitulo))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblDuracion))
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblFechaDeLanzamiento)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                                .addComponent(btnAlta))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(btnAñadirImagen)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        int id_combo_animales = jComboBoxAnimales.getSelectedIndex();
        int id_combo_clientes = jComboBoxCliente.getSelectedIndex();
        Connection conexion = ConexionBD.getInstancia().getConexion();
        AnimalesDAO animalesDAO = AnimalesDAO.getInstance(conexion);
        String uuidImagenAntigua;
        //Compruebo que hay un animal seleccionado
        if (id_combo_animales != -1) {
            //Gestiono el animal base
            Animal animalModificar = listarAnimales.get(id_combo_animales);
            Cliente cliente = listarClientes.get(id_combo_clientes);
            animalModificar
                    .setCaracteristicas(jTextAreaCaracteristicas.getText())
                    .setNombreAnimal(txtRaza.getText())
                    .setIdCliente(cliente.getId_cliente());
            //Gestiono la imagen solo la subo si la imagen actual es diferente de la de la bbdd
            if (imagenFile != null) {
                if (imagenFile.getName() != animalModificar.getImagen()) {
                    uuidImagenAntigua = animalModificar.getImagen();
                    UUID uuid = UUID.randomUUID();
                    FTPController ftpController = new FTPController();
                    String extension = "";
                    int i = imagenFile.getName().lastIndexOf('.');
                    if (i > 0) {
                        extension = imagenFile.getName().substring(i + 1);
                    }
                    //primero la subo,luego se la doy, de forma que si falla la subida no se modifica la imagen
                    ftpController.uploadFile(imagenFile, uuid.toString() + "." + extension);
                    animalModificar.setImagen(uuid.toString() + "." + extension);
                    //por ultimo borro la imagen antigua
                    ftpController.deleteFile(uuidImagenAntigua);
                }
            }
            //Por ultimo subo los cambios a la base de datos
            try {

                animalesDAO.actualizarAnimal(animalModificar);
                JOptionPane.showMessageDialog(this, "Animal modificado correctamente");
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(AnimalesModificar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAltaActionPerformed

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

    private void jComboBoxAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAnimalesActionPerformed
        actualizarDatos();
    }//GEN-LAST:event_jComboBoxAnimalesActionPerformed

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
            java.util.logging.Logger.getLogger(AnimalesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnimalesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnimalesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnimalesModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AnimalesModificar dialog = new AnimalesModificar(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> jComboBoxAnimales;
    private javax.swing.JComboBox<String> jComboBoxCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaCaracteristicas;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblFechaDeLanzamiento;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtRaza;
    // End of variables declaration//GEN-END:variables
    private File imagenFile;
    //Listas 
    private List<Animal> listarAnimales;
    private List<Cliente> listarClientes;
    //Modelos combos
    private DefaultComboBoxModel<String> modelAnimal = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> modelCliente = new DefaultComboBoxModel<>();
    //Conexion
    private Connection conexionBD;
    //DAOS
    private AnimalesDAO animalesDAO;
    private ClientesDAO clientesDAO;

    /**
     * Cargo la informacion correspondiente en el combo de los animales
     */
    private void cargarComboAnimales() {
        modelAnimal.removeAllElements();
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            AnimalesDAO animalesDAO = new AnimalesDAO(conexion);
            listarAnimales = animalesDAO.listar(new HashMap<>());
            for (Animal animal : listarAnimales) {
                modelAnimal.addElement(animal.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(CitaModificar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Cargo la informacion correspondiente en el combo de clientes
     */
    private void cargarComboClientes() {
        modelCliente.removeAllElements();
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            ClientesDAO clientesDAO = new ClientesDAO(conexion);
            listarClientes = clientesDAO.listarClientes();
            for (Cliente cliente : listarClientes) {
                modelCliente.addElement(String.valueOf(cliente.getDni()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CitaModificar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Devuelve la posicion de la lista en la que esta el dueño de un animal
     *
     * @param list   lista de clientes donde buscar
     * @param animal animal del cual se quiere buscar el dueño en la lista
     * @return posicion del dueño del animal en la lista
     */
    private int getWhereCliente(List<Cliente> list, Animal animal) {
        //porque llega aqui null?
        if (listarClientes != null) {
            for (int i = 0; i < list.size(); i++) {
                Cliente clienteTest = list.get(i);
                if (clienteTest.getId_cliente() == animal.getIdCliente()) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Acutaliza la informacion de la pantalla en base al animal seleccionado
     */
    private void actualizarDatos() {
        if (jComboBoxAnimales.getSelectedIndex() != -1) {
            Animal animal = listarAnimales.get(jComboBoxAnimales.getSelectedIndex());
            //Relleno la imagen
            try {
                if (animal.getImagen() != null) {
                    FTPController ftpControler = new FTPController();
                    imagenFile = ftpControler.downloadFile(animal.getImagen(), "./Resources/Cache/" + animal.getImagen());
                    String rutaImagen = imagenFile.getAbsolutePath();
                    System.out.println("Ruta de la imagen seleccionada: " + rutaImagen);
                    ImageIcon icono = new ImageIcon(rutaImagen);
                    lblImagen.setIcon(new ImageIcon(icono.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH)));
                } else {
                    lblImagen.setIcon(new ImageIcon(new byte[0]));
                }
            } catch (NullPointerException ex) {
                Logger.getLogger(AnimalesModificar.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Relleno el cliente
            jComboBoxCliente.setSelectedIndex(getWhereCliente(listarClientes, animal));
            //Relleno las informacion
            txtRaza.setText(animal.getTipoAnimal());
            jTextAreaCaracteristicas.setText(animal.getCaracteristicas());

        }
    }

    @Override
    public void actualizarAnimales() {
        cargarComboAnimales();
    }

    @Override
    public void actualizarClientes() {
        cargarComboClientes();
    }
}