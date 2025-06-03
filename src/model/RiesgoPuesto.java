/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Joel Grullon
 */
@Entity
@Table(name = "riesgos_puesto")
@NamedQueries({
    @NamedQuery(name = "RiesgosPuesto.findAll", query = "SELECT r FROM RiesgosPuesto r"),
    @NamedQuery(name = "RiesgosPuesto.findByIdRiesgo", query = "SELECT r FROM RiesgosPuesto r WHERE r.idRiesgo = :idRiesgo"),
    @NamedQuery(name = "RiesgosPuesto.findByNombreRiesgo", query = "SELECT r FROM RiesgosPuesto r WHERE r.nombreRiesgo = :nombreRiesgo"),
    @NamedQuery(name = "RiesgosPuesto.findByEstado", query = "SELECT r FROM RiesgosPuesto r WHERE r.estado = :estado")})
public class RiesgoPuesto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_riesgo")
    private Integer idRiesgo;
    @Basic(optional = false)
    @Column(name = "nombre_riesgo")
    private String nombreRiesgo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "riesgoId")
    private Collection<Puesto> puestosCollection;

    public RiesgoPuesto() {
    }

    public RiesgoPuesto(Integer idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public RiesgoPuesto(Integer idRiesgo, String nombreRiesgo) {
        this.idRiesgo = idRiesgo;
        this.nombreRiesgo = nombreRiesgo;
    }

    public Integer getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(Integer idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public String getNombreRiesgo() {
        return nombreRiesgo;
    }

    public void setNombreRiesgo(String nombreRiesgo) {
        this.nombreRiesgo = nombreRiesgo;
    }

    public Collection<Puesto> getPuestosCollection() {
        return puestosCollection;
    }

    public void setPuestosCollection(Collection<Puesto> puestosCollection) {
        this.puestosCollection = puestosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRiesgo != null ? idRiesgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RiesgoPuesto)) {
            return false;
        }
        RiesgoPuesto other = (RiesgoPuesto) object;
        if ((this.idRiesgo == null && other.idRiesgo != null) || (this.idRiesgo != null && !this.idRiesgo.equals(other.idRiesgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.RiesgosPuesto[ idRiesgo=" + idRiesgo + " ]";
    }
    
}
