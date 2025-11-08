package com.mycompany.gestionfuncionarios.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión con la base de datos PostgreSQL.
 * Esta clase centraliza los datos de acceso.
 */
public class ConexionDB {

    // 1. --- Tus Credenciales ---
    // (Estos son los datos que ya verificamos)
    
    /**
     * Nombre de la base de datos que creamos para el proyecto.
     */
    private static final String DB_NAME = "gestion_funcionarios"; 
    
    /**
     * Usuario administrador de PostgreSQL.
     */
    private static final String USER = "postgres";
    
    /**
     * Contraseña del usuario 'postgres'.
     */
    private static final String PASSWORD = "Manuela";
    
    // 2. --- URL de Conexión ---
    // (Esta URL se construye sola, no necesitas cambiarla)
    private static final String URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;

    
    /**
     * Método público y estático para obtener una conexión a la base de datos.
     * Desde aquí la llamarán todas las demás clases del DAO.
     * * @return un objeto Connection listo para ser usado.
     * @throws SQLException si ocurre un error en la conexión.
     */
    public static Connection getConnection() throws SQLException {
        // El DriverManager usará el "driver" (conector) que pusimos 
        // en el archivo pom.xml para entender cómo hablar con PostgreSQL.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Opcional: Método main para probar la conexión rápidamente
    
}