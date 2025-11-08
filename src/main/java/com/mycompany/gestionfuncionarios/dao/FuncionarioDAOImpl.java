package com.mycompany.gestionfuncionarios.dao;

// 1. Importamos el modelo y la interfaz
import com.mycompany.gestionfuncionarios.modelo.Funcionario;
import java.util.List;
import java.util.ArrayList;

// 2. Importamos TODAS las clases de SQL necesarias
import java.sql.Connection;
import java.sql.PreparedStatement; // El más importante para seguridad
import java.sql.ResultSet; // Para leer datos
import java.sql.SQLException; // Para el manejo de excepciones

/**
 * Implementación CONCRETA de la interfaz IFuncionarioDAO.
 * Aquí va toda la lógica de SQL (JDBC) y el manejo de excepciones.
 * Esta clase "implementa" (cumple) el contrato de IFuncionarioDAO.
 */
public class FuncionarioDAOImpl implements IFuncionarioDAO {

    // Usaremos constantes para las sentencias SQL. Es una buena práctica.
    private static final String SQL_INSERT = "INSERT INTO funcionarios (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM funcionarios WHERE tipo_identificacion = ? AND numero_identificacion = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM funcionarios ORDER BY apellidos";
    private static final String SQL_UPDATE = "UPDATE funcionarios SET nombres = ?, apellidos = ?, estado_civil = ?, sexo = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE tipo_identificacion = ? AND numero_identificacion = ?";
    private static final String SQL_DELETE = "DELETE FROM funcionarios WHERE tipo_identificacion = ? AND numero_identificacion = ?";

    /**
     * Método para CREAR un funcionario.
     * Cumple con el requisito de "Manejo de Excepciones".
     */
    @Override
    public boolean crear(Funcionario funcionario) {
        Connection cnn = null;
        PreparedStatement pstmt = null; // Usamos PreparedStatement para evitar SQL Injection
        
        try {
            // 1. Obtenemos la conexión de nuestra clase centralizada
            cnn = ConexionDB.getConnection();
            
            // 2. Preparamos la sentencia SQL
            pstmt = cnn.prepareStatement(SQL_INSERT);
            
            // 3. Establecemos los valores (los '?')
            pstmt.setString(1, funcionario.getTipoIdentificacion());
            pstmt.setString(2, funcionario.getNumeroIdentificacion());
            pstmt.setString(3, funcionario.getNombres());
            pstmt.setString(4, funcionario.getApellidos());
            pstmt.setString(5, funcionario.getEstadoCivil());
            pstmt.setString(6, funcionario.getSexo());
            pstmt.setString(7, funcionario.getDireccion());
            pstmt.setString(8, funcionario.getTelefono());
            // Convertimos de java.util.Date (del modelo) a java.sql.Date (para la BD)
            pstmt.setDate(9, new java.sql.Date(funcionario.getFechaNacimiento().getTime()));
            
            // 4. Ejecutamos la inserción
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Devuelve true si se insertó (1 fila afectada)
            
        } catch (SQLException e) {
            // 5. MANEJO DE EXCEPCIONES (requisito de la tarea)
            System.err.println("Error al crear funcionario: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            // 6. Cerramos recursos SIEMPRE (en el finally)
            try {
                if (pstmt != null) pstmt.close();
                if (cnn != null) cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para OBTENER UN funcionario por su ID.
     */
    @Override
    public Funcionario obtenerPorId(String tipoId, String numeroId) {
        Connection cnn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // ResultSet es para recibir los datos de un SELECT
        Funcionario funcionario = null;

        try {
            cnn = ConexionDB.getConnection();
            pstmt = cnn.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setString(1, tipoId);
            pstmt.setString(2, numeroId);
            
            rs = pstmt.executeQuery(); // Usamos executeQuery para SELECT
            
            // Si el ResultSet encontró un resultado...
            if (rs.next()) {
                funcionario = new Funcionario(); // Creamos el objeto
                // Llenamos el objeto con los datos de la fila
                funcionario.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                funcionario.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                funcionario.setNombres(rs.getString("nombres"));
                funcionario.setApellidos(rs.getString("apellidos"));
                funcionario.setEstadoCivil(rs.getString("estado_civil"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setDireccion(rs.getString("direccion"));
                funcionario.setTelefono(rs.getString("telefono"));
                funcionario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (cnn != null) cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return funcionario; // Devuelve el funcionario encontrado o null
    }

    /**
     * Método para OBTENER TODOS los funcionarios.
     */
    @Override
    public List<Funcionario> obtenerTodos() {
        Connection cnn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Funcionario> funcionarios = new ArrayList<>(); // Lista para guardar los resultados

        try {
            cnn = ConexionDB.getConnection();
            pstmt = cnn.prepareStatement(SQL_SELECT_ALL);
            rs = pstmt.executeQuery();
            
            // Mientras (while) haya filas en el resultado...
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                funcionario.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                funcionario.setNombres(rs.getString("nombres"));
                funcionario.setApellidos(rs.getString("apellidos"));
                funcionario.setEstadoCivil(rs.getString("estado_civil"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setDireccion(rs.getString("direccion"));
                funcionario.setTelefono(rs.getString("telefono"));
                funcionario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                
                funcionarios.add(funcionario); // Agregamos el funcionario a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (cnn != null) cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return funcionarios; // Devuelve la lista (vacía o llena)
    }

    /**
     * Método para ACTUALIZAR un funcionario.
     */
    @Override
    public boolean actualizar(Funcionario funcionario) {
        Connection cnn = null;
        PreparedStatement pstmt = null;
        
        try {
            cnn = ConexionDB.getConnection();
            pstmt = cnn.prepareStatement(SQL_UPDATE);
            
            // 1. Establecemos los nuevos valores (SET)
            pstmt.setString(1, funcionario.getNombres());
            pstmt.setString(2, funcionario.getApellidos());
            pstmt.setString(3, funcionario.getEstadoCivil());
            pstmt.setString(4, funcionario.getSexo());
            pstmt.setString(5, funcionario.getDireccion());
            pstmt.setString(6, funcionario.getTelefono());
            pstmt.setDate(7, new java.sql.Date(funcionario.getFechaNacimiento().getTime()));
            
            // 2. Establecemos el ID (WHERE)
            pstmt.setString(8, funcionario.getTipoIdentificacion());
            pstmt.setString(9, funcionario.getNumeroIdentificacion());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // True si se actualizó
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (cnn != null) cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para ELIMINAR un funcionario.
     */
    @Override
    public boolean eliminar(String tipoId, String numeroId) {
        Connection cnn = null;
        PreparedStatement pstmt = null;
        
        try {
            cnn = ConexionDB.getConnection();
            pstmt = cnn.prepareStatement(SQL_DELETE);
            
            // 1. Establecemos el ID (WHERE)
            pstmt.setString(1, tipoId);
            pstmt.setString(2, numeroId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // True si se eliminó
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (cnn != null) cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}