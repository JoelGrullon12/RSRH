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
@Table(name = "candidatos")
@NamedQueries({
    @NamedQuery(name = "Candidatos.findAll", query = "SELECT c FROM Candidatos c"),
    @NamedQuery(name = "Candidatos.findByIdCandidato", query = "SELECT c FROM Candidatos c WHERE c.idCandidato = :idCandidato"),
    @NamedQuery(name = "Candidatos.findByCedula", query = "SELECT c FROM Candidatos c WHERE c.cedula = :cedula"),
    @NamedQuery(name = "Candidatos.findByNombre", query = "SELECT c FROM Candidatos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Candidatos.findByApellido", query = "SELECT c FROM Candidatos c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Candidatos.findBySalario", query = "SELECT c FROM Candidatos c WHERE c.salario = :salario")})
public class Candidato extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_candidato")
    private Integer idCandidato;
    @Basic(optional = false)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "salario")
    private BigDecimal salario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidatoId")
    private Collection<Recomendacion> recomendacionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidatoId")
    private Collection<Capacitacion> capacitacionesCollection;
    @JoinColumn(name = "departamento_id", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false)
    private Departamento departamentoId;
    @JoinColumn(name = "puesto_id", referencedColumnName = "id_puesto")
    @ManyToOne(optional = false)
    private Puesto puestoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidatoId")
    private Collection<Competencia> competenciasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidatoId")
    private Collection<IdiomaCandidato> idiomasCandidatosCollection;

    public Candidato() {
    }

    public Candidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Candidato(Integer idCandidato, String cedula, String nombre, String apellido, BigDecimal salario) {
        this.idCandidato = idCandidato;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Collection<Recomendacion> getRecomendacionesCollection() {
        return recomendacionesCollection;
    }

    public void setRecomendacionesCollection(Collection<Recomendacion> recomendacionesCollection) {
        this.recomendacionesCollection = recomendacionesCollection;
    }

    public Collection<Capacitacion> getCapacitacionesCollection() {
        return capacitacionesCollection;
    }

    public void setCapacitacionesCollection(Collection<Capacitacion> capacitacionesCollection) {
        this.capacitacionesCollection = capacitacionesCollection;
    }

    public Departamento getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Departamento departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Puesto getPuestoId() {
        return puestoId;
    }

    public void setPuestoId(Puesto puestoId) {
        this.puestoId = puestoId;
    }

    public Collection<Competencia> getCompetenciasCollection() {
        return competenciasCollection;
    }

    public void setCompetenciasCollection(Collection<Competencia> competenciasCollection) {
        this.competenciasCollection = competenciasCollection;
    }

    public Collection<IdiomaCandidato> getIdiomasCandidatosCollection() {
        return idiomasCandidatosCollection;
    }

    public void setIdiomasCandidatosCollection(Collection<IdiomaCandidato> idiomasCandidatosCollection) {
        this.idiomasCandidatosCollection = idiomasCandidatosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCandidato != null ? idCandidato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.idCandidato == null && other.idCandidato != null) || (this.idCandidato != null && !this.idCandidato.equals(other.idCandidato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Candidatos[ idCandidato=" + idCandidato + " ]";
    }
    
}
