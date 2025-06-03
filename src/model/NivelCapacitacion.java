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
@Table(name = "niveles_capacitacion")
@NamedQueries({
    @NamedQuery(name = "NivelesCapacitacion.findAll", query = "SELECT n FROM NivelesCapacitacion n"),
    @NamedQuery(name = "NivelesCapacitacion.findByIdNivel", query = "SELECT n FROM NivelesCapacitacion n WHERE n.idNivel = :idNivel"),
    @NamedQuery(name = "NivelesCapacitacion.findByNombreNivel", query = "SELECT n FROM NivelesCapacitacion n WHERE n.nombreNivel = :nombreNivel"),
    @NamedQuery(name = "NivelesCapacitacion.findByEstado", query = "SELECT n FROM NivelesCapacitacion n WHERE n.estado = :estado")})
public class NivelCapacitacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel")
    private Integer idNivel;
    @Basic(optional = false)
    @Column(name = "nombre_nivel")
    private String nombreNivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelId")
    private Collection<Capacitacion> capacitacionesCollection;

    public NivelCapacitacion() {
    }

    public NivelCapacitacion(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public NivelCapacitacion(Integer idNivel, String nombreNivel) {
        this.idNivel = idNivel;
        this.nombreNivel = nombreNivel;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public Collection<Capacitacion> getCapacitacionesCollection() {
        return capacitacionesCollection;
    }

    public void setCapacitacionesCollection(Collection<Capacitacion> capacitacionesCollection) {
        this.capacitacionesCollection = capacitacionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivel != null ? idNivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelCapacitacion)) {
            return false;
        }
        NivelCapacitacion other = (NivelCapacitacion) object;
        if ((this.idNivel == null && other.idNivel != null) || (this.idNivel != null && !this.idNivel.equals(other.idNivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.NivelesCapacitacion[ idNivel=" + idNivel + " ]";
    }
    
}
