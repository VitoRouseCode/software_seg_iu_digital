package com.mycompany.gestionfuncionarios.dao;

// Importamos el modelo que usará esta interfaz
import com.mycompany.gestionfuncionarios.modelo.Funcionario;
// Importamos la clase List para el método de "obtenerTodos"
import java.util.List;

/**
 * Interfaz (Contrato) que define las operaciones CRUD 
 * para la entidad Funcionario.
 * * Esto es la base del Patrón DAO: define QUÉ se hace, 
 * pero no CÓMO se hace.
 */
public interface IFuncionarioDAO {
    
    /**
     * Crea un nuevo funcionario en la base de datos.
     * @param funcionario El objeto Funcionario con los datos a guardar.
     * @return true si se guardó exitosamente, false si no.
     */
    public boolean crear(Funcionario funcionario);
    
    /**
     * Obtiene un funcionario específico por su clave primaria compuesta.
     * @param tipoId El tipo de identificación del funcionario.
     * @param numeroId El número de identificación del funcionario.
     * @return El objeto Funcionario encontrado, o null si no se encuentra.
     */
    public Funcionario obtenerPorId(String tipoId, String numeroId);
    
    /**
     * Obtiene una lista con todos los funcionarios de la base de datos.
     * @return Una lista (List) de objetos Funcionario.
     */
    public List<Funcionario> obtenerTodos();
    
    /**
     * Actualiza un funcionario existente en la base de datos.
     * @param funcionario El objeto Funcionario con los datos a actualizar.
     * @return true si se actualizó exitosamente, false si no.
     */
    public boolean actualizar(Funcionario funcionario);
    
    /**
     * Elimina un funcionario de la base de datos por su clave primaria.
     * @param tipoId El tipo de identificación del funcionario.
     * @param numeroId El número de identificación del funcionario.
     * @return true si se eliminó exitosamente, false si no.
     */
    public boolean eliminar(String tipoId, String numeroId);
    
}