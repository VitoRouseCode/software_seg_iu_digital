package com.mycompany.gestionfuncionarios.modelo;

/**
 * Modelo (POJO) que representa la tabla "formacion_academica".
 */
public class FormacionAcademica {
    
    private int idFormacion; // Para el id_formacion (SERIAL)
    private String universidad;
    private String nivelEstudio;
    private String tituloEstudio;
    
    // FK al funcionario (opcional)
    private String funcionarioTipoId;
    private String funcionarioNumeroId;

    public FormacionAcademica() {
    }
    
    // Getters y Setters...

    public int getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(int idFormacion) {
        this.idFormacion = idFormacion;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getTituloEstudio() {
        return tituloEstudio;
    }

    public void setTituloEstudio(String tituloEstudio) {
        this.tituloEstudio = tituloEstudio;
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