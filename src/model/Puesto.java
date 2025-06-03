/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Joel Grullon
 */
@Entity
@Table(name = "puestos")
@NamedQueries({
    @NamedQuery(name = "Puestos.findAll", query = "SELECT p FROM Puestos p"),
    @NamedQuery(name = "Puestos.findByIdPuesto", query = "SELECT p FROM Puestos p WHERE p.idPuesto = :idPuesto"),
    @NamedQuery(name = "Puestos.findByNombrePuesto", query = "SELECT p FROM Puestos p WHERE p.nombrePuesto = :nombrePuesto"),
    @NamedQuery(name = "Puestos.findByDescripcion", query = "SELECT p FROM Puestos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Puestos.findBySalarioMinimo", query = "SELECT p FROM Puestos p WHERE p.salarioMinimo = :salarioMinimo"),
    @NamedQuery(name = "Puestos.findBySalarioMaximo", query = "SELECT p FROM Puestos p WHERE p.salarioMaximo = :salarioMaximo"),
    @NamedQuery(name = "Puestos.findByEstado", query = "SELECT p FROM Puestos p WHERE p.estado = :estado")})
public class Puesto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_puesto")
    private Integer idPuesto;
    @Basic(optional = false)
    @Column(name = "nombre_puesto")
    private String nombrePuesto;
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_minimo")
    private BigDecimal salarioMinimo;
    @Column(name = "salario_maximo")
    private BigDecimal salarioMaximo;
    @JoinColumn(name = "riesgo_id", referencedColumnName = "id_riesgo")
    @ManyToOne(optional = false)
    private RiesgoPuesto riesgoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puestoId")
    private Collection<Candidato> candidatosCollection;

    public Puesto() {
    }

    public Puesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Puesto(Integer idPuesto, String nombrePuesto) {
        this.idPuesto = idPuesto;
        this.nombrePuesto = nombrePuesto;
    }

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getSalarioMinimo() {
        return salarioMinimo;
    }

    public void setSalarioMinimo(BigDecimal salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }

    public BigDecimal getSalarioMaximo() {
        return salarioMaximo;
    }

    public void setSalarioMaximo(BigDecimal salarioMaximo) {
        this.salarioMaximo = salarioMaximo;
    }

    public RiesgoPuesto getRiesgoId() {
        return riesgoId;
    }

    public void setRiesgoId(RiesgoPuesto riesgoId) {
        this.riesgoId = riesgoId;
    }

    public Collection<Candidato> getCandidatosCollection() {
        return candidatosCollection;
    }

    public void setCandidatosCollection(Collection<Candidato> candidatosCollection) {
        this.candidatosCollection = candidatosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuesto != null ? idPuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puesto)) {
            return false;
        }
        Puesto other = (Puesto) object;
        if ((this.idPuesto == null && other.idPuesto != null) || (this.idPuesto != null && !this.idPuesto.equals(other.idPuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Puestos[ idPuesto=" + idPuesto + " ]";
    }
    
}
