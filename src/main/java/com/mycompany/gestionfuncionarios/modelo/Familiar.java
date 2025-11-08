package com.mycompany.gestionfuncionarios.modelo;

/**
 * Modelo (POJO) que representa la tabla "familiares".
 */
public class Familiar {

    private int idFamiliar; // Para el id_familiar (SERIAL)
    private String nombres;
    private String apellidos;
    private String rol;
    
    // Guardamos la FK al funcionario (opcional)
    private String funcionarioTipoId;
    private String funcionarioNumeroId;

    public Familiar() {
    }

    // Getters y Setters...
    
    public int getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(int idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFuncionarioTipoId() {
        return funcionarioTipoId;
    }

    public void setFuncionarioTipoId(String funcionarioTipoId) {
        this.funcionarioTipoId = funcionarioTipoId;
    }

    public String getFuncionarioNumeroId() {
        return funcionarioNumeroId;
    }

    public void setFuncionarioNumeroId(String funcionarioNumeroId) {
        this.funcionarioNumeroId = funcionarioNumeroId;
    }
}