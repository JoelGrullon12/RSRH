/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Joel Grullon
 */
@Entity
@Table(name = "competencias")
@NamedQueries({
    @NamedQuery(name = "Competencias.findAll", query = "SELECT c FROM Competencias c"),
    @NamedQuery(name = "Competencias.findByIdCompetencia", query = "SELECT c FROM Competencias c WHERE c.idCompetencia = :idCompetencia"),
    @NamedQuery(name = "Competencias.findByNombreCompetencia", query = "SELECT c FROM Competencias c WHERE c.nombreCompetencia = :nombreCompetencia"),
    @NamedQuery(name = "Competencias.findByDescripcion", query = "SELECT c FROM Competencias c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Competencias.findByEstado", query = "SELECT c FROM Competencias c WHERE c.estado = :estado")})
public class Competencia extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_competencia")
    private Integer idCompetencia;
    @Basic(optional = false)
    @Column(name = "nombre_competencia")
    private String nombreCompetencia;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "candidato_id", referencedColumnName = "id_candidato")
    @ManyToOne(optional = false)
    private Candidato candidatoId;

    public Competencia() {
    }

    public Competencia(Integer idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public Competencia(Integer idCompetencia, String nombreCompetencia) {
        this.idCompetencia = idCompetencia;
        this.nombreCompetencia = nombreCompetencia;
    }

    public Integer getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Integer idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public String getNombreCompetencia() {
        return nombreCompetencia;
    }

    public void setNombreCompetencia(String nombreCompetencia) {
        this.nombreCompetencia = nombreCompetencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Candidato getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(Candidato candidatoId) {
        this.candidatoId = candidatoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompetencia != null ? idCompetencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competencia)) {
            return false;
        }
        Competencia other = (Competencia) object;
        if ((this.idCompetencia == null && other.idCompetencia != null) || (this.idCompetencia != null && !this.idCompetencia.equals(other.idCompetencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Competencias[ idCompetencia=" + idCompetencia + " ]";
    }
    
}
