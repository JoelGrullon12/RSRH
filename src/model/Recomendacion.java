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
@Table(name = "recomendaciones")
@NamedQueries({
    @NamedQuery(name = "Recomendaciones.findAll", query = "SELECT r FROM Recomendaciones r"),
    @NamedQuery(name = "Recomendaciones.findByIdRecomendacion", query = "SELECT r FROM Recomendaciones r WHERE r.idRecomendacion = :idRecomendacion"),
    @NamedQuery(name = "Recomendaciones.findByNombreRecomendador", query = "SELECT r FROM Recomendaciones r WHERE r.nombreRecomendador = :nombreRecomendador"),
    @NamedQuery(name = "Recomendaciones.findByEmpresa", query = "SELECT r FROM Recomendaciones r WHERE r.empresa = :empresa"),
    @NamedQuery(name = "Recomendaciones.findByPuesto", query = "SELECT r FROM Recomendaciones r WHERE r.puesto = :puesto"),
    @NamedQuery(name = "Recomendaciones.findByTelefono", query = "SELECT r FROM Recomendaciones r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "Recomendaciones.findByEstado", query = "SELECT r FROM Recomendaciones r WHERE r.estado = :estado")})
public class Recomendacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recomendacion")
    private Integer idRecomendacion;
    @Basic(optional = false)
    @Column(name = "nombre_recomendador")
    private String nombreRecomendador;
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "telefono")
    private String telefono;
    @JoinColumn(name = "candidato_id", referencedColumnName = "id_candidato")
    @ManyToOne(optional = false)
    private Candidato candidatoId;

    public Recomendacion() {
    }

    public Recomendacion(Integer idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public Recomendacion(Integer idRecomendacion, String nombreRecomendador) {
        this.idRecomendacion = idRecomendacion;
        this.nombreRecomendador = nombreRecomendador;
    }

    public Integer getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(Integer idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public String getNombreRecomendador() {
        return nombreRecomendador;
    }

    public void setNombreRecomendador(String nombreRecomendador) {
        this.nombreRecomendador = nombreRecomendador;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (idRecomendacion != null ? idRecomendacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recomendacion)) {
            return false;
        }
        Recomendacion other = (Recomendacion) object;
        if ((this.idRecomendacion == null && other.idRecomendacion != null) || (this.idRecomendacion != null && !this.idRecomendacion.equals(other.idRecomendacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Recomendaciones[ idRecomendacion=" + idRecomendacion + " ]";
    }
    
}
