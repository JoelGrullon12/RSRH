/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Joel Grullon
 */
@Entity
@Table(name = "capacitaciones")
@NamedQueries({
    @NamedQuery(name = "Capacitaciones.findAll", query = "SELECT c FROM Capacitaciones c"),
    @NamedQuery(name = "Capacitaciones.findByIdCapacitacion", query = "SELECT c FROM Capacitaciones c WHERE c.idCapacitacion = :idCapacitacion"),
    @NamedQuery(name = "Capacitaciones.findByNombreCapacitacion", query = "SELECT c FROM Capacitaciones c WHERE c.nombreCapacitacion = :nombreCapacitacion"),
    @NamedQuery(name = "Capacitaciones.findByDescripcion", query = "SELECT c FROM Capacitaciones c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Capacitaciones.findByFechaDesde", query = "SELECT c FROM Capacitaciones c WHERE c.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "Capacitaciones.findByFechaHasta", query = "SELECT c FROM Capacitaciones c WHERE c.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "Capacitaciones.findByInstitucion", query = "SELECT c FROM Capacitaciones c WHERE c.institucion = :institucion"),
    @NamedQuery(name = "Capacitaciones.findByEstado", query = "SELECT c FROM Capacitaciones c WHERE c.estado = :estado")})
public class Capacitacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_capacitacion")
    private Integer idCapacitacion;
    @Basic(optional = false)
    @Column(name = "nombre_capacitacion")
    private String nombreCapacitacion;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @Basic(optional = false)
    @Column(name = "institucion")
    private String institucion;
    
    @JoinColumn(name = "candidato_id", referencedColumnName = "id_candidato")
    @ManyToOne(optional = false)
    private Candidato candidatoId;
    @JoinColumn(name = "nivel_id", referencedColumnName = "id_nivel")
    @ManyToOne(optional = false)
    private NivelCapacitacion nivelId;

    public Capacitacion() {
    }

    public Capacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public Capacitacion(Integer idCapacitacion, String nombreCapacitacion, String institucion) {
        this.idCapacitacion = idCapacitacion;
        this.nombreCapacitacion = nombreCapacitacion;
        this.institucion = institucion;
    }

    public Integer getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public String getNombreCapacitacion() {
        return nombreCapacitacion;
    }

    public void setNombreCapacitacion(String nombreCapacitacion) {
        this.nombreCapacitacion = nombreCapacitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Candidato getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(Candidato candidatoId) {
        this.candidatoId = candidatoId;
    }

    public NivelCapacitacion getNivelId() {
        return nivelId;
    }

    public void setNivelId(NivelCapacitacion nivelId) {
        this.nivelId = nivelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapacitacion != null ? idCapacitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacitacion)) {
            return false;
        }
        Capacitacion other = (Capacitacion) object;
        if ((this.idCapacitacion == null && other.idCapacitacion != null) || (this.idCapacitacion != null && !this.idCapacitacion.equals(other.idCapacitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Capacitaciones[ idCapacitacion=" + idCapacitacion + " ]";
    }
    
}
