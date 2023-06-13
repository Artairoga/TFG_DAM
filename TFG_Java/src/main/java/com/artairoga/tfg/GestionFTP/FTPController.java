/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.artairoga.tfg.GestionFTP;

import com.artairoga.tfg.GestionBBDD.ConexionBD;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author artai
 */
public class FTPController {

    private static final int BUFFER_SIZE = 4096;

    private String ip;
    private int puerto;
    private String user;
    private String password;
    private String remotePath;

    /**
     * Constructor de la clase FTPController
     */
    public FTPController() {
        Properties propiedades = new Properties();
        try (InputStream entrada = new FileInputStream("./Resources/config.properties")) {
            propiedades.load(entrada);
            String ipFtp = propiedades.getProperty("db.ip");
            this.ip = ipFtp;
            this.puerto = 21;
            this.user = "artai";
            this.password = "abc123.";
            this.remotePath = "/Images";
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Problemas con la conexion revise los ajustes");
        }

    }

    /**
     * Metodo que sube un archivo al servidor ftp
     * @param localFile archivo local
     * @param uuid nombre del archivo
     * @return true si se ha subido correctamente, false en caso contrario
     */
    public boolean uploadFile(File localFile, String uuid) {
        FTPClient ftpClient = new FTPClient();
        try (FileInputStream inputStream = new FileInputStream(localFile)) {
            ftpClient.connect(ip, puerto);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String remoteFilePath = remotePath + "/" + uuid;
            if (ftpClient.storeFile(remoteFilePath, inputStream)) {
                System.out.println("El archivo ha sido subido con éxito");
                return true;
            } else {
                System.out.println("Error al subir el archivo");
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Error al subir el archivo: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metodo que descarga un archivo del servidor ftp
     * @param remoteFilePath ruta del archivo en el servidor ftp
     * @param localFilePath ruta donde se guardara el archivo
     * @return el archivo descargado
     */
    public File downloadFile(String remoteFilePath, String localFilePath) {
        FTPClient ftpClient = new FTPClient();
        try (FileOutputStream outputStream = new FileOutputStream(localFilePath)) {
            ftpClient.connect(ip, puerto);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            if (ftpClient.retrieveFile(remotePath + "/" + remoteFilePath, outputStream)) {
                System.out.println("El archivo ha sido descargado con éxito");
                return new File(localFilePath);
            } else {
                System.out.println("Error al descargar el archivo");
                return null;
            }
        } catch (IOException ex) {
            System.out.println("Error al descargar el archivo: " + ex.getMessage());
            return null;
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metodo que elimina un archivo del servidor ftp
     * @param uuid nombre del archivo
     * @return true si se ha eliminado correctamente, false en caso contrario
     */
    public boolean deleteFile(String uuid) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, puerto);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            if (ftpClient.deleteFile(remotePath + "/" + uuid)) {
                System.out.println("El archivo ha sido eliminado con éxito");
                return true;
            } else {
                System.out.println("Error al eliminar el archivo");
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Error al eliminar el archivo: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
